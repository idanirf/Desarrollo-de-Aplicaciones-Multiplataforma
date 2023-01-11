package interchange

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import dataOfUse.DataofUse
import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import kotlinx.serialization.encodeToString
import logger
import nl.adaptivity.xmlutil.serialization.XML
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Logger

class Xmlc {

    /**
     * Función que pasndole una path te devuelve un
     * aarray list de Modelo Residuo dto si contiene los datos
     * necesarios
     */
    fun xmlToModeloresiduoDto(p : Path): ArrayList<ModeloResiduoDTO>{
        logger.info("Pasando la path a Mr")
        try {

            val file = p.toFile()
            if (file.exists()) {
                return XML.decodeFromString<ArrayList<ModeloResiduoDTO>>(file.readText())
            } else {
                throw IllegalArgumentException("El fichero ${file.absolutePath} no existe")
            }
        } catch (e : IOException) {
            logger.info("error al leer el xml")
        }

        return  ArrayList<ModeloResiduoDTO>()
    }

    /**
     * Función que pasndole una path te devuelve un
     * aarray list de Contenedores varios dto si contiene los datos
     * necesarios
     */
    fun xmlToContenedoresVariosDto(p : Path): ArrayList<ContenedoresVariosDTO>{
        logger.info("Pasando la path a Cv")
        try {

            val file = p.toFile()
            if (file.exists()) {
                return XML.decodeFromString<ArrayList<ContenedoresVariosDTO>>(file.readText())
            } else {
                throw IllegalArgumentException("El fichero ${file.absolutePath} no existe")
            }
        } catch (e : IOException) {
            logger.info("error al leer el xml")
        }

        return  ArrayList<ContenedoresVariosDTO>()
    }


    /**
     * funcion que pasndole una clase data of use y una pat te añade los datos en el fichero
     */
    fun writeData(p: Path, data : DataofUse) {

        var p = Paths.get("").toAbsolutePath().toString()+ File.separator +
                "DataOfUse"

        try {
            if (Files.notExists(Path.of(p))){
                Files.createDirectories(Path.of(p))
            }
            var p2 = p + File.separator + "datos.xml"
            if (Files.notExists(Path.of(p2))){
                Files.createFile(Path.of(p))
            }


            var fichero =File( p2)
            var mapper = XmlMapper();
            var bw = BufferedWriter(FileWriter(fichero,true))



            logger.info("escribiendo en el fichero")
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var xmlString: String = mapper.writeValueAsString(data)
            bw.newLine()
            bw.write(xmlString)
            bw.close()

        } catch (e : IOException) {
            logger.info("error al crear Xml")
        }
        logger.info("se ha creado el  Xml")
    }

    /**
     * Función que pasndole una arru list de Cotenedores varios dto
     * y una path te crea en ella un archivo con los datos formato xml
     */
    fun contenedoresVariosDtoToXml(array: ArrayList<ContenedoresVariosDTO>, p :Path) {
        logger.info("Creando file xml de Cv")
        val file = p.toFile()
        val xml = XML { indent = 4 }
        file.writeText(xml.encodeToString(array))
    }
    /**
     * Función que pasndole una arru list de modelo residuo dto
     * y una path te crea en ella un archivo con los datos formato xml
     */
    fun modeloResiduoDtoToXml(array: ArrayList<ModeloResiduoDTO> , p : Path) {
        logger.info("Creando file xml de Mr")
        val file = p.toFile()
        val xml = XML { indent = 4 }
        file.writeText(xml.encodeToString(array))
    }


}