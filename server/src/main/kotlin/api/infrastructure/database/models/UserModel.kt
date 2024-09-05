package api.infrastructure.database.models

import api.app.entities.User
import api.infrastructure.database.tables.UsersTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class UserModel(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserModel>(UsersTable)

    var email by UsersTable.email
    var password by UsersTable.password
    var type by UsersTable.type

    fun toEntity() = User(id.value, email, password, type)
}