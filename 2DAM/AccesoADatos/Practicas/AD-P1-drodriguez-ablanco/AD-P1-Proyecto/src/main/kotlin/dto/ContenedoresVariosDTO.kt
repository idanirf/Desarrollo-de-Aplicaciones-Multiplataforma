package dto


@kotlinx.serialization.Serializable
data class ContenedoresVariosDTO(
    val codigoInternoSituado: String?,
    val tipoContenedor: String?,
    val modelo: String?,
    val descripcionModelo: String?,
    val cantidad: String?,
    val lote: String?,
    val distrito: String?,
    val barrio: String?,
    val tipoVia: String?,
    val nombre: String?,
    val numero: Int?,
    val coordenadaX: String?,
    val coordenadaY: String?,
    val TAG: String?
){

    override fun toString(): String {
        return "ContenedoresVariosDTO(codigoInternoSituado=$codigoInternoSituado, tipoContenedor=$tipoContenedor, modelo=$modelo, descripcionModelo=$descripcionModelo, cantidad=$cantidad, lote=$lote, distrito=$distrito, barrio=$barrio, tipoVia=$tipoVia, nombre=$nombre, numero=$numero, coordenadaX=$coordenadaX, coordenadaY=$coordenadaY, TAG=$TAG)"
    }
}