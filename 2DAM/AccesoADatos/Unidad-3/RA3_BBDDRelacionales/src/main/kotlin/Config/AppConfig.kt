package Config

import java.io.Closeable
import java.io.FileInputStream
import java.util.*

object AppConfig {
    lateinit var databaseUrl : String
    lateinit var databaseUser: String
    lateinit var databasePassword: String
    lateinit var pathFilesCsv: String
    lateinit var dataBaseDropTables : String


    init {
        initConfig()
    }
    fun initConfig() {
        var propertiesFile = ClassLoader.getSystemResource("config.properties").file
        println(propertiesFile.toString())

        var properties = Properties()
        properties.load(FileInputStream(propertiesFile))
        databaseUrl = properties.getProperty("dbUrl")
        databaseUser = properties.getProperty("dbUser")
        databasePassword = properties.getProperty("dbPassword")
        pathFilesCsv = properties.getProperty("csvInputDir")
        dataBaseDropTables = properties.getProperty("dataBaseDropTables")
        }
    }
