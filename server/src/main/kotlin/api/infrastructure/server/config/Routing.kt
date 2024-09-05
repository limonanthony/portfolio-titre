package api.infrastructure.server.config

import api.infrastructure.libraries.di.DependencyInjections
import api.infrastructure.server.routing.authRoutes
import api.infrastructure.server.routing.reviewRoutes
import api.infrastructure.server.routing.userRoutes
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.configureRoutes(di: DependencyInjections) {
    routing {
        get {
            call.response.status(HttpStatusCode.OK)
            call.respond("Ok")
        }
        authRoutes(di.instance.get())
        reviewRoutes(di.instance.get())
        userRoutes(di.instance.get())
    }
}