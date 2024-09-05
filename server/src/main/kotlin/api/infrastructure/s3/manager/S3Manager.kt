package api.infrastructure.s3.manager

import api.infrastructure.env.Env
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.CreateBucketRequest
import aws.sdk.kotlin.services.s3.model.HeadBucketRequest
import aws.sdk.kotlin.services.s3.model.S3Exception
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

abstract class S3Manager(protected val env: Env) {
    val bucketName = env.s3.bucketName

    fun initialize() {
        runBlocking {
            initializeBucket()
        }
    }

    abstract fun getClient(): S3Client

    private suspend fun initializeBucket() = coroutineScope {
        try {
            if (shouldBucketBeCreated()) {
                createBucket()
            }
        } catch (e: S3Exception) {
            handleException(e)
        }
    }

    private suspend fun shouldBucketBeCreated(): Boolean = coroutineScope {
        val s3Client = getClient()
        try {
            val headBucketRequest = HeadBucketRequest { bucket = bucketName }
            s3Client.headBucket(headBucketRequest)
            false
        } catch (e: S3Exception) {
            if (e.sdkErrorMetadata.errorCode == "NotFound") {
                true
            } else {
                throw e
            }
        } finally {
            s3Client.close()
        }
    }

    private suspend fun createBucket() = coroutineScope {
        val s3Client = getClient()
        try {
            val createBucketRequest = CreateBucketRequest { bucket = bucketName }
            s3Client.createBucket(createBucketRequest)
        } catch (e: S3Exception) {
            throw e
        } finally {
            s3Client.close()
        }
    }

    private fun handleException(e: S3Exception) {
        println(e)
        throw e
    }
}