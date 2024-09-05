package api.infrastructure.server.utils

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun handleError(call: ApplicationCall, block: suspend ApplicationCall.() -> Unit) {
    try {
        block(call)
    } catch (e: Exception) {
        println(e.toString())
        call.respond(HttpStatusCode.InternalServerError, e.message ?: "Unknown error")
    }
}