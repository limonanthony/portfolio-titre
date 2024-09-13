package api.infrastructure.libraries.jwt.exceptions

import api.app.exceptions.ExceptionWithStatusCode
import api.infrastructure.server.status.HttpStatusCode

class InvalidTokenException :
    ExceptionWithStatusCode("The token is invalid.", httpStatusCode = HttpStatusCode.UNAUTHORIZED)