package api.app.entities

import api.infrastructure.env.Env
import api.infrastructure.env.enums.EnvironmentType
import java.util.*

data class Image(
    val id: UUID = UUID.randomUUID(),
    var path: String,
    var url: String = "",
) {
    fun buildUrl(env: Env) {
        val protocol = if (env.application.environment == EnvironmentType.DEVELOPMENT) "http" else "https"
        val host = env.api.host
        val port = env.api.port
        url = "$protocol://$host:$port/$path"
    }
}