package db

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients

object DBManager {
    var mongoClient: MongoClient = MongoClients.create("mongodb://localhost:27017")
    val db = mongoClient.getDatabase("MongoDB")

    fun restartdb(){
        db.drop()
    }

    fun open(){
        mongoClient = MongoClients.create("mongodb://localhost:27017")
    }

    fun close(){
        if (mongoClient!= null) {
            mongoClient.close()
        }
    }

    fun viewDBases(){
        println("Bases de datos/ colecciones: ")
        mongoClient.listDatabaseNames().forEach {
            println(it)
        }
    }

    fun viewCollections(){
        println("Bases de datos/ colecciones: ")
        db.listCollectionNames().forEach {
            println(it)
        }
    }

    fun initCollection(nameCollection:String){
        try {
            db.createCollection(nameCollection)
            println("Collection $nameCollection created")
        } catch (e:Exception) {
            println(e.message)
        }
    }
}