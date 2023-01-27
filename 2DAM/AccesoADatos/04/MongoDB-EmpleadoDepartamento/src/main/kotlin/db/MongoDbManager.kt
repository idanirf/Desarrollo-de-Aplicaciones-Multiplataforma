package db

import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object MongoDbManager {
    private lateinit var mongoClient: CoroutineClient
    lateinit var database: CoroutineDatabase

    private const val MONGO_TYPE = "mongodb+srv://"
    private const val HOST = "empleados.tvafy7e.mongodb.net"
    private const val DATABASE = "empleados"
    private const val USERNAME = "mongo"
    private const val PASSWORD = "mongo"
    private const val OPTIONS = "?authSource=admin&retryWrites=true&w=majority"

    private const val MONGO_URI =
        "$MONGO_TYPE$USERNAME:$PASSWORD@$HOST/$DATABASE"



    init {

        println("Inicializando conexion a MongoDB -> $MONGO_URI$OPTIONS")
        mongoClient =
            KMongo.createClient("$MONGO_URI$OPTIONS")
                .coroutine
        database = mongoClient.getDatabase("tenistas")
    }
}