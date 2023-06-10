package db

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

object MongoDbManager {
    lateinit var mongoClient: MongoClient
    lateinit var database: MongoDatabase

    init {
        mongoClient = KMongo.createClient("mongodb://mongoadmin:mongopass@localhost/tenistas?authSource=admin")
        database = mongoClient.getDatabase("itv")
    }
}