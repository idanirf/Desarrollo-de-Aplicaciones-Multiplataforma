package services.cache

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import database.AppDatabase
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

private val logger = KotlinLogging.logger {}
@Single
@Named("CacheClient")
class CacheClient {
    private val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)

    init {
        logger.debug { "SqlDeLightClient.init() - Create Schemas" }
        AppDatabase.Schema.create(driver)
    }

    val queries = AppDatabase(driver).appDatabaseQueries

    fun removeAllData() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        queries.transaction {
            logger.debug { "SqlDeLightClient.removeAllData() " }
            queries.removeAllUsers()
        }
    }
}