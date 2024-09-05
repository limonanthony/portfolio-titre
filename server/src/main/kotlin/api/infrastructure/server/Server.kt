package api.infrastructure.server

import api.infrastructure.env.Env
import api.infrastructure.libraries.di.DependencyInjections
import api.infrastructure.server.config.configureContentNegotiation
import api.infrastructure.server.config.configureHttp
import api.infrastructure.server.config.configureRoutes
import api.infrastructure.server.config.configureValidation
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

class Server(
    private val di: DependencyInjections
) {
    fun start() {
        val env: Env = di.instance.get()
        embeddedServer(Netty, port = env.api.port, host = env.api.host) {
            module(di)
            println("Server started on address http://${env.api.host}:${env.api.port}")
        }.start(wait = true)
    }
}

fun Application.module(di: DependencyInjections) {
    configureHttp(di.instance.get())
    configureContentNegotiation()
    configureRoutes(di)
    configureValidation()
}
