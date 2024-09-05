package api.app.exceptions

class ImageUploadException(path: String) :
    Exception("There was an issue with the upload of the image with the path $path")