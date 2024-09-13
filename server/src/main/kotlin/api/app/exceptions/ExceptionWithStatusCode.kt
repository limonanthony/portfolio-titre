package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

open class ExceptionWithStatusCode(
    message: String,
    val httpStatusCode: HttpStatusCode,
) : Exception(message)