package api.infrastructure.database.tables

import api.app.entities.User
import api.app.entities.enums.UserType
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ResultRow

object UsersTable : UUIDTable("users") {
    var email = varchar("email", 255)
    var password = varchar("password", 255)
    var type = enumeration("type", UserType::class)

    fun ResultRow.toEntity() = User(
        id = this[id].value,
        email = this[email],
        password = this[password],
        type = this[type]
    )
}