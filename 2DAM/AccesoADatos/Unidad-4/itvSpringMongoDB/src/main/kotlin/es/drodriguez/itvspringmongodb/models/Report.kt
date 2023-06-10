package es.drodriguez.itvspringmongodb.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
@Document("reports")
data class Report(
    @Id
    val id: ObjectId = ObjectId.get(),
    var date: LocalDate? = null,
    var vehicle: Vehicle? = null,
    var employee: Employee? = null,
    var competent: Boolean? = null,
    var braking: Double? = null,
    var pollution: Double? = null,
    var brakingCompetent: Boolean? = null,
    var lightsCompetent: Boolean? = null
) {
    override fun toString(): String {
        return "Report(Id=$id ,date=$date, vehicle=$vehicle, employee=$employee, competent=$competent, braking=$braking, pollution=$pollution, brakingCompetent=$brakingCompetent, lightsCompetent=$lightsCompetent)"
    }
}