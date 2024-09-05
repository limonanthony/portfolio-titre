package api.infrastructure.libraries.di.modules

import api.adapters.repositories.ImageRepository
import api.adapters.repositories.ProjectRepository
import api.adapters.repositories.ReviewRepository
import api.adapters.repositories.UserRepository
import api.infrastructure.database.repositories.ImageRepositoryImpl
import api.infrastructure.database.repositories.ProjectRepositoryImpl
import api.infrastructure.database.repositories.ReviewRepositoryImpl
import api.infrastructure.database.repositories.UserRepositoryImpl
import org.koin.dsl.module

val repositoriesModules = module {
    factory<ImageRepository> { ImageRepositoryImpl() }
    factory<ProjectRepository> { ProjectRepositoryImpl() }
    factory<ReviewRepository> { ReviewRepositoryImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl() }
}