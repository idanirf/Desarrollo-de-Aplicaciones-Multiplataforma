package config

import java.io.FileInputStream
import java.util.*

// Configuracion leida del properties para nuestra App
data class ApplicationProperties(
    val nombre: String,
    val version: String,
    val jdbcUrl: String,
    val jdbcUserName: String,
    val jdbcPassword: String,
    val jdbcDriverClassName: String,
    val jdbcMaximumPoolSize: Int = 10,
    val jdbcCreateTables: Boolean = true,
    val jdbcshowSQL: Boolean = true,
) {
    companion object {
        val DEFAULT = ApplicationProperties(
            nombre = "app",
            version = "1.0.0",
            jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            jdbcUserName = "dani",
            jdbcPassword = "",
            jdbcDriverClassName = "org.h2.Driver",
            jdbcMaximumPoolSize = 10,
            jdbcCreateTables = true,
            jdbcshowSQL = true,
        )

        fun fromPropertiesFile(fileName: String): ApplicationProperties {
            val properties = Properties()
            properties.load(FileInputStream(fileName))
            return ApplicationProperties(
                nombre = properties.getProperty("nombre"),
                version = properties.getProperty("version"),
                jdbcUrl = properties.getProperty("jdbc.url"),
                jdbcUserName = properties.getProperty("jdbc.username"),
                jdbcPassword = properties.getProperty("jdbc.password"),
                jdbcDriverClassName = properties.getProperty("jdbc.driverClassName"),
                jdbcMaximumPoolSize = properties.getProperty("jdbc.maximumPoolSize").toInt(),
                jdbcCreateTables = properties.getProperty("jdbc.createTables").toBoolean(),
                jdbcshowSQL = properties.getProperty("jdbc.showSQL").toBoolean(),
            )
        }
    }
}