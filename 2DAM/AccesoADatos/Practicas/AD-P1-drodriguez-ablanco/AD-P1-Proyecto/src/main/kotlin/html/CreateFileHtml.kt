package html


import logger
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.isDirectory

class CreateFileHtml {
    fun create(distrito :String,  directoriodeResumen: Path, html: String): Boolean {

        if(directoriodeResumen.isDirectory()){
            logger.info("el path es un directorio")
            return  crearfichero(distrito,directoriodeResumen, html)
        }else{
            logger.info("el path no es un directorio no se puede crear el fichero")
            return false
        }
    }

    private fun crearfichero(distrito: String,  directoriodeResumen: Path,  html: String): Boolean {

        var path = Path.of(directoriodeResumen.toString() + File.separator + "resumen$distrito.html")
        var file = path.toFile()
        var bw = BufferedWriter(FileWriter(file))
        try {
            bw.write(html)
        }catch (e: Exception){
            e.printStackTrace()
            logger.info("no se ha podido escribir en el fichero")
            return false
        }finally {
            bw.close()
        }
        return true
    }


}