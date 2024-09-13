package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

class ConflictException(
    entity: String,
    value: String,
) : ExceptionWithStatusCode(
    message = "The $entity $value already exists",
    httpStatusCode = HttpStatusCode.INTERNAL_SERVER_ERROR
)