package services

import com.mongodb.reactivestreams.client.ChangeStreamPublisher
import db.MongoDbManager
import models.Empleado

// Esta clase es un servicio que se utiliza en el caso de que utilicemos MongoAtlas un servicio set local con docker.
class EmpleadosService {
    fun watch(): ChangeStreamPublisher<Empleado> {

        return MongoDbManager.database.getCollection<Empleado>()
            .watch<Empleado>()
            .publisher
    }
}