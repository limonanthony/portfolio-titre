package api.infrastructure.server.config

import api.infrastructure.env.Env
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureHttp(env: Env) {
    install(CORS) {
        allowHeader(HttpHeaders.Authorization)

        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Accept)

        HttpMethod.DefaultMethods.forEach { method ->
            allowMethod(method)
        }
        anyHost()
    }
}