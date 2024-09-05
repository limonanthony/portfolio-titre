package api.adapters.presenters

import api.app.entities.User
import api.infrastructure.libraries.serialization.CustomUUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ReviewPresenter(
    @Serializable(with = CustomUUIDSerializer::class)
    val id: UUID,
    val email: String?,

    val content: String,
    val rating: Int,

    val author: User?,
)