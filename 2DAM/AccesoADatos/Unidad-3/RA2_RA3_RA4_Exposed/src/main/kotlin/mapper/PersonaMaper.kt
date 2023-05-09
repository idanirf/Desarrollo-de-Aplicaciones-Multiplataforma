package mapper

import entities.PersonasDao
import models.Persona

fun PersonasDao.toPersona(): Persona {
    return Persona(
        id = this.id.value,
        nombre = this.nombre,
        fechaCarntet = this.fechaCarnet
    )
}