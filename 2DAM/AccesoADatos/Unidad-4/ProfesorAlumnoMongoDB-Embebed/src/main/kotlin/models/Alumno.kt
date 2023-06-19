package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId

class Alumno(
    @BsonId
    val id:String = newId<Alumno>().toString(),
    val nombreCompleto: String? = null,
    var asignatura: Asignatura? = null,
){
    override fun toString(): String {
        return "Alumno(id='$id', nombreCompleto=$nombreCompleto, asignatura=$asignatura)"
    }
}