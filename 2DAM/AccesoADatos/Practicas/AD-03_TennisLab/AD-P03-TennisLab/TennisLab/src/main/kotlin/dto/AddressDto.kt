package dto

import kotlinx.serialization.Serializable


@Serializable
data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoDto
){
}