package repositories.date

import models.Date
import repositories.ICrudRepository

interface IDateRepository : ICrudRepository<Long, Date> {
    fun getAppointmentsByEmployeeId(employeeId: String): List<Date>

}