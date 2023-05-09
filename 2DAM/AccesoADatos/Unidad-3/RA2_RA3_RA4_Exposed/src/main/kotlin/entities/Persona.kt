package entities

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object PersonaTable: LongIdTable(){
    var nombre = varchar("nombre", 50)
    var fechaCarnet = varchar("fecha_carnet", 50)
}

class PersonasDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PersonasDao>(PersonaTable) {

    }

    var nombre by PersonaTable.nombre
    var fechaCarnet by PersonaTable.fechaCarnet
}


