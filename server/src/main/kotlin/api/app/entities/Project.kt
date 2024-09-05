package api.app.entities

import java.util.*

data class Project(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var githubUrl: String,
    var description: String,
    var images: List<Image>
)