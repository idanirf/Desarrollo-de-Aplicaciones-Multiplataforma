package models

class VehiculoDto(
    var id: Long = 0,
    var uuid: String,
    var marca: String,
    var modelo: String,
    var matricula: String,
    var fechaMatriculacion: String,
    var tipoMotor: String,
    var createdAt: String,
    var updatedAt: String,
    var deleted: String
){
    override fun toString(): String {
        return "VehiculoDto(id=$id, uuid='$uuid', marca='$marca', modelo='$modelo', matricula='$matricula', fechaMatriculacion='$fechaMatriculacion', tipoMotor='$tipoMotor', createdAt='$createdAt', updatedAt='$updatedAt', deleted='$deleted')"
    }
}
