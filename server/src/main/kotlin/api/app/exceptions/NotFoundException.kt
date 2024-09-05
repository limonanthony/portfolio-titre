package api.app.exceptions

class NotFoundException(entity: String) : Exception("$entity not found.")