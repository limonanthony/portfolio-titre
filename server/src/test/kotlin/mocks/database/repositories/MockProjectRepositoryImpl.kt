package mocks.database.repositories

import api.adapters.repositories.ProjectRepository
import api.app.entities.Project
import java.util.*

class MockProjectRepositoryImpl : ProjectRepository {
    private val projects: MutableList<Project> = mutableListOf()
    override suspend fun exists(predicate: (entity: Project) -> Boolean): Boolean {
        return projects.any(predicate)
    }

    override suspend fun save(entity: Project) {
        find { it.id == entity.id }?.apply {
            githubUrl = entity.githubUrl
            images = entity.images
            title = entity.title
            description = entity.description
        }
    }

    override suspend fun create(entity: Project): Project {
        projects.add(entity)
        return entity
    }

    override suspend fun find(predicate: (entity: Project) -> Boolean): Project? {
        return projects.find(predicate)
    }

    override suspend fun findMany(predicate: (entity: Project) -> Boolean): List<Project> {
        return projects.filter(predicate)
    }

    override suspend fun findAll(): List<Project> {
        return projects.toList()
    }

    override suspend fun delete(id: UUID) {
        projects.removeIf { it.id == id }
    }

}