import db.DataBaseManager
import db.DataBaseManager.createTables
import db.createTables

fun main(args: Array<String>) {
    println("EMPLEADOS - DEPARTAMENTO | BBDD RELACIONAL")
    println("===========================================")
    initDataBase()


}

/*
 * Función para iniciar la base de datos
 */

fun initDataBase() {
    DataBaseManager.open()
    createTables(createTables()) //TODO
    DataBaseManager.close()
}