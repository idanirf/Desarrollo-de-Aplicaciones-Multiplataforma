package Config

import java.io.Closeable
import java.io.FileInputStream
import java.util.*

object AppConfig {
    private lateinit var serverUrl: String
    private lateinit var serverPort: String
    private lateinit var dataBaseName: String
    private lateinit var initScript: String
    private lateinit var jdbcDriver: String
    private lateinit var connectionUrl: String
    init {
        initConfig()
    }
    fun initConfig() {
        val propsFile = ClassLoader.getSystemResource("config.properties").file
        propsFile?.let {
            val props = Properties()
            props.load(FileInputStream(propsFile))
            serverUrl = props.getProperty("database.url", "localhost")
            serverPort = props.getProperty("database.port", "3306")
            dataBaseName = props.getProperty("database.name", "Vehiculos")
            jdbcDriver = props.getProperty("database.driver", "org.sqlite.Driver")

            connectionUrl =
                props.getProperty("database.connectionUrl", "jdbc:sqlite:mem:${this.dataBaseName};DB_CLOSE_DELAY=-1")
            initScript = props.getProperty("database.initScript", ClassLoader.getSystemResource("init.sql").file)
        }
    }
}