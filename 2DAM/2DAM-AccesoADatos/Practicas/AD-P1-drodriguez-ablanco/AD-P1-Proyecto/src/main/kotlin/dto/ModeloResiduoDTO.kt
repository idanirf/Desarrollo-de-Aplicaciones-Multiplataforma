package dto


import java.util.logging.Logger


//para pasar a json y a xml
@kotlinx.serialization.Serializable
data class ModeloResiduoDTO(

    val año: String? ,
    val mes: String? ,
    val lote: String? ,
    val residuo: String? ,
    val distrito: String?,
    val nombreDistrito: String? ,
    val toneladas: String? ,
){
    override fun toString(): String {
        return "ModeloResiduoDTO(año=$año, mes=$mes, lote=$lote, residuo=$residuo, distrito=$distrito, nombreDistrito=$nombreDistrito, toneladas=$toneladas)"
    }


}

