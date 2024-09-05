package api.app.services

import api.adapters.dtos.ImageCreationDto
import api.adapters.dtos.S3CreationDto
import api.adapters.libraries.S3Service
import api.adapters.repositories.ImageRepository
import api.app.abstract.CoroutineContext
import api.app.entities.Image
import api.app.exceptions.ImageDeletionException
import api.app.exceptions.ImageUploadException
import api.app.exceptions.NotFoundException
import api.infrastructure.env.Env
import api.infrastructure.env.enums.EnvironmentType
import kotlinx.coroutines.withContext
import java.util.*

class ImageService(
    private val env: Env,
    private val repository: ImageRepository,
    private val s3Service: S3Service,
) : CoroutineContext() {
    suspend fun create(data: ImageCreationDto): Image = withContext(dispatcher) {
        val image = Image(path = data.path)
        s3Service.createFile(data.path, data.file)
            .onSuccess { repository.create(image) }
            .onFailure {
                println(it)
                throw ImageUploadException(data.path)
            }
        image.apply { url = buildUrl(path) }
    }

    suspend fun createMultiple(data: List<ImageCreationDto>): List<Image> = withContext(dispatcher) {
        val images = data.map {
            Image(path = it.path)
        }
        s3Service.createFiles(data.map { S3CreationDto(path = it.path, file = it.file) })
            .onSuccess { repository.createMultiple(images) }
            .onFailure {
                println(it)
                throw ImageUploadException(it.message ?: "unknown path")
            }
        images
    }

    suspend fun findById(id: UUID): Image = withContext(dispatcher) {
        repository.find { it.id == id }?.apply {
            url = buildUrl(path)
        } ?: throw NotFoundException(Image::class.simpleName!!)
    }

    suspend fun findByIds(ids: List<UUID>): List<Image> = withContext(dispatcher) {
        repository.findMany { ids.contains(it.id) }.map {
            it.apply { url = buildUrl(path) }
        }
    }

    suspend fun delete(id: UUID): Unit = withContext(dispatcher) {
        val image = findById(id)
        s3Service.deleteFile(image.path)
            .onSuccess { repository.delete(image.id) }
            .onFailure {
                println(it)
                throw ImageDeletionException(image.path)
            }
    }

    suspend fun deleteByIds(ids: List<UUID>): Unit = withContext(dispatcher) {
        val images = findByIds(ids)
        if (images.size != ids.size) throw NotFoundException(Image::class.simpleName!!)
        s3Service.deleteFiles(images.map { it.path })
            .onSuccess { repository.deleteMultiple(ids) }
            .onFailure {
                println(it)
                throw ImageDeletionException(images.joinToString(", ") { it.path })
            }
    }

    private fun buildUrl(path: String): String {
        val protocol = if (env.application.environment == EnvironmentType.DEVELOPMENT) "http" else "https"
        val host = env.api.host
        val port = env.api.port
        return "$protocol://$host:$port/$path"
    }
}