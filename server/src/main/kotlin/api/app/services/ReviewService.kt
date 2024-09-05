package api.app.services

import api.adapters.dtos.ReviewCreationDto
import api.adapters.dtos.ReviewEditionDto
import api.adapters.repositories.ReviewRepository
import api.app.abstract.CoroutineContext
import api.app.entities.Review
import api.app.entities.User
import api.app.exceptions.ConflictException
import api.app.exceptions.NotFoundException
import kotlinx.coroutines.withContext
import java.util.*

class ReviewService(
    private val userService: UserService,
    private val repository: ReviewRepository,
) : CoroutineContext() {
    suspend fun create(data: ReviewCreationDto): Review = withContext(dispatcher) {
        val author: User? = data.authorId?.let { userService.findById(it) }
        assertNoReviewConflicts(email = data.email, author = author)
        val review = Review(
            email = data.email,
            content = data.content,
            author = author,
            rating = data.rating
        )
        repository.create(review)
    }

    suspend fun findById(id: UUID): Review = withContext(dispatcher) {
        repository.find { it.id == id } ?: throw NotFoundException(Review::class.simpleName!!)
    }

    suspend fun findAll(): List<Review> = withContext(dispatcher) {
        repository.findAll()
    }

    suspend fun edit(id: UUID, data: ReviewEditionDto): Unit = withContext(dispatcher) {
        val review = findById(id)

        data.content?.let { review.content = it }
        data.rating?.let { review.rating = it }

        repository.save(review)
    }

    suspend fun delete(id: UUID): Unit = withContext(dispatcher) {
        val review = findById(id)
        repository.delete(review.id)
    }

    private suspend fun assertNoReviewConflicts(email: String?, author: User?): Unit = withContext(dispatcher) {
        email?.let {
            if (repository.exists { it.email == email || it.author?.email == email }) {
                throw ConflictException(Review::class.simpleName!!, "email")
            }
        }
        author?.let {
            if (repository.exists { it.author?.id == author.id }) {
                throw ConflictException(Review::class.simpleName!!, "author")
            }

            if (repository.exists { it.email == author.email }) {
                throw ConflictException(Review::class.simpleName!!, "email")
            }
        }
    }
}