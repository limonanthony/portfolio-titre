package mocks.database.repositories

import api.adapters.repositories.ImageRepository
import api.app.entities.Image
import java.util.*

class MockImageRepositoryImpl : ImageRepository {
    private val images: MutableList<Image> = mutableListOf()
    override suspend fun createMultiple(entities: List<Image>): List<Image> {
        entities.forEach {
            images.add(it)
        }
        return entities
    }

    override suspend fun deleteMultiple(ids: List<UUID>) {
        images.filter { it.id in ids }.forEach {
            images.remove(it)
        }
    }

    override suspend fun exists(predicate: (entity: Image) -> Boolean): Boolean {
        return images.any(predicate)
    }

    override suspend fun save(entity: Image) {
        find { it.id == entity.id }?.apply {
            path = entity.path
            url = entity.url
        }
    }

    override suspend fun create(entity: Image): Image {
        images.add(entity)
        return entity
    }

    override suspend fun find(predicate: (entity: Image) -> Boolean): Image? {
        return images.find(predicate)
    }

    override suspend fun findMany(predicate: (entity: Image) -> Boolean): List<Image> {
        return images.filter(predicate).toList()
    }

    override suspend fun findAll(): List<Image> {
        return images.toList()
    }

    override suspend fun delete(id: UUID) {
        images.removeIf { it.id == id }
    }
}