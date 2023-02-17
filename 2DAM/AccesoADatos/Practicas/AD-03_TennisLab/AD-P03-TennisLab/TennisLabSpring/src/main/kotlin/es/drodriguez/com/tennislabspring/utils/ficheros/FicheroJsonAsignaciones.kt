package utils.ficheros

import es.drodriguez.com.tennislabspring.models.AsignacionesEncordadores
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class FicheroJsonAsignaciones {
    private var json = Json { prettyPrint=true }
    private var mutex = Mutex()

    /**
     * Write fichero: Método para escribir en el fichero de las asignaciones de encordadores
     *
     * @param path
     * @param item
     */
    suspend fun writeFichero(path: String, item: List<AsignacionesEncordadores>) {
        var out: OutputStreamWriter
        var fichero = File(path)

        mutex.withLock {
            out = OutputStreamWriter(FileOutputStream(fichero, false))
            out.use { it.write(json.encodeToString(item)) }
        }
    }
}