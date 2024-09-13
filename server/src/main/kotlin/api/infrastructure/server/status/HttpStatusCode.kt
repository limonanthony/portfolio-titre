package api.infrastructure.server.status

enum class HttpStatusCode(val value: Int) {
    CONFLICT(io.ktor.http.HttpStatusCode.Conflict.value),
    CREATED(io.ktor.http.HttpStatusCode.Created.value),
    BAD_REQUEST(io.ktor.http.HttpStatusCode.BadRequest.value),
    INTERNAL_SERVER_ERROR(io.ktor.http.HttpStatusCode.InternalServerError.value),
    NO_CONTENT(io.ktor.http.HttpStatusCode.NoContent.value),
    NOT_FOUND(io.ktor.http.HttpStatusCode.NotFound.value),
    OK(io.ktor.http.HttpStatusCode.OK.value),
    UNAUTHORIZED(io.ktor.http.HttpStatusCode.Unauthorized.value), ;

    companion object {
        fun fromCode(code: Int): HttpStatusCode? {
            return entries.find { it.value == code }
        }
    }
}