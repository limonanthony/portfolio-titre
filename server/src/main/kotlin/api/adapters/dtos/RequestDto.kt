package api.adapters.dtos

import api.app.entities.User
import java.util.*

data class BaseRequestDto<T>(
    val accessToken: String?,
    val payload: T,
)

data class AuthenticatedRequestDto<T>(
    val user: User,
    val data: T,
)

data class BaseEditionRequestDto<T>(
    val id: UUID,
    val data: T
)