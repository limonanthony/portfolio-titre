package api.infrastructure.libraries.di.modules

import api.infrastructure.env.*
import api.infrastructure.env.enums.EnvKey
import api.infrastructure.env.enums.EnvironmentType
import org.koin.dsl.module

val envModule = module {
    single {
        EnvApi(
            host = getRequiredEnv(EnvKey.API_HOST),
            port = getRequiredEnv(EnvKey.API_PORT).toInt()
        )
    }

    single {
        EnvApplication(
            environment = EnvironmentType.fromValue(getRequiredEnv(EnvKey.APPLICATION_ENVIRONMENT)),
        )
    }

    single {
        EnvJwt(
            secret = getRequiredEnv(EnvKey.JWT_SECRET),
            refreshExpiration = getRequiredEnv(EnvKey.JWT_REFRESH_EXPIRATION).toInt(),
            accessExpiration = getRequiredEnv(EnvKey.JWT_ACCESS_EXPIRATION).toInt(),
        )
    }

    single {
        EnvDatabase(
            host = getRequiredEnv(EnvKey.DATABASE_HOST),
            port = getRequiredEnv(EnvKey.DATABASE_PORT).toInt(),
            name = getRequiredEnv(EnvKey.DATABASE_NAME),
            user = getRequiredEnv(EnvKey.DATABASE_USER),
            password = getRequiredEnv(EnvKey.DATABASE_PASSWORD),
        )
    }

    single {
        EnvS3(
            accessKey = getRequiredEnv(EnvKey.S3_ACCESS_KEY),
            secretKey = getRequiredEnv(EnvKey.S3_SECRET_KEY),
            bucketName = getRequiredEnv(EnvKey.S3_BUCKET_NAME),
            region = getRequiredEnv(EnvKey.S3_REGION),
            endpointUrl = getEnv(EnvKey.S3_ENDPOINT_URL),
            endpointPort = getEnv(EnvKey.S3_ENDPOINT_PORT)?.toInt()
        )
    }

    single {
        Env(get(), get(), get(), get(), get())
    }
}