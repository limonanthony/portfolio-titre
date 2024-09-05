package api.app.middlewares

import api.app.entities.User
import api.app.entities.enums.UserType
import api.app.exceptions.UnauthorizedException
import api.app.services.AuthService
import api.infrastructure.libraries.jwt.exceptions.InvalidTokenException

class TokenMiddleware(
    private val authService: AuthService
) {
    @Throws(UnauthorizedException::class, InvalidTokenException::class)
    suspend fun <R> getUser(
        token: String?,
        block: suspend (User) -> R
    ): R {
        if (token == null) throw UnauthorizedException()
        val user = authService.getUserFromToken(token)
        return block(user)
    }

    @Throws(InvalidTokenException::class)
    suspend fun <R> getOptionalUser(
        token: String?,
        block: suspend (User?) -> R
    ): R {
        val user = token?.let { authService.getUserFromToken(it) }
        return block(user)
    }

    @Throws(UnauthorizedException::class, InvalidTokenException::class)
    suspend fun <R> getAdmin(
        token: String?,
        block: suspend (User) -> R
    ): R {
        val user = token?.let { authService.getUserFromToken(it) } ?: throw UnauthorizedException()
        if (user.type != UserType.ADMIN) throw UnauthorizedException()
        return block(user)
    }
}