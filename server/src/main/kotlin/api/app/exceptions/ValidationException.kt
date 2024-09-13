package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

class ValidationException : ExceptionWithStatusCode("Error", httpStatusCode = HttpStatusCode.BAD_REQUEST)