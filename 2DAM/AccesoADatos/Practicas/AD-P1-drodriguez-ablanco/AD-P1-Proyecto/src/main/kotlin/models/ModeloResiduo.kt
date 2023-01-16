package models

import enums.Meses
import enums.TipoResiduo
import java.io.Serializable
import java.time.LocalDate

 class ModeloResiduo (año:String?, mes: String?, lote: String?, residuo: String?, distrito : String?
                     , nombreDistrito: String?, toneladas: Float?): Serializable {
    val año : String?=año
    val mes : String? = mes
    val lote : String? = lote
    var residuo : String? = residuo
    val distrito : String? = distrito
    val nombreDistrito : String? = nombreDistrito
    val toneladas :  Float? = toneladas

     override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModeloResiduo

        if (año != other.año) return false
        if (mes != other.mes) return false
        if (lote != other.lote) return false
        if (residuo != other.residuo) return false
        if (distrito != other.distrito) return false
        if (nombreDistrito != other.nombreDistrito) return false
        if (toneladas != other.toneladas) return false

        return true
    }
     override fun toString(): String {
         return "ModeloResiduo(año=$año, mes=$mes, lote=$lote, residuo=$residuo, distrito=$distrito, nombreDistrito=$nombreDistrito, toneladas=$toneladas)"
     }


 }