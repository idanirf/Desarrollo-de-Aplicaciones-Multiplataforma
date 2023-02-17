package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import models.User

import java.time.LocalDate
import java.util.*


@Serializable
data class AsignacionesEncordadores(
    val idPedido: String,
    val encordador_id: String,
    @Contextual
    val fecha: String? = LocalDate.now().toString()
) {
}