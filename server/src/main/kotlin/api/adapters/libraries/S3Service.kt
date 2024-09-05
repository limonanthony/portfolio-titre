package api.adapters.libraries

import api.adapters.dtos.S3CreationDto
import java.io.File

interface S3Service {
    suspend fun createFile(path: String, file: File): Result<Unit>
    suspend fun createFiles(data: List<S3CreationDto>): Result<Unit>
    suspend fun getFile(path: String): Result<File>
    suspend fun getFilesInFolder(path: String): Result<List<String>>
    suspend fun deleteFolder(path: String): Result<Unit>
    suspend fun deleteFile(path: String): Result<Unit>
    suspend fun deleteFiles(paths: List<String>): Result<Unit>
}