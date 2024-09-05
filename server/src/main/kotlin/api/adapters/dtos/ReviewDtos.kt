package api.adapters.dtos

import api.infrastructure.libraries.serialization.NullableUUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class ReviewCreationDto(
    @Serializable(with = NullableUUIDSerializer::class)
    val authorId: UUID?,
    val email: String?,

    val content: String,
    val rating: Int,
)

@Serializable
data class ReviewEditionDto(
    val content: String?,
    val rating: Int?,
)