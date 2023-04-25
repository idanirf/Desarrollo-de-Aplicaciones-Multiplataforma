package services

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import database.AppDatabase

object SqlDelightClient {


    private val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    val queries = AppDatabase(driver).appDatabaseQueries

    init {
        AppDatabase.Schema.create(driver)
    }
}