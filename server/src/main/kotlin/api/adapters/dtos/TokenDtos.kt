package api.adapters.dtos

import api.app.entities.enums.UserType
import java.util.*

data class TokenDataDto(
    val sub: UUID,
    val type: UserType,
    val issuedAt: Date,
    val expiresAt: Date,
)