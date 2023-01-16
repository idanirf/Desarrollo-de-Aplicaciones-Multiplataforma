package dataOfUse

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Logger


private val logger: Logger = Logger.getLogger("Azahara y Dani Log")

/**
 * clase que se dedica a:
 * Por cada ejecución debemos guardar un fichero bitacora.xml donde tengamos en este
XML un listado de las ejecuciones con la siguiente información:
- ID de la ejecución en base a uuid
- Instante: Instante de la ejecución en formato ISO 8601
- Tipo de opción elegida (parser, resumen global, resumen ciudad).
- Éxito: si tuvo éxito o no su procesamiento.
- Tiempo de ejecución: tiempo de ejecución si tuvo éxito en milisegundos.
 */
class DataofUse(tipoOpcion : String, exito: Boolean, tiempoEjecucion : Long) {

    val id = UUID.randomUUID()
    //SO 8601: la porción de la fecha sigue el formato YYYY-MM-DD ejemplo: 2016-06-01T14:41:36-08:00.
    val instanteFormatoISO  = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
    val tipoOpcion : String =  tipoOpcion
    var exito : Boolean = exito
    val tiempoDeEjecucion : Long = tiempoEjecucion

    override fun toString(): String {
        return "DataofUse(id=$id, instanteFormatoISO='$instanteFormatoISO', tipoOpcion='$tipoOpcion', exito=$exito, tiempoDeEjecucion=$tiempoDeEjecucion)"
    }


}