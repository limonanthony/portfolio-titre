package api.app.services

import api.adapters.dtos.ImageCreationDto
import api.adapters.libraries.S3Service
import api.adapters.repositories.ImageRepository
import api.app.entities.Image
import api.app.exceptions.ImageUploadException
import api.app.exceptions.NotFoundException
import api.infrastructure.env.Env
import api.infrastructure.env.enums.EnvironmentType
import extensions.image
import io.github.serpro69.kfaker.Faker
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import mocks.database.repositories.MockImageRepositoryImpl
import java.io.File
import java.util.*

class ImageServiceTest : FunSpec({
    val faker = Faker()

    val host = "localhost"
    val port = 4242

    val bucketName = "test-bucket"

    lateinit var imageRepository: ImageRepository
    lateinit var imageService: ImageService
    lateinit var s3Service: S3Service

    fun fakeEnv(): Env {
        val fakeEnv: Env = mockk()
        every { fakeEnv.application.environment } returns EnvironmentType.DEVELOPMENT
        every { fakeEnv.api.host } returns host
        every { fakeEnv.api.port } returns port

        every { fakeEnv.s3.accessKey } returns "admin"
        every { fakeEnv.s3.secretKey } returns "adminadminadmin"
        every { fakeEnv.s3.bucketName } returns bucketName
        every { fakeEnv.s3.region } returns "test-region"
        every { fakeEnv.s3.endpointUrl } returns "http://127.0.0.1"
        every { fakeEnv.s3.endpointPort } returns 9000
        return fakeEnv
    }

    fun fakeS3Service(): S3Service {
        return mockk<S3Service>()
    }

    beforeEach {
        s3Service = fakeS3Service()

        imageRepository = MockImageRepositoryImpl()
        imageService = ImageService(
            env = fakeEnv(),
            s3Service = s3Service,
            repository = imageRepository
        )
    }

    context("Create function") {
        lateinit var file: File
        lateinit var path: String
        lateinit var dto: ImageCreationDto

        beforeEach {
            file = faker.random.image()
            path = "${UUID.randomUUID()}/${file.name}"
            dto = ImageCreationDto(file, path)

            coEvery { s3Service.createFile(path, file) } returns Result.success(Unit)
        }

        context("should return the image with the correct values") {
            lateinit var image: Image

            beforeEach {
                image = imageService.create(dto)
            }

            test("path should be valid") {
                image.path shouldBe path
            }

            test("url should be valid") {
                val validUrl = "http://$host:$port/$path"
                image.url shouldBe validUrl
            }
        }

        test("should not throw error when the image is created successfully") {
            shouldNotThrow<Exception> { imageService.create(dto) }
        }

        test("should throw when the upload to s3 failed") {
            coEvery { s3Service.createFile(path, file) } returns Result.failure(Exception("error"))
            shouldThrow<ImageUploadException> { imageService.create(dto) }
        }
    }

    context("FindById function") {
        lateinit var file: File
        lateinit var path: String
        lateinit var dto: ImageCreationDto

        beforeEach {
            file = faker.random.image()
            path = "${UUID.randomUUID()}/${file.name}"
            dto = ImageCreationDto(
                path = path,
                file = file
            )

            coEvery { s3Service.createFile(path, file) } returns Result.success(Unit)
        }

        test("should return the right image") {
            val image = imageService.create(dto)
            shouldNotThrow<NotFoundException> {
                with(imageService.findById(image.id)) {
                    val validUrl = "http://$host:$port/$path"
                    this shouldNotBe null
                    id shouldBe image.id
                    path shouldBe image.path
                    url shouldBe validUrl
                }
            }
        }

        test("should throw when the image doesn't exist") {
            shouldThrow<NotFoundException> { imageService.findById(UUID.randomUUID()) }
        }
    }

    context("Delete function") {
        lateinit var file: File
        lateinit var path: String
        lateinit var dto: ImageCreationDto

        beforeEach {
            file = faker.random.image()
            path = "${UUID.randomUUID()}/${file.name}"
            dto = ImageCreationDto(
                path = path,
                file = file
            )

            coEvery { s3Service.createFile(path, file) } returns Result.success(Unit)
        }

        test("should not throw when the image exists") {
            coEvery { s3Service.deleteFile(path) } returns Result.success(Unit)
            val image = imageService.create(dto)
            shouldNotThrow<NotFoundException> {
                imageService.delete(image.id)
            }
        }

        test("should throw when the image does not exist") {
            shouldThrow<NotFoundException> { imageService.delete(UUID.randomUUID()) }
        }

        test("should delete the image") {
            coEvery { s3Service.deleteFile(path) } returns Result.success(Unit)
            val image = imageService.create(dto)
            imageService.delete(image.id)
            shouldThrow<NotFoundException> { imageService.findById(image.id) }
        }
    }
})