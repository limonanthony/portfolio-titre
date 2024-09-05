package api.app.entities

import java.util.*

data class Image(
    val id: UUID = UUID.randomUUID(),
    var path: String,
    var url: String = "",
)