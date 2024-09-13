package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

class NotFoundException(entity: String) :
    ExceptionWithStatusCode("$entity not found.", httpStatusCode = HttpStatusCode.NOT_FOUND)