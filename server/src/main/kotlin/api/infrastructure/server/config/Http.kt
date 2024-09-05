package api.infrastructure.server.config

import api.infrastructure.env.Env
import api.infrastructure.env.enums.EnvironmentType
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureHttp(env: Env) {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        if (env.application.environment == EnvironmentType.DEVELOPMENT) {
            anyHost()
        }
    }
}