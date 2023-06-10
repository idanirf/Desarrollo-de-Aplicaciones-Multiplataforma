package es.drodriguez.itvh2hibernatespringv2.models

import jakarta.persistence.*

@Entity
@Table(name="report")
class Report(
    @Id
    var uuidReport: String? = null,
    var competent: Boolean? = null,
    var braking: Double? = null,
    var pollution: Double? = null,
    var brakingCompetent: Boolean? = null,
    var lightsCompetent: Boolean? = null
) {
    override fun toString(): String {
        return "Report(uuidReport=$uuidReport, competent=$competent, braking=$braking, pollution=$pollution, brakingCompetent=$brakingCompetent, lightsCompetent=$lightsCompetent)"
    }
}