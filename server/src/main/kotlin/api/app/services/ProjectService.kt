package api.app.services

import api.adapters.dtos.ImageCreationDto
import api.adapters.dtos.ProjectCreationDto
import api.adapters.dtos.ProjectEditionDto
import api.adapters.libraries.S3Service
import api.adapters.repositories.ProjectRepository
import api.app.abstract.CoroutineContext
import api.app.entities.Project
import api.app.exceptions.NotFoundException
import kotlinx.coroutines.withContext
import java.util.*

class ProjectService(
    private val s3Service: S3Service,
    private val imageService: ImageService,
    private val repository: ProjectRepository
) : CoroutineContext() {
    suspend fun create(data: ProjectCreationDto): Project = withContext(dispatcher) {
        val project = repository.create(
            Project(
                title = data.title,
                description = data.description,
                githubUrl = data.githubUrl,
                images = listOf()
            )
        )

        project.images = imageService.createMultiple(data.images.map {
            ImageCreationDto(it, "projects/${project.id}/${it.name}")
        })

        repository.save(project)

        project
    }

    suspend fun findById(id: UUID): Project = withContext(dispatcher) {
        repository.find { it.id == id } ?: throw NotFoundException(Project::class.simpleName!!)
    }

    suspend fun findAll(): List<Project> = withContext(dispatcher) {
        repository.findAll()
    }

    suspend fun edit(id: UUID, data: ProjectEditionDto): Unit = withContext(dispatcher) {
        val project = findById(id)

        data.title?.let { project.title = it }
        data.githubUrl?.let { project.githubUrl = it }
        data.description?.let { project.description = it }

        repository.save(project)
    }

    suspend fun delete(id: UUID): Unit = withContext(dispatcher) {
        val project = findById(id)
        imageService.deleteByIds(project.images.map { it.id })
        repository.delete(project.id)
        s3Service.deleteFolder(project.id.toString())
    }
}