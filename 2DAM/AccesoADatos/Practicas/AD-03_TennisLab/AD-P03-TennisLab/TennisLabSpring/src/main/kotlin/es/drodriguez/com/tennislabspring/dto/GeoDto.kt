package dto

import kotlinx.serialization.Serializable


@Serializable
data class GeoDto(
    val lat: String,
    val lng: String
){
}
