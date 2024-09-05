package api.app.services

import api.adapters.dtos.UserCreationDto
import api.adapters.dtos.UserEditionDto
import api.adapters.libraries.HashingService
import api.app.entities.User
import api.app.entities.enums.UserType
import api.app.exceptions.ConflictException
import api.app.exceptions.NotFoundException
import io.github.serpro69.kfaker.Faker
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import mocks.database.repositories.MockUserRepositoryImpl
import java.util.*

class UserServiceTest : FunSpec({
    val faker = Faker()

    val hashedReturnValue = faker.random.randomString(24)

    lateinit var service: UserService

    lateinit var dto: UserCreationDto

    beforeTest {
        val hashService = mockk<HashingService>()
        coEvery { hashService.hash(any()) } returns hashedReturnValue

        service = UserService(MockUserRepositoryImpl(), hashService)

        dto = UserCreationDto(
            email = faker.internet.email(),
            password = "Pa\$\$w0rd!",
            type = UserType.USER
        )
    }

    context("Create function") {
        lateinit var user: User

        beforeTest {
            user = service.create(dto)
        }

        test("returned value should match params") {
            user.email shouldBe dto.email
            user.type shouldBe dto.type
        }

        test("password should be hashed") {
            user.password shouldBe hashedReturnValue
        }

        test("should throw when email already taken") {
            shouldThrow<ConflictException> { service.create(dto) }
        }
    }

    context("FindByCredentials function") {
        lateinit var user: User

        beforeTest {
            user = service.create(dto)
        }

        test("should return the correct user") {
            service.findByCredentials(user.email, user.password) shouldBe user
        }

        test("should throw when the user does not exist") {
            shouldThrow<NotFoundException> { service.findByCredentials(faker.random.randomString(10), user.password) }
        }
    }

    context("FindById function") {
        lateinit var user: User

        beforeTest {
            user = service.create(dto)
        }

        test("should return the correct user") {
            service.findById(user.id) shouldBe user
        }

        test("should throw when user does not exist") {
            shouldThrow<NotFoundException> { service.findById(UUID.randomUUID()) }
        }
    }

    context("FindByEmail function") {
        lateinit var user: User

        beforeTest {
            user = service.create(dto)
        }

        test("should return the correct user") {
            service.findByEmail(user.email) shouldBe user
        }

        test("should throw when user does not exist") {
            shouldThrow<NotFoundException> { service.findByEmail(faker.random.randomString(24)) }
        }
    }

    context("FindAll function") {
        test("should return empty list when no users are created") {
            service.findAll().shouldBeEmpty()
        }

        test("should have 1 user after one user created") {
            service.create(dto)
            service.findAll() shouldHaveSize 1
        }

        test("should have 5 users after creating 5 users") {
            for (i in 1..5) {
                service.create(
                    UserCreationDto(
                        email = faker.internet.email(),
                        password = "Pa\$\$w0rd!",
                        type = UserType.USER
                    )
                )
            }
            service.findAll() shouldHaveSize 5
        }
    }

    context("Edit function") {
        lateinit var user: User
        lateinit var editionDto: UserEditionDto

        beforeTest {
            user = service.create(dto)
            editionDto = UserEditionDto(
                email = faker.internet.email(),
                password = "Pa\$\$w0rd!",
                type = UserType.ADMIN
            )
        }

        test("should edit the user") {
            service.edit(user.id, editionDto)
            user = service.findById(user.id)
            user.email shouldBe editionDto.email
            user.type shouldBe editionDto.type
        }

        test("password should be hashed") {
            service.edit(user.id, editionDto)
            user = service.findById(user.id)
            user.password shouldBe hashedReturnValue
        }

        test("should throw when user does not exist") {
            shouldThrow<NotFoundException> { service.edit(UUID.randomUUID(), editionDto) }
        }
    }

    context("Delete function") {
        test("should throw if user does not exist") {
            shouldThrow<NotFoundException> { service.delete(UUID.randomUUID()) }
        }

        test("should delete the user") {
            val user = service.create(dto)
            service.delete(user.id)
            service.findAll() shouldHaveSize 0
        }
    }
})