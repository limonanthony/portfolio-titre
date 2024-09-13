package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

class InvalidCredentialsException :
    ExceptionWithStatusCode("Invalid credentials.", httpStatusCode = HttpStatusCode.UNAUTHORIZED)