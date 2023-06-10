package es.drodriguez.itvspringmongodb.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("owners")
data class Owner(
    @Id
    val id: ObjectId = ObjectId.get(),
    var dni: String? = null,
    var name: String? = null,
    var surnames: String? = null,
    var telf: String? = null
) {
    override fun toString(): String {
        return "Owner(Id= $id, Dni=$dni, name=$name, surnames=$surnames, telf=$telf)"
    }
}