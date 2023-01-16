package db

fun createTables() = """
        CREATE TABLE IF NOT EXISTS departamentos (
        id UUID PRIMARY KEY,
        nombre VARCHAR(255) NOT NULL,
        presupuesto DOUBLE NOT NULL
        )
    CREATE TABLE IF NOT EXISTS empleados (
        id UUID PRIMARY KEY,
        nombre VARCHAR(255) NOT NULL,
        fechaNacimiento VARCHAR(255) NOT NULL,
        departamento VARCHAR(255) NOT NULL
        )

    """.trimIndent()