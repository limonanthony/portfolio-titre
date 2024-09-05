package api.infrastructure.env.exceptions

import api.infrastructure.env.enums.EnvKey

class MissingEnvException(key: EnvKey) : Exception("${key.key} is missing in environment variables.")