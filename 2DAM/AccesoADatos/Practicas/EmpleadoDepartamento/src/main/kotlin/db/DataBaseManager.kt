package db

import java.sql.*

object DataBaseManager {
    // Parámetros de conexión
    private lateinit var serverUrl: String
    private lateinit var serverPort: String
    private lateinit var dataBaseName: String
    private lateinit var user: String
    private lateinit var password: String

    /*
    Tipos de Driver
    SQLite: "org.sqlite.JDBC";
    MySQL: "com.mysql.jdbc.Driver"
    MariaDB: "com.mysql.cj.jdbc.Driver"
    PostgreSQL: "org.postgresql.Driver"
    H2: "org.h2.Driver"
    */
    private var jdbcDriver: String = "org.h2.Driver"

    // Para manejar las conexiones y respuestas de las mismas
    private var connection: Connection? = null
    private var preparedStatement: PreparedStatement? = null


    init {
        initConfig()
    }

    /**
     * Carga la configuración de acceso al servidor de Base de Datos
     * Puede ser directa "hardcodeada" o asignada dinámicamente a traves de ficheros .env o properties
     */
    private fun initConfig() {
        // Carga de configuración de acceso a la Base de Datos
        serverUrl = "localhost"
        serverPort = "3306"
        dataBaseName = "tenistas"
        jdbcDriver = "org.h2.Driver"
        user = "blog"
        password = "blog1234"

        //logger.debug { "Configuración de acceso a la Base de Datos cargada" }
    }

    /**
     * Establece la conexión con la Base de Datos
     * @return true si la conexión se ha establecido correctamente, false en caso contrario
     */
    @Throws(SQLException::class)
    fun open() {
        //String url = "jdbc:sqlite:"+this.ruta+this.bbdd;
        // MySQL jdbc:mysql://localhost/prueba", "root", "1dam"
        // val url = "jdbc:mariadb://" + serverUrl + ":" + serverPort + "/" + dataBaseName
        val url = "jdbc:h2:mem:${this.dataBaseName};DB_CLOSE_DELAY=-1"
        // Obtenemos la conexión
        connection = DriverManager.getConnection(url, user, password)
        //logger.debug { "Conexión a la Base de Datos establecida: $url" }
    }

    /**
     * Cierra la conexión con el servidor de base de datos
     *
     * @throws SQLException Servidor no responde o no puede realizar la operación de cierre
     */
    @Throws(SQLException::class)
    fun close() {
        preparedStatement?.close()
        connection?.close()
        //logger.debug { "Conexión a la Base de Datos cerrada" }
    }

    /**
     * Realiza una consulta a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    @Throws(SQLException::class)
    private fun executeQuery(querySQL: String, vararg params: Any?): ResultSet? {
        preparedStatement = connection?.prepareStatement(querySQL)
        // Si hay parámetros, los asignamos
        return preparedStatement?.let {
            for (i in params.indices) {
                it.setObject(i + 1, params[i])
            }
            //logger.debug { "Ejecutando consulta: $querySQL con parámetros: ${params.contentToString()}" }
            it.executeQuery()
        }
    }

    /**
     * Realiza una consulta select a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe
     */
    @Throws(SQLException::class)
    fun select(querySQL: String, vararg params: Any?): ResultSet? {
        return executeQuery(querySQL, *params)
    }

    /**
     * Realiza una consulta select a la base de datos de manera "preparada" obteniendo los parametros opcionales si son necesarios
     *
     * @param querySQL consulta SQL de tipo select
     * @param limit    número de registros de la página
     * @param offset   desplazamiento de registros o número de registros ignorados para comenzar la devolución
     * @param params   parámetros de la consulta parametrizada
     * @return ResultSet de la consulta
     * @throws SQLException No se ha podido realizar la consulta o la tabla no existe o el desplazamiento es mayor que el número de registros
     */
    @Throws(SQLException::class)
    fun select(querySQL: String, limit: Int, offset: Int, vararg params: Any?): ResultSet? {
        val query = "$querySQL LIMIT $limit OFFSET $offset"
        return executeQuery(query, *params)
    }

    /**
     * Realiza una consulta de tipo insert de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param insertSQL consulta SQL de tipo insert
     * @param params    parámetros de la consulta parametrizada
     * @return Clave del registro insertado si es autonumerico
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    /* @Throws(SQLException::class)
     fun insert(insertSQL: String, vararg params: Any?): ResultSet? {
         // Con return generated keys obtenemos las claves generadas las claves es autonumerica por ejemplo,
         // el id de la tabla si es autonumérico. Si no quitar.
         preparedStatement = connection?.prepareStatement(insertSQL, *//*Statement.RETURN_GENERATED_KEYS*//*)
        // Si hay parámetros, los asignamos
        return preparedStatement?.let {
            for (i in params.indices) {
                it.setObject(i + 1, params[i])
            }
            logger.info { "Ejecutando consulta: $insertSQL con parámetros: ${params.contentToString()}" }
            it.executeUpdate()
            it.generatedKeys
        }
    }*/

    // USO esta función porque los UUID los genero desde la propia aplicación
    @Throws(SQLException::class)
    fun insert(insertSQL: String, vararg params: Any?): Int {
        return updateQuery(insertSQL, *params)
    }

    /**
     * Realiza una consulta de tipo update de manera "preparada" con los parametros opcionales si son necesarios
     *
     * @param updateSQL consulta SQL de tipo update
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros actualizados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    fun update(updateSQL: String, vararg params: Any?): Int {
        return updateQuery(updateSQL, *params)
    }

    /**
     * Realiza una consulta de tipo delete de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param deleteSQL consulta SQL de tipo delete
     * @param params    parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    fun delete(deleteSQL: String, vararg params: Any?): Int {
        return updateQuery(deleteSQL, *params)
    }

    /**
     * Realiza una consulta de tipo update, es decir que modifca los datos, de manera "preparada" con los parametros opcionales si son encesarios
     *
     * @param genericSQL consulta SQL de tipo update, delete, creted, etc.. que modifica los datos
     * @param params     parámetros de la consulta parametrizada
     * @return número de registros eliminados
     * @throws SQLException tabla no existe o no se ha podido realizar la operación
     */
    @Throws(SQLException::class)
    private fun updateQuery(genericSQL: String, vararg params: Any?): Int {
        // Con return generated keys obtenemos las claves generadas
        preparedStatement = connection?.prepareStatement(genericSQL)
        // Si hay parámetros, los asignamos
        return preparedStatement?.let {
            for (i in params.indices) {
                preparedStatement!!.setObject(i + 1, params[i])
            }
            //logger.debug { "Ejecutando consulta: $genericSQL con parámetros: ${params.contentToString()}" }
            it.executeUpdate()
        } ?: 0

    }

    fun createTables(genericSQL: String): Int {
        //logger.debug { "Creando Tablas..." }
        return updateQuery(genericSQL)
    }
}