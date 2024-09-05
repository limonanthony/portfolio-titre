package api.infrastructure.s3

import api.infrastructure.env.Env
import api.infrastructure.s3.manager.S3Manager
import api.infrastructure.s3.manager.S3ManagerDevelopment
import aws.sdk.kotlin.services.s3.model.DeleteBucketRequest
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import java.util.*

class S3ManagerTest : FunSpec({
    lateinit var s3Manager: S3Manager
    lateinit var bucketName: String

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

    beforeTest {
        bucketName = UUID.randomUUID().toString()
        val env = fakeEnv()
        s3Manager = S3ManagerDevelopment(env)
    }

    context("Bucket function") {
        test("should be created successfully") {
            shouldNotThrow<Exception> {
                s3Manager.initialize()
            }
        }
    }
    afterEach {
        runBlocking {
            val s3Client = s3Manager.getClient()
            s3Client.deleteBucket(DeleteBucketRequest { bucket = bucketName })
            s3Client.close()
        }
    }

})