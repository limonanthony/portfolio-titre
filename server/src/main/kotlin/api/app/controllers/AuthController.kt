package api.app.controllers

import api.adapters.dtos.BaseRequestDto
import api.adapters.dtos.LoginDto
import api.adapters.dtos.RegisterDto
import api.adapters.presenters.TokensPresenter
import api.app.abstract.CoroutineContext
import api.app.services.AuthService

class AuthController(
    private val authService: AuthService
) : CoroutineContext() {
    suspend fun login(request: BaseRequestDto<LoginDto>): TokensPresenter {
        return authService.login(request.payload)
    }

    suspend fun register(request: BaseRequestDto<RegisterDto>): TokensPresenter {
        return authService.register(request.payload)
    }

    suspend fun refreshTokens(request: BaseRequestDto<String>): TokensPresenter {
        return authService.refresh(request.payload)
    }
}