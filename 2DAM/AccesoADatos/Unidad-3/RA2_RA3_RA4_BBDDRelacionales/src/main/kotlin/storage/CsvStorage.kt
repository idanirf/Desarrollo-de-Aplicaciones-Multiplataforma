package storage

import models.VehiculoDto
import java.io.File

class CsvStorage: Storage {
    override fun readVehiculoDto(url: String): ArrayList<VehiculoDto> {
        if (!File(url).exists()){
            println("El fichero no existe en la ruta indicada")
            return ArrayList()
        }else if(!File(url).canRead()) {
            println("El fichero no se puede leer")
            return ArrayList()
        }
        val vehiculos = ArrayList<VehiculoDto>()
        try {
            println("Leemos lineas del fichero")
            var lista = File(url).readLines().drop(1).map { s -> getVehiculoDto(s) }
            vehiculos.addAll(lista)
        } catch (e: Exception) {
            println("Se ha producido un error en la ejecución" + e.printStackTrace())
        }
        return vehiculos
    }

    override fun writeVehiculoDto(url: String, vehiculos: List<VehiculoDto>): Boolean {
        TODO()
    }

    private fun getVehiculoDto(linea: String): VehiculoDto {
        var campo =linea.split(",")
        return VehiculoDto(
            0L,
            campo[0].trim(),
            campo[1].trim(),
            campo[2].trim(),
            campo[3].trim(),
            campo[4].trim(),
            campo[5].trim(),
            campo[6].trim(),
            campo[7].trim(),
            campo[8].trim(),
        )
    }
}