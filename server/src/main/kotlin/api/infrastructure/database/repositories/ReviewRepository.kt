package api.infrastructure.database.repositories

import api.adapters.repositories.ReviewRepository
import api.adapters.repositories.UserRepository
import api.app.abstract.CoroutineContext
import api.app.entities.Review
import api.infrastructure.database.models.ReviewModel
import api.infrastructure.database.models.UserModel
import api.infrastructure.database.tables.ReviewsTable
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.UUID

class ReviewRepositoryImpl(
    private val userRepository: UserRepository,
) : ReviewRepository, CoroutineContext() {
    override suspend fun exists(predicate: (Review) -> Boolean): Boolean = withContext(dispatcher) {
        newSuspendedTransaction {
            ReviewModel.all().map { it.toEntity() }.any(predicate)
        }
    }

    override suspend fun save(entity: Review): Unit = withContext(dispatcher) {
        newSuspendedTransaction {
            ReviewModel.find { ReviewsTable.id eq entity.id }.firstOrNull()?.apply {
                this.email = entity.email
                this.content = entity.content
                this.rating = entity.rating
            }
        }
    }

    override suspend fun create(entity: Review): Review = withContext(dispatcher) {
        newSuspendedTransaction {
            ReviewModel.new {
                email = entity.email
                content = entity.content
                rating = entity.rating
                author = entity.author?.let { UserModel.findById(it.id) }
            }.toEntity()
        }
    }

    override suspend fun find(predicate: (Review) -> Boolean): Review? = withContext(dispatcher) {
        newSuspendedTransaction {
            ReviewModel.all().map { it.toEntity() }.find(predicate)
        }
    }

    override suspend fun findMany(predicate: (Review) -> Boolean): List<Review> = withContext(dispatcher) {
        newSuspendedTransaction {
            ReviewModel.all().map { it.toEntity() }.filter(predicate)
        }
    }

    override suspend fun findAll(): List<Review> = withContext(dispatcher) {
        newSuspendedTransaction {
            ReviewModel.all().map { it.toEntity() }
        }
    }

    override suspend fun delete(id: UUID) {
        newSuspendedTransaction {
            ReviewModel.findById(id)?.delete()
        }
    }
}