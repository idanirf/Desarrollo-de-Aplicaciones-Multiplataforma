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
)