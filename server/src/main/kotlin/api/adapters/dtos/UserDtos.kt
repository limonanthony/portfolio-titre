package api.adapters.dtos

import api.app.entities.enums.UserType
import kotlinx.serialization.Serializable

@Serializable
data class UserCreationDto(
    val email: String,
    val password: String,
    val type: UserType = UserType.USER
)

@Serializable
data class UserEditionDto(
    val email: String? = null,
    val password: String? = null,
    val type: UserType? = null,
)