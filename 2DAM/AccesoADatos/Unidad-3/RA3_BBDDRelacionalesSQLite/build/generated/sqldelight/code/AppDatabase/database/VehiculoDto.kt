package database

import kotlin.Long
import kotlin.String

public data class VehiculoDto(
  public val id: Long,
  public val uuid: String,
  public val marca: String,
  public val modelo: String,
  public val matricula: String,
  public val fechaMatriculacion: String,
  public val tipoMotor: String,
  public val createdAt: String,
  public val updatedAt: String,
  public val deleted: String
) {
  public override fun toString(): String = """
  |VehiculoDto [
  |  id: $id
  |  uuid: $uuid
  |  marca: $marca
  |  modelo: $modelo
  |  matricula: $matricula
  |  fechaMatriculacion: $fechaMatriculacion
  |  tipoMotor: $tipoMotor
  |  createdAt: $createdAt
  |  updatedAt: $updatedAt
  |  deleted: $deleted
  |]
  """.trimMargin()
}
