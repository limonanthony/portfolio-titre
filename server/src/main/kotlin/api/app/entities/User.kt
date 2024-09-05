package api.app.entities

import api.app.entities.enums.UserType
import api.infrastructure.libraries.serialization.CustomUUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class User(
    @Serializable(with = CustomUUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    var email: String,
    var password: String,
    var type: UserType,
)