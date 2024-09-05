package mocks.database.repositories

import api.adapters.repositories.ReviewRepository
import api.app.entities.Review
import java.util.*

class MockReviewRepositoryImpl : ReviewRepository {
    private val reviews: MutableList<Review> = mutableListOf()

    override suspend fun exists(predicate: (entity: Review) -> Boolean): Boolean {
        return reviews.any(predicate)
    }

    override suspend fun save(entity: Review) {
        find { it.id == entity.id }?.apply {
            email = entity.email
            content = entity.content
            rating = entity.rating
            author = entity.author
        }
    }

    override suspend fun create(entity: Review): Review {
        reviews.add(entity)
        return entity
    }

    override suspend fun find(predicate: (entity: Review) -> Boolean): Review? {
        return reviews.find(predicate)
    }

    override suspend fun findMany(predicate: (entity: Review) -> Boolean): List<Review> {
        return reviews.filter(predicate)
    }

    override suspend fun findAll(): List<Review> {
        return reviews.toList()
    }

    override suspend fun delete(id: UUID) {
        reviews.removeIf { it.id == id }
    }
}