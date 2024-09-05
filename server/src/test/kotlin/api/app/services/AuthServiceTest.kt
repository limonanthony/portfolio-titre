package api.app.services

import api.adapters.dtos.LoginDto
import api.adapters.dtos.RegisterDto
import api.adapters.dtos.TokenDataDto
import api.adapters.dtos.UserCreationDto
import api.adapters.libraries.JwtService
import api.app.entities.User
import api.app.entities.enums.UserType
import api.app.exceptions.InvalidCredentialsException
import api.app.exceptions.NotFoundException
import api.infrastructure.libraries.jwt.enums.TokenType
import api.infrastructure.libraries.jwt.exceptions.InvalidTokenException
import io.github.serpro69.kfaker.Faker
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import java.util.*

class AuthServiceTest : FunSpec({
    val faker = Faker()

    val accessToken = "access-token"
    val refreshToken = "refresh-token"

    lateinit var authService: AuthService
    lateinit var jwtService: JwtService
    lateinit var userService: UserService

    lateinit var registerDto: RegisterDto
    lateinit var loginDto: LoginDto
    lateinit var user: User

    beforeTest {
        jwtService = mockk()
        userService = mockk()
        authService = AuthService(jwtService, userService)
        registerDto = RegisterDto(
            email = faker.internet.email(),
            password = "Pa\$\$w0rd!"
        )
        loginDto = LoginDto(
            email = registerDto.email,
            password = registerDto.password
        )
        user = User(
            id = UUID.randomUUID(),
            email = registerDto.email,
            password = registerDto.password,
            type = UserType.entries.random()
        )
    }

    context("Login function") {
        test("return tokens with correct credentials") {
            coEvery { userService.findByCredentials(user.email, user.password) } returns user
            every { jwtService.create(user, TokenType.ACCESS) } returns accessToken
            every { jwtService.create(user, TokenType.REFRESH) } returns refreshToken

            val tokens = authService.login(loginDto)

            tokens.accessToken shouldBe accessToken
            tokens.refreshToken shouldBe refreshToken
        }

        test("should throw when the credentials are wrong") {
            coEvery { userService.findByCredentials(any(), any()) } throws NotFoundException(User::class.simpleName!!)
            shouldThrow<InvalidCredentialsException> { authService.login(loginDto) }
        }
    }

    context("Register function") {
        test("returns tokens") {
            coEvery { userService.create(UserCreationDto(registerDto.email, registerDto.password)) } returns user
            every { jwtService.create(user, TokenType.ACCESS) } returns accessToken
            every { jwtService.create(user, TokenType.REFRESH) } returns refreshToken
            val tokens = authService.register(registerDto)

            tokens.accessToken shouldBe accessToken
            tokens.refreshToken shouldBe refreshToken
        }
    }

    context("Refresh function") {
        test("should return tokens when refresh token is valid") {
            coEvery { userService.findById(user.id) } returns user
            coEvery { jwtService.create(user, TokenType.ACCESS) } returns accessToken
            coEvery { jwtService.verify(refreshToken) } returns true
            coEvery { jwtService.decode(refreshToken) } returns TokenDataDto(
                user.id,
                user.type,
                issuedAt = Date(),
                expiresAt = Date(Date().time + 10000)
            )

            val tokens = authService.refresh(refreshToken)

            tokens.accessToken shouldBe accessToken
            tokens.refreshToken shouldBe refreshToken
        }

        test("should throw when the token is invalid") {
            coEvery { jwtService.verify(refreshToken) } returns false

            shouldThrow<InvalidTokenException> { authService.refresh(refreshToken) }
        }
    }
})
