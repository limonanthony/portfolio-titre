package api.adapters.libraries

import api.adapters.dtos.TokenDataDto
import api.app.entities.User
import api.infrastructure.libraries.jwt.enums.TokenType

interface JwtService {
    fun create(user: User, type: TokenType): String
    fun verify(token: String): Boolean
    fun decode(token: String): TokenDataDto
}