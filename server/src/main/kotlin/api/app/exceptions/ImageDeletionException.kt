package api.app.exceptions

class ImageDeletionException(path: String) : Exception("The image with path $path could not be deleted")