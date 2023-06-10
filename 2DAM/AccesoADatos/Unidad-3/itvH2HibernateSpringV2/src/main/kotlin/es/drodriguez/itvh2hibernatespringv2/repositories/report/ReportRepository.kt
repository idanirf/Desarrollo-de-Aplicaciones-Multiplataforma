package es.drodriguez.itvh2hibernatespringv2.repositories.report

import es.drodriguez.itvh2hibernatespringv2.models.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository:JpaRepository<Report, String> {
}