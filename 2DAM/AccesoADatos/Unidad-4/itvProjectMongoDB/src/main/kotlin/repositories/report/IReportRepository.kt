package repositories.report

import models.Report
import repositories.ICrudRepository

interface IReportRepository: ICrudRepository<Report, String> {
}