package api.app.exceptions

class ConflictException(entity: String, value: String) : Exception("The $entity $value already exists")