package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId

class Owner(
    @BsonId
    val id: String = newId<Owner>().toString(),
    var dni: String? = null,
    var name: String? = null,
    var surnames: String? = null,
    var telf: String? = null
) {
    override fun toString(): String {
        return "Owner(Id= $id, Dni=$dni, name=$name, surnames=$surnames, telf=$telf)"
    }
}