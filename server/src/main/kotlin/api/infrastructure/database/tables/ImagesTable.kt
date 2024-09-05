package api.infrastructure.database.tables

import api.app.entities.Image
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow

object ImagesTable : UUIDTable("images") {
    var path = varchar("path", 255)
    var url = varchar("url", 255)

    fun ResultRow.toEntity() = Image(
        id = this[id].value,
        path = this[path],
        url = this[url]
    )
}