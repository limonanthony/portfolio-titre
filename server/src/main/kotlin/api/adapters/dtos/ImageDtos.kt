package api.adapters.dtos

import java.io.File

data class ImageCreationDto(
    val file: File,
    val path: String,
)