package models

import java.time.LocalDate
import java.time.LocalTime


data class Aemet(
    val dia: LocalDate?,
    val localidad: String?,
    val provincia: String?,
    var temperaturaMax: Double?,
    val horaRegistroTempMac: LocalTime?,
    var temperaturaMin: Double?,
    val horaRegistroTempMin: LocalTime?,
    val precipitacion: Double?
)

