package api.adapters.presenters

import api.app.entities.User
import api.app.entities.enums.UserType
import api.infrastructure.libraries.serialization.CustomUUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserPresenter(
    @Serializable(with = CustomUUIDSerializer::class)
    val id: UUID,
    val email: String,
    val type: UserType
) {
    companion object {
        fun fromEntity(user: User): UserPresenter {
            return UserPresenter(
                id = user.id,
                email = user.email,
                type = user.type,
            )
        }
    }
}
