package dto

import kotlinx.serialization.Serializable

@Serializable
data class TareaDto(
    val userId: String,
    val id: String,
    val title: String,
    val completed: String
) {
}