package api.infrastructure.env.enums

enum class EnvironmentType(val value: String) {
    DEVELOPMENT("development"),
    PRODUCTION("production");

    companion object {
        fun fromValue(value: String): EnvironmentType {
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("Unknown environment type: $value")
        }
    }
}