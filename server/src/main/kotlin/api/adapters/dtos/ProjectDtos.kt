package api.adapters.dtos

import java.io.File

data class ProjectCreationDto(
    val title: String,
    val githubUrl: String,
    val description: String,
    val images: List<File>
)

data class ProjectEditionDto(
    val title: String?,
    val githubUrl: String?,
    val description: String?,
)