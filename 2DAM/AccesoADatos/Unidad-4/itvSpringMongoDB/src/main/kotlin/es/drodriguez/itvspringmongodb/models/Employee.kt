package es.drodriguez.itvspringmongodb.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.Period
@Document("employees")
data class Employee(
    @Id
    val id: ObjectId = ObjectId.get(),
    var name: String? = null,
    var telf: String? = null,
    var email: String? = null,
    var username: String? = null,
    var password: String? = null,
    var dataHiring: LocalDate? = null,
    var speciality: Speciality = Speciality.ELECTRICITY,
    var responsible: Boolean? = null,
    var baseSalary: Int = speciality.salary ?: 0,
) {
    fun calculateYearsOfService(): Int {
        val currentDate = LocalDate.now()
        return Period.between(dataHiring, currentDate).years
    }

    fun calculateSalaryWithSeniority(): Int {
        val baseSalary = speciality.salary ?: 0
        val yearsOfService = calculateYearsOfService()
        val additionalSalary = (yearsOfService / 3) * 100
        return baseSalary + additionalSalary
    }

    override fun toString(): String {
        return "Employee(Id= $id, Name=$name, telf=$telf, email=$email, username=$username, password=$password, dataHiring=$dataHiring, speciality=$speciality)"
    }
}
enum class Speciality(val salary: Int) {
    ELECTRICITY(1800),
    ENGINE(1700),
    MECHANICS(1600),
    INSIDE(1750);
}