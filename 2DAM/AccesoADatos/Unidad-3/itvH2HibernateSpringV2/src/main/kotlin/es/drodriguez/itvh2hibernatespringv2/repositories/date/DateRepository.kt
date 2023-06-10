package es.drodriguez.itvh2hibernatespringv2.repositories.date

import es.drodriguez.itvh2hibernatespringv2.models.Date
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DateRepository:JpaRepository<Date, String> {
}