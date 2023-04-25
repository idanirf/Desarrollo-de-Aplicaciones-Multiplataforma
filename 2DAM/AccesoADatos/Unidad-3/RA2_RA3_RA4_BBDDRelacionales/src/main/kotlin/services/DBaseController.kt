package services

import Config.AppConfig
import java.sql.DriverManager

object DBaseController {
    val db get() = DriverManager.getConnection(AppConfig.databaseUrl)

    init {
        if(AppConfig.dataBaseDropTables.toBoolean()){
            println("Borrar toda la tabla, ... Borrando")
            dropTable()
        }
        println("Leyendo datos y creando tabla")
        createTables()
    }
    fun dropTable(){
        val sql = "DROP TABLE IF EXISTS vehiculos"
        db.use {
            it.createStatement().use { s ->
                s.executeUpdate(sql)
            }
        }
    }

    fun createTables(){
        val sql = """CREATE TABLE IF NOT EXISTS vehiculos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            uuid TEXT UNIQUE,
            marca TEXT,
            modelo TEXT,
            matricula TEXT,
            fechaMatriculacion TEXT,
            tipoMotor TEXT,
            createdAt TEXT,
            updatedAt TEXT,
            deleted TEXT
            )"""
        db.use {
            it.createStatement().use { s ->
                s.executeUpdate(sql)
            }
        }
    }
}