package utils.ficheros

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Pedido
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class FicheroJsonPedidosPendientes {
    private var json = Json { prettyPrint=true }
    private var mutex = Mutex()

    /**
     * Write fichero: Método para escribir en el fichero Json para los pedidos pendientes.
     *
     * @param path
     * @param item
     */
    suspend fun writeFichero(path: String, item: List<Pedido>){
        var out: OutputStreamWriter
        var fichero = File(path)

        mutex.withLock {
            out = OutputStreamWriter(FileOutputStream(fichero, false))
            out.use { it.write(json.encodeToString(item)) }
        }
    }
}