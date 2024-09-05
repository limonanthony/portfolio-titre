package api.app.entities.enums

enum class UserType(val value: String) {
    ADMIN("admin"),
    USER("user");

    companion object {
        fun fromValue(value: String): UserType {
            return entries.find { it.value == value }
                ?: throw IllegalArgumentException("No enum constant for value: $value")
        }
    }
}