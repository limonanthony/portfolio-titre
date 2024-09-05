package api.infrastructure.libraries.di.modules

import api.infrastructure.env.*
import api.infrastructure.env.enums.EnvKey
import api.infrastructure.env.enums.EnvironmentType
import org.koin.dsl.module

val envModule = module {
    single {
        try {
            println("API_HOST ${System.getenv("API_HOST")}")
            EnvApi(
                host = getRequiredEnv(EnvKey.API_HOST),
                port = getRequiredEnv(EnvKey.API_PORT).toInt()
            )
        } catch (e: Exception) {
            println("Error")
            println(e.cause.toString())
            println(e.stackTraceToString())
            println(e.message)
            throw e
        }
    }

    single {
        try {
            EnvApplication(
                environment = EnvironmentType.fromValue(getRequiredEnv(EnvKey.APPLICATION_ENVIRONMENT)),
            )
        } catch (e: Exception) {
            println("Error")
            println(e.message)
            throw e
        }
    }

    single {
        try {
            EnvJwt(
                secret = getRequiredEnv(EnvKey.JWT_SECRET),
                refreshExpiration = getRequiredEnv(EnvKey.JWT_REFRESH_EXPIRATION).toInt(),
                accessExpiration = getRequiredEnv(EnvKey.JWT_ACCESS_EXPIRATION).toInt(),
            )
        } catch (e: Exception) {
            println("Error")
            println(e.message)
            throw e
        }
    }

    single {
        try {
            EnvDatabase(
                host = getRequiredEnv(EnvKey.DATABASE_HOST),
                port = getRequiredEnv(EnvKey.DATABASE_PORT).toInt(),
                name = getRequiredEnv(EnvKey.DATABASE_NAME),
                user = getRequiredEnv(EnvKey.DATABASE_USER),
                password = getRequiredEnv(EnvKey.DATABASE_PASSWORD),
            )
        } catch (e: Exception) {
            println("Error")
            println(e.message)
            throw e
        }
    }

    single {
        try {
            EnvS3(
                accessKey = getRequiredEnv(EnvKey.S3_ACCESS_KEY),
                secretKey = getRequiredEnv(EnvKey.S3_SECRET_KEY),
                bucketName = getRequiredEnv(EnvKey.S3_BUCKET_NAME),
                region = getRequiredEnv(EnvKey.S3_REGION),
                endpointUrl = getEnv(EnvKey.S3_ENDPOINT_URL),
                endpointPort = getEnv(EnvKey.S3_ENDPOINT_PORT)?.toInt()
            )
        } catch (e: Exception) {
            println("Error")
            println(e.message)
            throw e
        }
    }

    single {
        try {
            Env(get(), get(), get(), get(), get())
        } catch (e: Exception) {
            println("Error")
            println(e.message)
            throw e
        }
    }
}