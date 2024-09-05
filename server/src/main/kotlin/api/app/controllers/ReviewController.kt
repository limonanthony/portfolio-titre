package api.app.controllers

import api.adapters.dtos.BaseEditionRequestDto
import api.adapters.dtos.BaseRequestDto
import api.adapters.dtos.ReviewCreationDto
import api.adapters.dtos.ReviewEditionDto
import api.app.entities.Review
import api.app.entities.User
import api.app.exceptions.UnauthorizedException
import api.app.middlewares.TokenMiddleware
import api.app.services.ReviewService
import java.util.*

class ReviewController(
    private val tokenMiddleware: TokenMiddleware,
    private val reviewService: ReviewService
) {
    suspend fun create(request: BaseRequestDto<ReviewCreationDto>) =
        tokenMiddleware.getOptionalUser(request.accessToken) {
            verifyUserIsAuthor(request.payload.authorId, it)
            reviewService.create(request.payload)
        }

    suspend fun findAll(): List<Review> {
        return reviewService.findAll()
    }

    suspend fun edit(request: BaseRequestDto<BaseEditionRequestDto<ReviewEditionDto>>) =
        tokenMiddleware.getUser(request.accessToken) {
            reviewService.edit(request.payload.id, request.payload.data)
        }

    suspend fun delete(request: BaseRequestDto<UUID>) =
        tokenMiddleware.getUser(request.accessToken) {
            reviewService.delete(request.payload)
        }

    private fun verifyUserIsAuthor(authorId: UUID?, user: User?) {
        if (authorId == null) return
        if (user == null) throw UnauthorizedException()
        if (authorId != user.id) throw UnauthorizedException()
    }
}