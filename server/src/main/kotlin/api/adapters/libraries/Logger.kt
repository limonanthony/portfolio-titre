package api.adapters.libraries

interface Logger {
    fun info(message: String)
    fun error(message: String)
    fun warning(message: String)
}