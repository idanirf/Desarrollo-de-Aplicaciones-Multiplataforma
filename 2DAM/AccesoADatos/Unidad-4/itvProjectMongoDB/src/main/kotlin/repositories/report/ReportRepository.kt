package repositories.report

import db.MongoDbManager
import models.Report
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection

class ReportRepository: IReportRepository {
    override fun findAll(): List<Report> {
        return MongoDbManager.database.getCollection<Report>().find().toList()
    }

    override fun findById(id: String): Report? {
        return MongoDbManager.database.getCollection<Report>().findOneById(id)
    }

    override fun save(entity: Report): Report {
        MongoDbManager.database.getCollection<Report>().insertOne(entity)
        return entity
    }

    override fun delete(entity: Report): Boolean {
        return MongoDbManager.database.getCollection<Report>().deleteOneById(entity.id).wasAcknowledged()
    }
}