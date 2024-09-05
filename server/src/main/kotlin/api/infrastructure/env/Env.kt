package api.infrastructure.env

import api.infrastructure.env.enums.EnvKey
import api.infrastructure.env.enums.EnvironmentType
import api.infrastructure.env.exceptions.MissingEnvException

data class Env(
    val application: EnvApplication,
    val api: EnvApi,
    val jwt: EnvJwt,
    val database: EnvDatabase,
    val s3: EnvS3,
)

data class EnvApplication(
    val environment: EnvironmentType
)

data class EnvApi(
    val host: String,
    val port: Int,
)

data class EnvJwt(
    val secret: String,
    val accessExpiration: Int,
    val refreshExpiration: Int,
)

data class EnvDatabase(
    val host: String,
    val port: Int,
    val name: String,
    val user: String,
    val password: String,
)

data class EnvS3(
    val accessKey: String,
    val secretKey: String,
    val region: String,
    val bucketName: String,
    val endpointUrl: String?,
    val endpointPort: Int?,
)

fun getEnv(key: EnvKey): String? = System.getenv(key.key)

fun getRequiredEnv(key: EnvKey): String = getEnv(key) ?: throw MissingEnvException(key)
