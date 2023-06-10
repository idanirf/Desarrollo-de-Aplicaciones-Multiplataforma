package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import java.time.LocalDate

class Report(
    @BsonId
    val id: String = newId<Report>().toString(),
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