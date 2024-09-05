package api.app.services

import api.adapters.dtos.ReviewCreationDto
import api.adapters.dtos.ReviewEditionDto
import api.adapters.repositories.ReviewRepository
import api.app.entities.Review
import api.app.entities.User
import api.app.exceptions.ConflictException
import api.app.exceptions.NotFoundException
import io.github.serpro69.kfaker.Faker
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import mocks.database.repositories.MockReviewRepositoryImpl
import mocks.utils.getRandomUser
import java.util.*

class ReviewServiceTest : FunSpec({
    val faker = Faker()

    lateinit var userService: UserService
    lateinit var reviewRepository: ReviewRepository

    lateinit var reviewService: ReviewService

    fun getRandomRating() = faker.random.nextInt(1, 5)

    fun getRandomContent() = faker.random.randomString(100)

    beforeTest {
        userService = mockk()
        reviewRepository = MockReviewRepositoryImpl()
        reviewService = ReviewService(
            userService = userService,
            repository = reviewRepository
        )
    }

    context("Create function") {
        lateinit var user: User

        beforeTest {
            user = getRandomUser()
            coEvery { userService.findById(user.id) } returns user
        }

        test("should create the review with an author") {
            val dto = ReviewCreationDto(
                authorId = user.id,
                email = null,
                content = getRandomContent(),
                rating = getRandomRating(),
            )

            val review = reviewService.create(dto)

            with(dto) {
                review.author shouldBe user
                review.email shouldBe email
                review.content shouldBe content
                review.rating shouldBe rating
            }
        }

        test("should create the review with an email") {
            val dto = ReviewCreationDto(
                authorId = null,
                email = faker.internet.email(),
                content = getRandomContent(),
                rating = getRandomRating()
            )

            val review = reviewService.create(dto)

            with(dto) {
                review.author?.id shouldBe authorId
                review.email shouldBe email
                review.content shouldBe content
                review.rating shouldBe rating
            }
        }

        test("should create the review without an email or author") {
            val dto = ReviewCreationDto(
                authorId = null,
                email = null,
                content = getRandomContent(),
                rating = getRandomRating()
            )

            val review = reviewService.create(dto)

            with(dto) {
                review.author?.id shouldBe authorId
                review.email shouldBe email
                review.content shouldBe content
                review.rating shouldBe rating
            }
        }

        test("should throw ConflictException if author is already in a review") {
            val dto = ReviewCreationDto(
                authorId = user.id,
                email = null,
                content = getRandomContent(),
                rating = getRandomRating()
            )

            reviewService.create(dto)

            shouldThrow<ConflictException> { reviewService.create(dto) }
        }

        test("should throw ConflictException when the email is already in a review") {
            val dto = ReviewCreationDto(
                authorId = null,
                email = user.email,
                content = getRandomContent(),
                rating = getRandomRating()
            )

            reviewService.create(dto)

            shouldThrow<ConflictException> {
                reviewService.create(dto)
            }
        }

        test("should throw ConflictException when an author email is already in a review") {
            val dtoAuthor = ReviewCreationDto(
                authorId = user.id,
                email = null,
                content = getRandomContent(),
                rating = getRandomRating()
            )
            val dtoEmail = ReviewCreationDto(
                authorId = null,
                email = user.email,
                content = getRandomContent(),
                rating = getRandomRating()
            )

            reviewService.create(dtoAuthor)

            shouldThrow<ConflictException> { reviewService.create(dtoEmail) }
        }

        test("should throw ConflictException when trying to create a review with an email of an author that already reviewed") {
            val dtoEmail = ReviewCreationDto(
                authorId = null,
                email = user.email,
                content = getRandomContent(),
                rating = getRandomRating(),
            )

            val dtoAuthor = ReviewCreationDto(
                authorId = user.id,
                email = null,
                content = getRandomContent(),
                rating = getRandomRating(),
            )

            reviewService.create(dtoEmail)

            shouldThrow<ConflictException> { reviewService.create(dtoAuthor) }
        }
    }

    context("FindById function") {
        lateinit var review: Review
        beforeTest {
            review = reviewService.create(
                ReviewCreationDto(
                    authorId = null,
                    email = null,
                    content = getRandomContent(),
                    rating = getRandomRating()
                )
            )
        }

        test("should return the review when it exists") {
            reviewService.findById(review.id) shouldBe review
        }

        test("should throw NotFoundException when the review does not exist") {
            shouldThrow<NotFoundException> { reviewService.findById(UUID.randomUUID()) }
        }
    }

    context("FindAll function") {
        test("should return an empty list when no reviews exists") {
            reviewService.findAll() shouldBe emptyList()
        }
        test("should return 1 review if 1 review has been created") {
            reviewService.create(
                ReviewCreationDto(
                    authorId = null,
                    email = null,
                    content = getRandomContent(),
                    rating = getRandomRating()
                )
            )
            reviewService.findAll().size shouldBe 1
        }
        test("should return 10 reviews if 10 reviews has been created") {
            for (i in 1..10) {
                reviewService.create(
                    ReviewCreationDto(
                        authorId = null,
                        email = null,
                        content = getRandomContent(),
                        rating = getRandomRating()
                    )
                )
            }
            reviewService.findAll().size shouldBe 10
        }
    }

    context("Edit function") {
        lateinit var creationDto: ReviewCreationDto
        lateinit var editionDto: ReviewEditionDto
        lateinit var review: Review

        beforeTest {
            creationDto = ReviewCreationDto(
                authorId = null,
                email = null,
                content = getRandomContent(),
                rating = getRandomRating()
            )
            editionDto = ReviewEditionDto(
                content = getRandomContent(),
                rating = getRandomRating()
            )
            review = reviewService.create(creationDto)
        }

        test("should update the review correctly") {
            reviewService.edit(review.id, editionDto)
            val updatedReview = reviewService.findById(review.id)
            updatedReview.content shouldBe editionDto.content
            updatedReview.rating shouldBe editionDto.rating
        }

        test("should throw if the review does not exist") {
            shouldThrow<NotFoundException> { reviewService.edit(UUID.randomUUID(), editionDto) }
        }
    }

    context("Delete function") {
        test("should throw a NotFoundException if the review does not exist") {
            shouldThrow<NotFoundException> { reviewService.delete(UUID.randomUUID()) }
        }

        test("should delete the review if the review exists") {
            val review = reviewService.create(
                ReviewCreationDto(
                    authorId = null,
                    email = null,
                    content = getRandomContent(),
                    rating = getRandomRating()
                )
            )
            reviewService.findAll().size shouldBe 1
            reviewService.delete(review.id)
            reviewService.findAll().size shouldBe 0
            shouldThrow<NotFoundException> { reviewService.findById(review.id) }
        }
    }
})