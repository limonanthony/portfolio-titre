package api.infrastructure.database.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object ReviewsTable : UUIDTable("reviews") {
    var email = varchar("email", 255).nullable()
    var content = varchar("content", 255)
    var rating = integer("rating")

    var author = reference("author", UsersTable).nullable()
}