package utils.ficheros

import es.drodriguez.com.tennislabspring.dto.PedidoDto
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class FicheroJsonPedidosCompletados{
    private var json = Json { prettyPrint=true }
    private var mutex = Mutex()

    /**
     * Write fichero: Metodo para escribir en el fichero de la lista de pedidos.
     *
     * @param path
     * @param item
     */
    suspend fun writeFichero(path: String, item: List<PedidoDto>) {
        var out: OutputStreamWriter
        var fichero = File(path)

        mutex.withLock {
            out = OutputStreamWriter(FileOutputStream(fichero, false))
            out.use { it.write(json.encodeToString(item)) }
        }
    }
}