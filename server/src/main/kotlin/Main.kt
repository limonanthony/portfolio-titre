import api.infrastructure.database.Db
import api.infrastructure.libraries.di.DependencyInjections
import api.infrastructure.server.Server

fun main() {
    try {
        val di = DependencyInjections()
        Db(di.instance.get())
        val server = Server(di)
        server.start()
    } catch (e: Exception) {
        println(e.message)
    }
}