package models

import enums.TipoContenedor
import java.io.File

 class ContenedoresVarios(
     codigoInternoSituado: String?,
     tipoContenedor: String?,
     modelo: String?,
     descripcionModelo: String?,
     cantidad: String?,
     lote: String?,
     distrito: String?,
     barrio: String?,
     tipoVia: String?,
     nombre: String?,
     numero: Int?,
     coordenadaX: String?,
     coordenadaY: String?,
     TAG: String?

)  {
     val codigoInternoSituado: String?= codigoInternoSituado
     val tipoContenedor: String? = tipoContenedor
     val modelo: String?=modelo
     val descripcionModelo: String?= descripcionModelo
     val cantidad: String?= cantidad
     val lote: String?= lote
     val distrito: String?= distrito
     val barrio: String? = barrio
     val tipoVia: String?= tipoVia
     val nombre: String?= nombre
     val numero: Int?= numero
     val coordenadaX: String?= coordenadaX
     val coordenadaY: String?=coordenadaY
     val TAG: String? = TAG

     override fun equals(other: Any?): Boolean {
         if (this === other) return true
         if (javaClass != other?.javaClass) return false

         other as ContenedoresVarios

         if (codigoInternoSituado != other.codigoInternoSituado) return false
         if (tipoContenedor != other.tipoContenedor) return false
         if (modelo != other.modelo) return false
         if (descripcionModelo != other.descripcionModelo) return false
         if (cantidad != other.cantidad) return false
         if (lote != other.lote) return false
         if (distrito != other.distrito) return false
         if (barrio != other.barrio) return false
         if (tipoVia != other.tipoVia) return false
         if (nombre != other.nombre) return false
         if (numero != other.numero) return false
         if (coordenadaX != other.coordenadaX) return false
         if (coordenadaY != other.coordenadaY) return false
         if (TAG != other.TAG) return false

         return true
     }
     override fun hashCode(): Int {
         var result = codigoInternoSituado?.hashCode() ?: 0
         result = 31 * result + (tipoContenedor?.hashCode() ?: 0)
         result = 31 * result + (modelo?.hashCode() ?: 0)
         result = 31 * result + (descripcionModelo?.hashCode() ?: 0)
         result = 31 * result + (cantidad?.hashCode() ?: 0)
         result = 31 * result + (lote?.hashCode() ?: 0)
         result = 31 * result + (distrito?.hashCode() ?: 0)
         result = 31 * result + (barrio?.hashCode() ?: 0)
         result = 31 * result + (tipoVia?.hashCode() ?: 0)
         result = 31 * result + (nombre?.hashCode() ?: 0)
         result = 31 * result + (numero?.hashCode() ?: 0)
         result = 31 * result + (coordenadaX?.hashCode() ?: 0)
         result = 31 * result + (coordenadaY?.hashCode() ?: 0)
         result = 31 * result + (TAG?.hashCode() ?: 0)
         return result
     }
     override fun toString(): String {
         return "ContenedoresVarios(codigoInternoSituado=$codigoInternoSituado, tipoContenedor=$tipoContenedor, modelo=$modelo, descripcionModelo=$descripcionModelo, cantidad=$cantidad, lote=$lote, distrito=$distrito, barrio=$barrio, tipoVia=$tipoVia, nombre=$nombre, numero=$numero, coordenadaX=$coordenadaX, coordenadaY=$coordenadaY, TAG=$TAG)"
     }


 }
//para mejorar el programa más adelante
fun getEnumTipoContenedor(s: String): TipoContenedor {
    when (s) {
        "Envases" -> return TipoContenedor.ENVASES
        "Organica" ->return TipoContenedor.ORGANICA
        "Resto" -> return TipoContenedor.RESTO
        "Papel y carton" -> return TipoContenedor.PAPEL_Y_CARTÓN
        "Vidrio" -> return TipoContenedor.VIDRIO
    }
    return TipoContenedor.DESCONOCIDO


}




