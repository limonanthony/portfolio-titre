package api.adapters.repositories

import java.util.*

interface BaseRepository<Entity> {
    suspend fun exists(predicate: (entity: Entity) -> Boolean): Boolean
    suspend fun save(entity: Entity)
    suspend fun create(entity: Entity): Entity
    suspend fun find(predicate: (entity: Entity) -> Boolean): Entity?
    suspend fun findMany(predicate: (entity: Entity) -> Boolean): List<Entity>
    suspend fun findAll(): List<Entity>
    suspend fun delete(id: UUID)
}