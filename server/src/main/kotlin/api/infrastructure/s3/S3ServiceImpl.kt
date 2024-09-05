package api.infrastructure.s3

import api.adapters.dtos.S3CreationDto
import api.adapters.libraries.S3Service
import api.app.abstract.CoroutineContext
import api.infrastructure.s3.manager.S3Manager
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.*
import aws.smithy.kotlin.runtime.content.asByteStream
import aws.smithy.kotlin.runtime.content.writeToFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.koin.core.error.MissingPropertyException
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class S3ServiceImpl(
    private val s3Manager: S3Manager,
) : S3Service, CoroutineContext() {
    private val bucketName = s3Manager.bucketName

    override suspend fun createFile(path: String, file: File): Result<Unit> = withS3Client {
        val putObjectRequest = PutObjectRequest {
            bucket = bucketName
            key = path
            body = file.asByteStream()
            contentType = Files.probeContentType(Paths.get(file.path)) ?: "application/octet-stream"
            contentLength = file.length()
        }
        it.putObject(putObjectRequest)
        Result.success(Unit)
    }

    override suspend fun createFiles(data: List<S3CreationDto>): Result<Unit> = withS3Client {
        val deferredUploads = data.map { dto ->
            async {
                createFile(dto.path, dto.file)
                    .onFailure { throw it }
            }
        }
        deferredUploads.awaitAll()
        Result.success(Unit)
    }

    override suspend fun getFile(path: String): Result<File> = withS3Client {
        val fileName = path.substringAfterLast('/')
        val tempDir = Files.createTempDirectory("s3_downloads").toFile()
        val tempFile = File(tempDir, fileName)

        val getObjectRequest = GetObjectRequest {
            bucket = bucketName
            key = path
        }
        it.getObject(getObjectRequest) { response ->
            response.body?.writeToFile(tempFile)
        }
        Result.success(tempFile)
    }

    override suspend fun getFilesInFolder(path: String): Result<List<String>> = withS3Client {
        val listObjectsRequest = ListObjectsV2Request {
            bucket = bucketName
            prefix = path
        }
        val listObjectsResponse = it.listObjectsV2(listObjectsRequest)
        val paths = listObjectsResponse.contents?.map { response ->
            response.key ?: throw MissingPropertyException("S3 key missing")
        }?.toList() ?: emptyList()
        Result.success(paths)
    }

    override suspend fun deleteFolder(path: String): Result<Unit> = withS3Client {
        getFilesInFolder(path)
            .onSuccess {
                if (it.isEmpty()) {
                    return@withS3Client Result.failure(MissingPropertyException("Folder does not exist"))
                } else
                    deleteFiles(it)
            }
            .onFailure { throw it }
        Result.success(Unit)
    }

    override suspend fun deleteFile(path: String): Result<Unit> = withS3Client {
        val deleteObjectRequest = DeleteObjectRequest {
            bucket = bucketName
            key = path
        }
        it.deleteObject(deleteObjectRequest)
        Result.success(Unit)
    }

    override suspend fun deleteFiles(paths: List<String>): Result<Unit> = withS3Client {
        val objectKeys = paths.map { ObjectIdentifier { key = it } }
        val deleteObjectsRequest = DeleteObjectsRequest {
            bucket = bucketName
            delete = Delete { objects = objectKeys }
        }
        it.deleteObjects(deleteObjectsRequest)
        Result.success(Unit)
    }

    private suspend fun <T> withS3Client(
        block: suspend CoroutineScope.(client: S3Client) -> Result<T>
    ) = withContext(dispatcher) {
        val client = s3Manager.getClient()
        try {
            block(client)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            client.close()
        }
    }
}