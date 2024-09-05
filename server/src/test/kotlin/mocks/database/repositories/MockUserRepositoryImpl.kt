package mocks.database.repositories

import api.adapters.repositories.UserRepository
import api.app.entities.User
import java.util.*

class MockUserRepositoryImpl : UserRepository {
    private var users = mutableListOf<User>()
    override suspend fun exists(predicate: (entity: User) -> Boolean): Boolean {
        return users.any(predicate)
    }

    override suspend fun save(entity: User) {
        find { it.id == entity.id }?.apply {
            email = entity.email
            password = entity.password
            type = entity.type
        }
    }

    override suspend fun create(entity: User): User {
        users.add(entity)
        return entity
    }

    override suspend fun find(predicate: (entity: User) -> Boolean): User? {
        return users.find(predicate)
    }

    override suspend fun findMany(predicate: (entity: User) -> Boolean): List<User> {
        return users.filter(predicate)
    }

    override suspend fun findAll(): List<User> {
        return users.toList()
    }

    override suspend fun delete(id: UUID) {
        users.removeIf { it.id == id }
    }
}