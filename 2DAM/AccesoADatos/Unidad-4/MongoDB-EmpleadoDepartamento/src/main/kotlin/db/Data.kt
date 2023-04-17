package db

import models.Departamento
import models.Empleado
import java.time.LocalDate

fun loadDataEmpleados() = listOf(
    Empleado(nombre="Juan", apellidos = "García", fechaAlta = LocalDate.of(2020, 1, 1)),
    Empleado(nombre="Daniel", apellidos = "Rodríguez", fechaAlta = LocalDate.of(2020, 1, 1))
)

fun loadDataDepartamentos() = listOf(
    Departamento(nombre = "Informática"),
    Departamento(nombre = "Ventas")
)