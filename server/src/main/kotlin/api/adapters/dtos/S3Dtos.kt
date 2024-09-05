package api.adapters.dtos

import java.io.File

data class S3CreationDto(
    val path: String,
    val file: File,
)