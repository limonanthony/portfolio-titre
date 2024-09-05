package api.infrastructure.server.utils

import io.ktor.server.request.*

fun getBearerToken(request: ApplicationRequest): String? {
    return request.header("Authorization")?.substringAfter("Bearer ")
}