package api.infrastructure.libraries.jwt

import api.adapters.dtos.TokenDataDto
import api.adapters.libraries.JwtService
import api.app.entities.User
import api.app.entities.enums.UserType
import api.infrastructure.env.Env
import api.infrastructure.libraries.jwt.enums.TokenType
import api.infrastructure.libraries.jwt.exceptions.ExpiredTokenException
import api.infrastructure.libraries.jwt.exceptions.InvalidTokenException
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.TokenExpiredException
import java.util.*

class JwtServiceImpl(
    env: Env,
) : JwtService {
    private val issuer = "auth0"
    private val secret = env.jwt.secret
    private val accessExpiration = env.jwt.accessExpiration
    private val refreshExpiration = env.jwt.refreshExpiration
    private val algorithm = Algorithm.HMAC256(secret)
    private val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .build()

    override fun create(user: User, type: TokenType): String {
        val currentDate = Date()
        val expirationDate = Date(currentDate.time + getExpirationHours(type) * 3600 * 1000)
        return JWT.create()
            .withIssuer(issuer)
            .withIssuedAt(currentDate)
            .withExpiresAt(expirationDate)
            .withSubject(user.id.toString())
            .withClaim("type", user.type.value)
            .sign(algorithm)
    }

    override fun verify(token: String): Boolean {
        return try {
            verifier.verify(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun decode(token: String): TokenDataDto {
        try {
            val decodedJWT = verifier.verify(token)
            return TokenDataDto(
                sub = UUID.fromString(decodedJWT.subject),
                type = UserType.fromValue(decodedJWT.getClaim("type").asString()),
                issuedAt = decodedJWT.issuedAt,
                expiresAt = decodedJWT.expiresAt
            )
        } catch (e: TokenExpiredException) {
            throw ExpiredTokenException()
        } catch (e: Exception) {
            throw InvalidTokenException()
        }
    }

    private fun getExpirationHours(type: TokenType): Int {
        return when (type) {
            TokenType.ACCESS -> accessExpiration
            TokenType.REFRESH -> refreshExpiration
        }
    }

}