package api.infrastructure.database.models

import api.infrastructure.database.tables.ProjectImagesTable
import api.infrastructure.database.tables.ProjectsTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class ProjectModel(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ProjectModel>(ProjectsTable)

    var title by ProjectsTable.title
    var githubUrl by ProjectsTable.githubUrl
    var description by ProjectsTable.description

    var images by ImageModel via ProjectImagesTable
}