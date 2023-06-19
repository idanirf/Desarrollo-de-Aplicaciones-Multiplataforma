package db

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

object MongoManager {
    lateinit var mongoClient: MongoClient
    lateinit var db: MongoDatabase

    init{
        mongoClient = KMongo.createClient("mongodb://mongoadmin:mongopass@localhost/tenistas?authSource=admin")
        db = mongoClient.getDatabase("alumno")
    }
}