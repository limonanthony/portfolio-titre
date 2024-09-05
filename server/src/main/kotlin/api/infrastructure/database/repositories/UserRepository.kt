package api.infrastructure.database.repositories

import api.adapters.repositories.UserRepository
import api.app.abstract.CoroutineContext
import api.app.entities.User
import api.infrastructure.database.models.UserModel
import api.infrastructure.database.tables.UsersTable
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.UUID

class UserRepositoryImpl : UserRepository, CoroutineContext() {
    override suspend fun exists(predicate: (User) -> Boolean): Boolean = withContext(dispatcher) {
        newSuspendedTransaction {
            UserModel.all().map { it.toEntity() }.any(predicate)
        }
    }

    override suspend fun save(entity: User) {
        newSuspendedTransaction {
            UserModel.find { UsersTable.id eq entity.id }.firstOrNull()?.apply {
                email = entity.email
                password = entity.password
                type = entity.type
            }
        }
    }

    override suspend fun create(entity: User): User = withContext(dispatcher) {
        newSuspendedTransaction {
            UserModel.new {
                email = entity.email
                password = entity.password
                type = entity.type
            }.toEntity()
        }
    }

    override suspend fun find(predicate: (User) -> Boolean): User? = withContext(dispatcher) {
        newSuspendedTransaction {
            UserModel.all().map { it.toEntity() }.find(predicate)
        }
    }

    override suspend fun findMany(predicate: (User) -> Boolean): List<User> = withContext(dispatcher) {
        newSuspendedTransaction {
            UserModel.all().map { it.toEntity() }.filter(predicate)
        }
    }

    override suspend fun findAll(): List<User> = withContext(dispatcher) {
        newSuspendedTransaction {
            UserModel.all().map { it.toEntity() }
        }
    }

    override suspend fun delete(id: UUID): Unit = withContext(dispatcher) {
        newSuspendedTransaction {
            UserModel.findById(id)?.delete()
        }
    }
}
