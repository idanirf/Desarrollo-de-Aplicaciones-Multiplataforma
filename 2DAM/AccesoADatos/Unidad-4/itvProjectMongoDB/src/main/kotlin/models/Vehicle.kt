package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import java.time.LocalDate

class Vehicle(
    @BsonId
    val id: String = newId<Vehicle>().toString(),
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