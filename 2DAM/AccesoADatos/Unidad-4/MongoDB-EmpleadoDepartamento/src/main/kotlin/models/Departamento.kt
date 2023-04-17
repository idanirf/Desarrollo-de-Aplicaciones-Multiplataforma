package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class Departamento(
    @BsonId @Contextual
    val id: Id<Departamento> = newId<Departamento>(),
    var nombre: String,
) {
}