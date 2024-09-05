package api.app.entities

import api.infrastructure.libraries.serialization.CustomUUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Review(
    @Serializable(with = CustomUUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    var email: String?,

    var content: String,
    var rating: Int,

    @Serializable
    var author: User?,
) {
    init {
        require(rating in 1..5) { "Rating must be between 1 and 5" }
    }
}