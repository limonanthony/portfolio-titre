package api.infrastructure.server.utils

import api.app.exceptions.ExceptionWithStatusCode
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun handleError(call: ApplicationCall, block: suspend ApplicationCall.() -> Unit) {
    try {
        block(call)
    } catch (e: ExceptionWithStatusCode) {
        println(e.toString())
        val statusCode = HttpStatusCode.fromValue(e.httpStatusCode.value)
        call.respond(statusCode, e.message ?: "Unknown error")
    }
}