package api.infrastructure.database.models

import api.app.entities.Review
import api.infrastructure.database.tables.ReviewsTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class ReviewModel(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ReviewModel>(ReviewsTable)

    var email by ReviewsTable.email
    var content by ReviewsTable.content
    var rating by ReviewsTable.rating

    var author by UserModel optionalReferencedOn ReviewsTable.author

    fun toEntity() = Review(id.value, email, content, rating, author?.toEntity())
}