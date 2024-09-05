package api.app.services

import api.adapters.dtos.LoginDto
import api.adapters.dtos.RegisterDto
import api.adapters.dtos.UserCreationDto
import api.adapters.libraries.JwtService
import api.adapters.presenters.TokensPresenter
import api.app.abstract.CoroutineContext
import api.app.entities.User
import api.app.entities.enums.UserType
import api.app.exceptions.InvalidCredentialsException
import api.app.exceptions.NotFoundException
import api.infrastructure.libraries.jwt.enums.TokenType
import api.infrastructure.libraries.jwt.exceptions.InvalidTokenException
import kotlinx.coroutines.withContext

class AuthService(
    private val jwtService: JwtService,
    private val userService: UserService
) : CoroutineContext() {
    suspend fun login(data: LoginDto): TokensPresenter {
        try {
            val user = userService.findByCredentials(email = data.email, password = data.password)
            return TokensPresenter(
                accessToken = jwtService.create(user, TokenType.ACCESS),
                refreshToken = jwtService.create(user, TokenType.REFRESH)
            )
        } catch (e: Exception) {
            throw InvalidCredentialsException()
        }
    }

    suspend fun register(data: RegisterDto): TokensPresenter {
        val user = userService.create(
            UserCreationDto(
                email = data.email,
                password = data.password,
                type = UserType.USER
            )
        )
        return TokensPresenter(
            accessToken = jwtService.create(user, TokenType.ACCESS),
            refreshToken = jwtService.create(user, TokenType.REFRESH)
        )
    }

    suspend fun refresh(refreshToken: String): TokensPresenter {
        if (!jwtService.verify(refreshToken)) {
            throw InvalidTokenException()
        }
        val payload = jwtService.decode(refreshToken)
        val user = userService.findById(payload.sub)
        return TokensPresenter(
            accessToken = jwtService.create(user, TokenType.ACCESS),
            refreshToken = refreshToken
        )
    }

    suspend fun getUserFromToken(token: String): User = withContext(dispatcher) {
        if (!jwtService.verify(token)) {
            throw InvalidTokenException()
        }
        
        val payload = jwtService.decode(token)

        try {
            userService.findById(payload.sub)
        } catch (e: NotFoundException) {
            throw InvalidTokenException()
        }
    }
}