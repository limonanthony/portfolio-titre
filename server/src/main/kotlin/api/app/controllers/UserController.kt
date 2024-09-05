package api.app.controllers

import api.adapters.dtos.BaseEditionRequestDto
import api.adapters.dtos.BaseRequestDto
import api.adapters.dtos.UserCreationDto
import api.adapters.dtos.UserEditionDto
import api.app.abstract.CoroutineContext
import api.app.entities.User
import api.app.entities.enums.UserType
import api.app.exceptions.UnauthorizedException
import api.app.middlewares.TokenMiddleware
import api.app.services.UserService
import java.util.UUID

class UserController(
    private val tokenMiddleware: TokenMiddleware,
    private val userService: UserService,
) : CoroutineContext() {
    suspend fun create(request: BaseRequestDto<UserCreationDto>) = tokenMiddleware.getAdmin(request.accessToken) {
        userService.create(request.payload)
    }

    suspend fun update(request: BaseRequestDto<BaseEditionRequestDto<UserEditionDto>>) =
        tokenMiddleware.getUser(request.accessToken) {
            if (it.type != UserType.ADMIN && it.id != request.payload.id) throw UnauthorizedException()
            userService.edit(request.payload.id, request.payload.data)
        }

    suspend fun getAll(): List<User> {
        return userService.findAll()
    }

    suspend fun delete(request: BaseRequestDto<UUID>) = tokenMiddleware.getUser(request.accessToken) {
        if (it.type != UserType.ADMIN && it.id != request.payload) throw UnauthorizedException()
        userService.delete(request.payload)
    }
}