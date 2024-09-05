package api.adapters.repositories

import api.app.entities.Image
import java.util.UUID

interface ImageRepository : BaseRepository<Image> {
    suspend fun createMultiple(images: List<Image>): List<Image>
    suspend fun deleteMultiple(ids: List<UUID>)
}