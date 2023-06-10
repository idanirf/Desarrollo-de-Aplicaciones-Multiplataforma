package repositories.report

import controllers.HibernateManager
import controllers.HibernateManager.manager
import models.Report

class ReportRepositoryImpl: IReportRepository {
    override fun create(t: Report): Report {
        var report: Report = t
        HibernateManager.transaction {
            manager.persist(t)
            report = manager.find(Report::class.java, t.uuidReport)
        }
        return report
    }

    override fun update(t: Report): Boolean {
        var res = false
        HibernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Report): Boolean {
        var res = false
        HibernateManager.transaction {
            val report = manager.find(Report::class.java, t.uuidReport)
            report.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

    override fun getById(id: String?): Report? {
        var res: Report? = null
        HibernateManager.transaction {
            res = manager.find(Report::class.java, id)
        }
        return res
    }

    override fun getAll(): List<Report> {
        var report = mutableListOf<Report>()
        HibernateManager.transaction {
            val query = manager.createNamedQuery("Report.findAll",Report::class.java)
            report = query.resultList
        }
        return report
    }
}