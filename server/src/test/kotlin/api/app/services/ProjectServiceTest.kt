package api.app.services

import api.adapters.dtos.ProjectCreationDto
import api.adapters.libraries.S3Service
import api.adapters.repositories.ProjectRepository
import io.github.serpro69.kfaker.Faker
import io.kotest.core.spec.style.FunSpec
import io.mockk.mockk
import mocks.database.repositories.MockProjectRepositoryImpl

class ProjectServiceTest : FunSpec({
    val faker = Faker()
    lateinit var service: ProjectService
    lateinit var s3Service: S3Service
    lateinit var imageService: ImageService
    lateinit var repository: ProjectRepository

    beforeEach {
        repository = MockProjectRepositoryImpl()
        s3Service = mockk()
        imageService = mockk()
        service = ProjectService(s3Service, imageService, repository)
    }

    context("Create function") {
        lateinit var dto: ProjectCreationDto
        beforeEach {
            dto = ProjectCreationDto(
                title = faker.random.randomString(64),
                description = faker.random.randomString(256),
                githubUrl = "http://github.com/",
                images = listOf(),
            )

        }

        test("should create the project successfully") {

        }
    }
})