package interchange

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import logger
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class Jsonc {

    /**
     * Función que pasa Contenedores VArios dtoa Json y crea un file con la información
     */
    fun contenedoresVariosToAJson(p : Path, array : ArrayList<ContenedoresVariosDTO>){

        var path = Path.of(p.toString()+File.separator+"contenedoresVarios.json")

        if (Files.notExists(path)){Files.createFile(path)}

        val file = path.toFile()

        val json = Json { prettyPrint = true }

        file.writeText(json.encodeToString(array))

        logger.info("fichero creado")



}
    /**
     * Función que pasa Json a Contenedores varios dto o lanza una excepción
     */
    fun readJsontoContenedoresvariosDto(p : Path): List<ContenedoresVariosDTO> {
        logger.info("Pasando la path a Mr")
        val file = p.toFile()
        if (file.exists()) {
            return Json.decodeFromString<List<ContenedoresVariosDTO>>(file.readText())
        } else {
            throw IllegalArgumentException("El fichero Json no tiene los datos correctos")
        }
    }

    /**
     * Función que pasa ModeloResiduoDto a Json y crea un file con la información
     */
    fun modeloResiduoDtoToAJson(p : Path, array : ArrayList<ModeloResiduoDTO>){

        var path = Path.of(p.toString()+File.separator+"modeloResiduo.Json")

        if (Files.notExists(path)){Files.createFile(path)}

        val file = path.toFile()

        val json = Json { prettyPrint = true }

        file.writeText(json.encodeToString(array))

        logger.info("fichero creado")


    }

    /**
     * Función que pasa Json a ModeloResiduoDto o lanza una excepción
     */
    fun readJsontoModeloresiduoDto(p : Path): ArrayList<ModeloResiduoDTO> {

        val file = p.toFile()
        if (file.exists()) {
            var j =Json.decodeFromString<ArrayList<ModeloResiduoDTO>>(file.readText())
            return j
        } else {
            throw IllegalArgumentException("El fichero Json no tiene los datos correctos")
        }
    }

}