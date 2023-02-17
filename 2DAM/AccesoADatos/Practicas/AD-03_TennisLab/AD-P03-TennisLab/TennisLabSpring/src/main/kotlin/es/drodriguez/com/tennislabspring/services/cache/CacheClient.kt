package es.drodriguez.com.tennislabspring.services.cache

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import database.AppDatabase
import org.springframework.stereotype.Service

@Service
object CacheClient {
    private val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

    init {
        AppDatabase.Schema.create(driver)
    }

    val queries = AppDatabase(driver).appDatabaseQueries

    fun removeAllData() {
        queries.transaction {
            queries.removeAllUsers()
        }
    }
}