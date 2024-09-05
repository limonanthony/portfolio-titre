package api.infrastructure.database.tables

import org.jetbrains.exposed.sql.Table

object ProjectImagesTable : Table() {
    val project = reference("project", ProjectsTable)
    val image = reference("image", ImagesTable)

    override val primaryKey = PrimaryKey(project, image)
}