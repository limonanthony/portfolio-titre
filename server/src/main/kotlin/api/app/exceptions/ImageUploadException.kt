package api.app.exceptions

import api.infrastructure.server.status.HttpStatusCode

class ImageUploadException(path: String) :
    ExceptionWithStatusCode(
        "There was an issue with the upload of the image with the path $path",
        HttpStatusCode.INTERNAL_SERVER_ERROR
    )