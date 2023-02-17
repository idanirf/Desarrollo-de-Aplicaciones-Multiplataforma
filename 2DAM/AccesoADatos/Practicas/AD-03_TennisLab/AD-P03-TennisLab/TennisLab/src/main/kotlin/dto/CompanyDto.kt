package dto

import kotlinx.serialization.Serializable


@Serializable
data class CompanyDto(
    val name: String,
    val catchPhrase: String,
    val bs: String
){
}