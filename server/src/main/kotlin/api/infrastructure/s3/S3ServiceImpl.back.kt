import api.app.abstract.CoroutineContext
import api.infrastructure.s3.manager.S3Manager
import aws.sdk.kotlin.services.s3.model.*
import aws.smithy.kotlin.runtime.content.asByteStream
import aws.smithy.kotlin.runtime.content.writeToFile
import kotlinx.coroutines.withContext
import org.koin.core.error.MissingPropertyException
import java.io.File

class S3ServiceImplBack(
    private val s3Manager: S3Manager,
) : CoroutineContext() {
    private val bucketName = s3Manager.bucketName

    suspend fun createFile(path: String, file: File): Result<Unit> = withContext(dispatcher) {
        val client = s3Manager.getClient()
        try {
            val putObjectRequest = PutObjectRequest {
                bucket = bucketName
                key = path
                body = file.asByteStream()
            }
            client.putObject(putObjectRequest)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            client.close()
        }
    }

    suspend fun getFile(path: String): Result<File> = withContext(dispatcher) {
        val client = s3Manager.getClient()
        try {
            val tempFile = File.createTempFile("s3File", null)
            val getObjectRequest = GetObjectRequest {
                bucket = bucketName
                key = path
            }
            client.getObject(
                getObjectRequest,
                block = { it.body?.writeToFile(tempFile) }
            )
            Result.success(tempFile)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            client.close()
        }
    }

    suspend fun getFiles(path: String): Result<List<String>> = withContext(dispatcher) {
        val client = s3Manager.getClient()
        try {
            val listObjectsRequest = ListObjectsV2Request {
                bucket = bucketName
                prefix = path
            }
            val listObjectsResponse = client.listObjectsV2(listObjectsRequest)
            val paths = listObjectsResponse.contents?.map {
                it.key ?: throw MissingPropertyException("S3 key missing")
            }?.toList() ?: emptyList()
            Result.success(paths)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            client.close()
        }
    }

    suspend fun deleteFolder(path: String): Result<Unit> = withContext(dispatcher) {
        val client = s3Manager.getClient()
        try {
            getFiles(path)
                .onSuccess { deleteFiles(it) }
                .onFailure { throw it }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            client.close()
        }
    }

    suspend fun deleteFile(path: String): Result<Unit> = withContext(dispatcher) {
        val client = s3Manager.getClient()
        try {
            val deleteObjectRequest = DeleteObjectRequest {
                bucket = bucketName
                key = path
            }
            client.deleteObject(deleteObjectRequest)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            client.close()
        }
    }

    suspend fun deleteFiles(paths: List<String>): Result<Unit> = withContext(dispatcher) {
        val client = s3Manager.getClient()
        try {
            val objectKeys = paths.map { ObjectIdentifier { key = it } }
            val deleteObjectsRequest = DeleteObjectsRequest {
                bucket = bucketName
                delete = Delete { objects = objectKeys }
            }
            client.deleteObjects(deleteObjectsRequest)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        } finally {
            client.close()
        }
    }
}