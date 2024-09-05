package api.infrastructure.database.repositories

import api.adapters.repositories.ProjectRepository
import api.app.entities.Project
import java.util.UUID

class ProjectRepositoryImpl : ProjectRepository {
    override suspend fun exists(predicate: (Project) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun save(entity: Project) {
        TODO("Not yet implemented")
    }

    override suspend fun create(entity: Project): Project {
        TODO("Not yet implemented")
    }

    override suspend fun find(predicate: (Project) -> Boolean): Project? {
        TODO("Not yet implemented")
    }

    override suspend fun findMany(predicate: (Project) -> Boolean): List<Project> {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Project> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: UUID) {
        TODO("Not yet implemented")
    }
}