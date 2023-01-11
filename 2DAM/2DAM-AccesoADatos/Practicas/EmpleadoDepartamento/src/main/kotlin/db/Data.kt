package db

import models.Departamento
import java.util.*

/*
fun getEmpleadoInit() = listOf(
    Empleado(
        id = UUID.randomUUID(), nombre = "Juan", fechaNacimiento = LocalDate.parse("1990-01-01")
    ),
    Empleado(
        id = UUID.randomUUID(), nombre = "Pedro", fechaNacimiento = LocalDate.parse("1991-01-01"),
    ),
    Empleado(
        id = UUID.randomUUID(), nombre = "Maria", fechaNacimiento = LocalDate.parse("1992-01-01")
    ),
)*/

fun getDepartamentoInit() = listOf(
    Departamento(
        id = UUID.randomUUID(), nombre = "Departamento 1", presupuesto = 1000.0
    ),
    Departamento(
        id = UUID.randomUUID(), nombre = "Departamento 2", presupuesto = 2000.0
    ),
    Departamento(
        id = UUID.randomUUID(), nombre = "Departamento 3", presupuesto = 3000.0
    )
)