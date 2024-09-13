package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

class ImageDeletionException(
    path: String,
) : ExceptionWithStatusCode(
    "The image with path $path could not be deleted",
    httpStatusCode = HttpStatusCode.INTERNAL_SERVER_ERROR
)