package api.app.controllers

import api.adapters.dtos.BaseRequestDto
import api.adapters.dtos.ProjectCreationDto
import api.app.middlewares.TokenMiddleware
import api.app.services.ProjectService

class ProjectController(
    private val tokenMiddleware: TokenMiddleware,
    private val projectService: ProjectService,
) {
    suspend fun create(request: BaseRequestDto<ProjectCreationDto>) = tokenMiddleware.getAdmin(request.accessToken) {
        val project = projectService.create(request.payload)
    }
}