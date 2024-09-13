package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

class UnauthorizedException : ExceptionWithStatusCode("Unauthorized", httpStatusCode = HttpStatusCode.UNAUTHORIZED)