package dto

import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDto,
    val phone: String,
    val website: String,
    val company: CompanyDto
)
