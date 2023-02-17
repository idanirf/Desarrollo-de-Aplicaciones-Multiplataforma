package utils.ficheros

import dto.TareaDto
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class FicheroJsonServicios {

    private var json = Json { prettyPrint=true }
    private var mutex = Mutex()

    suspend fun writeFichero(path: String, item: List<TareaDto>) {
        var out: OutputStreamWriter
        var fichero = File(path)

        mutex.withLock {
            out = OutputStreamWriter(FileOutputStream(fichero, false))
            out.use { it.write(json.encodeToString(item))}
            out.close()
        }
    }
}