package api.infrastructure.database

import api.infrastructure.database.tables.ReviewsTable
import api.infrastructure.database.tables.UsersTable
import api.infrastructure.env.Env
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class Db(
    env: Env
) {
    val instance: Database

    init {
        instance = Database.connect(
            url = buildUrl(env),
            driver = "org.postgresql.Driver",
            user = env.database.user,
            password = env.database.password
        )

        buildTables()
    }

    private fun buildUrl(env: Env): String {
        return "jdbc:postgresql://${env.database.host}:${env.database.port}/${env.database.name}"
    }

    private fun buildTables() {
        transaction {
            SchemaUtils.create(ReviewsTable, UsersTable)
            println("Tables created")
        }
    }
}