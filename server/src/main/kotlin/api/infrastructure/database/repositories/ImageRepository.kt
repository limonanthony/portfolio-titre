package api.infrastructure.database.repositories

import api.adapters.repositories.ImageRepository
import api.app.abstract.CoroutineContext
import api.app.entities.Image
import java.util.UUID

class ImageRepositoryImpl : ImageRepository, CoroutineContext() {
    override suspend fun createMultiple(images: List<Image>): List<Image> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMultiple(ids: List<UUID>) {
        TODO("Not yet implemented")
    }

    override suspend fun exists(predicate: (Image) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun save(entity: Image) {
        TODO("Not yet implemented")
    }

    override suspend fun create(entity: Image): Image {
        TODO("Not yet implemented")
    }

    override suspend fun find(predicate: (Image) -> Boolean): Image? {
        TODO("Not yet implemented")
    }

    override suspend fun findMany(predicate: (Image) -> Boolean): List<Image> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Image> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}
