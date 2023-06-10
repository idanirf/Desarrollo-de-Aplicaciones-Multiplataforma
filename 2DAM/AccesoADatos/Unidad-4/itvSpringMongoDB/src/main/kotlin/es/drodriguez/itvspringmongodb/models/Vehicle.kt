package es.drodriguez.itvspringmongodb.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
@Document("vehicles")
data class Vehicle(
    @Id
    val id: ObjectId = ObjectId.get(),
    var brand: String? = null,
    var model: String? = null,
    var licensePlate: String? = null,
    var dateLicensePlate: LocalDate? = null,
    var lastRevisionDate: String? = null,
    var owner: Owner? = null
) {
    override fun toString(): String {
        return "Vehicle(Id=$id, Brand=$brand, model=$model, licensePlate=$licensePlate, dateLicensePlate=$dateLicensePlate, lastRevisionDate=$lastRevisionDate, owner=$owner)"
    }
}