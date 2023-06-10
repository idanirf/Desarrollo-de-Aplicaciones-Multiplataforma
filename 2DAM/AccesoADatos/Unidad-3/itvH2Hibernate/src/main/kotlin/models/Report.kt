package models

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery

@Entity
@NamedQuery(name = "Report.findAll", query = "SELECT t FROM Report t")
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