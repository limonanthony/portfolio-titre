package api.infrastructure.libraries.jwt

import api.adapters.dtos.TokenDataDto
import api.adapters.libraries.JwtService
import api.app.entities.User
import api.app.entities.enums.UserType
import api.infrastructure.env.Env
import api.infrastructure.libraries.jwt.enums.TokenType
import api.infrastructure.libraries.jwt.exceptions.InvalidTokenException
import io.github.serpro69.kfaker.Faker
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldHaveLengthBetween
import io.kotest.matchers.string.shouldNotBeEmpty
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll

class JwtServiceTest : FunSpec({
    val faker = Faker()

    val accessExpiration = 5
    val refreshExpiration = 10 * 24

    lateinit var jwtService: JwtService

    beforeTest {
        val env = mockk<Env>()
        coEvery { env.jwt.secret } returns "randomHash256"
        coEvery { env.jwt.accessExpiration } returns accessExpiration
        coEvery { env.jwt.refreshExpiration } returns refreshExpiration

        jwtService = JwtServiceImpl(env)
    }

    afterTest {
        unmockkAll()
    }

    fun getRandomTokenType() = TokenType.entries.random()

    fun getRandomUser(): User {
        return User(
            email = faker.internet.email(),
            password = "Pa\$\$w0rd!",
            type = UserType.entries.random()
        )
    }

    context("Create function") {
        test("should create a valid token") {
            val user = getRandomUser()

            val token = jwtService.create(user, type = TokenType.ACCESS)

            token shouldNotBe null
            token.shouldNotBeEmpty()
            token.shouldHaveLengthBetween(200, 1000)
        }
    }

    context("Verify function") {
        lateinit var token: String

        beforeTest {
            val user = getRandomUser()
            token = jwtService.create(user, type = TokenType.REFRESH)
        }

        test("should return true when the token is valid") {
            jwtService.verify(token) shouldBe true
        }

        test("should not return true when the token is invalid") {
            jwtService.verify(faker.random.randomString(500)) shouldNotBe true
        }
    }

    context("Decode function") {
        lateinit var tokenType: TokenType
        lateinit var user: User
        lateinit var token: String
        lateinit var decodedToken: TokenDataDto

        beforeTest {
            user = getRandomUser()
            tokenType = getRandomTokenType()
            token = jwtService.create(user, tokenType)
            decodedToken = jwtService.decode(token)
        }

        test("should return the right userId") {
            decodedToken.sub shouldBe user.id
        }

        test("should return the right user type") {
            decodedToken.type shouldBe user.type
        }

        test("should throw if the token is invalid") {
            shouldThrow<InvalidTokenException> {
                jwtService.decode(faker.random.randomString(100))
            }
        }
    }
})