package models

import javax.persistence.Embeddable

@Embeddable
data class Persona(
    var nombre: String? = "",
    var email: String? ="",
    var fechaCarnet: String? = ""
)
