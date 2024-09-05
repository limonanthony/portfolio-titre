package api.infrastructure.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object ProjectsTable : UUIDTable("projects") {
    var title = varchar("title", 255)
    var githubUrl = varchar("github_url", 255)
    var description = text("description")
}