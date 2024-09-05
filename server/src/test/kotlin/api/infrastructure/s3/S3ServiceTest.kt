package api.infrastructure.s3

import api.adapters.libraries.S3Service
import api.infrastructure.env.Env
import api.infrastructure.s3.manager.S3Manager
import api.infrastructure.s3.manager.S3ManagerDevelopment
import io.github.serpro69.kfaker.Faker
import io.kotest.assertions.fail
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.beOfType
import io.mockk.every
import io.mockk.mockk
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class S3ServiceTest : FunSpec({
    val faker = Faker()
    val bucketName = "test-bucket"

    val mainFolderName = "project"

    lateinit var s3Manager: S3Manager
    lateinit var s3Service: S3Service

    fun fakeEnv(): Env {
        val env = mockk<Env>()
        every { env.s3.accessKey } returns "admin"
        every { env.s3.secretKey } returns "adminadminadmin"
        every { env.s3.bucketName } returns bucketName
        every { env.s3.region } returns "test-region"
        every { env.s3.endpointUrl } returns "http://127.0.0.1"
        every { env.s3.endpointPort } returns 9000
        return env
    }

    fun createRandomTextFile(): File {
        val tempFile = File.createTempFile(UUID.randomUUID().toString(), ".txt")
        tempFile.writeText(faker.random.randomString(100))
        return tempFile
    }

    fun getFilePath(mainFolder: String, subFolder: String, fileName: String): String {
        return "$mainFolder/$subFolder/$fileName"
    }

    beforeEach {
        s3Manager = S3ManagerDevelopment(fakeEnv())
        s3Service = S3ServiceImpl(s3Manager)

        s3Manager.initialize()
    }

    context("Create file function") {
        lateinit var file: File
        lateinit var path: String

        beforeEach {
            file = createRandomTextFile()
            path = getFilePath(mainFolderName, UUID.randomUUID().toString(), file.name)
        }

        test("should create the file successfully") {
            s3Service.createFile(path, file)

            s3Service.getFile(path).onSuccess {
                it shouldNotBe null
            }.onFailure {
                fail("The result should not fail.")
            }
        }
    }

    context("Get file function") {
        lateinit var file: File
        lateinit var path: String

        beforeEach {
            file = createRandomTextFile()
            path = getFilePath(mainFolderName, UUID.randomUUID().toString(), file.name)
        }

        test("should return a failure when the object does not exist") {
            s3Service.getFile(path)
                .onSuccess {
                    fail("The function cannot succeed when the object does not exist")
                }
                .onFailure {
                    it shouldNotBe null
                }
        }

        context("should return correct file infos") {
            beforeEach {
                s3Service.createFile(path, file)
            }

            test("should have the same name") {
                s3Service.getFile(path)
                    .onSuccess { it.name shouldBe file.name }
                    .onFailure { fail("Failed to get file: ${it.message}") }
            }

            test("should have the same content type") {
                s3Service.getFile(path)
                    .onSuccess { it.contentType shouldBe file.contentType }
                    .onFailure { fail("Failed to get file: ${it.message}") }
            }

            test("should have the same length") {
                s3Service.getFile(path)
                    .onSuccess { it.length() shouldBe file.length() }
                    .onFailure { fail("Failed to get file: ${it.message}") }
            }

            test("should have the same content") {
                s3Service.getFile(path)
                    .onSuccess { it.readBytes() shouldBe file.readBytes() }
                    .onFailure { fail("Failed to get file: ${it.message}") }
            }
        }
    }

    context("Get files function") {
        lateinit var file: File
        lateinit var path: String

        beforeEach {
            file = createRandomTextFile()
            path = getFilePath(mainFolderName, UUID.randomUUID().toString(), file.name)
        }

        test("should contain the only path created") {
            s3Service.createFile(path, file)
            s3Service.getFilesInFolder(path.substringBeforeLast("/"))
                .onSuccess { it shouldContain path }
                .onFailure { fail("Failed to get files: ${it.message}") }
        }

        test("should have only 1 element") {
            s3Service.createFile(path, file)
            s3Service.getFilesInFolder(path.substringBeforeLast("/"))
                .onSuccess { it.size shouldBe 1 }
                .onFailure { fail("Failed to get files: ${it.message}") }
        }

        test("should have 5 elements") {
            for (i in 1..5) {
                val tempFile = createRandomTextFile()
                val tempPath = "${path.substringBeforeLast("/")}/${tempFile.name}"
                s3Service.createFile(tempPath, tempFile)
            }
            s3Service.getFilesInFolder(path.substringBeforeLast("/"))
                .onSuccess { it.size shouldBe 5 }
                .onFailure { fail("Failed to get files: ${it.message}") }
        }

        test("should be an empty list when no file has been created") {
            s3Service.getFilesInFolder(path.substringBeforeLast("/"))
                .onSuccess { it.isEmpty() shouldBe true }
                .onFailure { fail("Failed to get files: ${it.message}") }
        }
    }

    context("Delete folder function") {
        lateinit var file: File
        lateinit var path: String

        beforeEach {
            file = createRandomTextFile()
            path = "projects/${UUID.randomUUID()}/${file.name}"
        }

        test("should be a success when the folder exists") {
            s3Service.createFile(path, file)
            s3Service.deleteFolder(path.substringBeforeLast("/"))
                .onSuccess {
                    s3Service.getFilesInFolder(path.substringBeforeLast("/"))
                        .onSuccess { it.isEmpty() shouldBe true }
                }
                .onFailure {
                    fail("Failed to delete folder: ${it.message}")
                }
        }

        test("should be a failure when the folder doesn't exist") {
            s3Service.deleteFolder(mainFolderName)
                .onSuccess {
                    fail("Should not be a success")
                }
                .onFailure {
                    beOfType<Exception>()
                }
        }
    }

    context("Delete file function") {
        lateinit var file: File
        lateinit var path: String

        beforeEach {
            file = createRandomTextFile()
            path = getFilePath(mainFolderName, UUID.randomUUID().toString(), file.name)
        }

        test("should succeed when file exists") {
            s3Service.createFile(path, file)
            s3Service.deleteFile(path)
                .onSuccess {
                    true shouldBe true
                }
                .onFailure {
                    fail("Deleting file failed: ${it.message}")
                }
        }

        test("should successfully delete the file") {
            s3Service.createFile(path, file)
            s3Service.deleteFile(path)
            s3Service.getFile(path)
                .onSuccess {
                    fail("Should not succeed getting the file")
                }
                .onFailure {
                    beOfType<Exception>()
                }
        }
    }

    afterEach {
        s3Service.deleteFolder(mainFolderName)
    }
})

val File.contentType: String
    get() = Files.probeContentType(Paths.get(path))