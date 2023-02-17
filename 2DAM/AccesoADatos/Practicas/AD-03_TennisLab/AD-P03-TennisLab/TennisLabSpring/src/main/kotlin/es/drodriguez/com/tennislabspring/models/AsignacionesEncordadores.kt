package es.drodriguez.com.tennislabspring.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

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