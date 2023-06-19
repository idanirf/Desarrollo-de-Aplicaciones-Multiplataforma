package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId

class Asignatura(
    @BsonId
    val id: String = newId<Asignatura>().toString(),
    val codModulo: String? = null,
    var nombreModulo: String? = null,
    var horasSemanales: Int? = null,

    ){
    override fun toString(): String {
        return "Asignatura(id='$id', codModulo=$codModulo, nombreModulo=$nombreModulo)"
    }
}

