package api.infrastructure.database.models

import api.infrastructure.database.tables.ImagesTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID

import java.util.UUID

class ImageModel(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ImageModel>(ImagesTable)

    var path by ImagesTable.path
    var url by ImagesTable.url
}