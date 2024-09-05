package api.infrastructure.s3.manager

import api.infrastructure.env.Env
import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client

class S3ManagerProduction(env: Env) : S3Manager(env) {
    override fun getClient() = S3Client {
        region = env.s3.region
        credentialsProvider = StaticCredentialsProvider {
            accessKeyId = env.s3.accessKey
            secretAccessKey = env.s3.secretKey
        }
    }
}