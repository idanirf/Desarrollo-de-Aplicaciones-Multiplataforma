package mappers

import dto.ContenedoresVariosDTO
import models.ContenedoresVarios

class MapperContenedoresVarios {

    /**
     * Funcion que pasandole un objeto contenedores Varios
     * te devuelve un objeto contenedores varios dto
     */
    fun contenedoresVariosToDto(pojo: ContenedoresVarios): ContenedoresVariosDTO {

        return ContenedoresVariosDTO(
            codigoInternoSituado = pojo.codigoInternoSituado,
            // tipoContenedor = getTipoContenedor(pojo.tipoContenedor),
            tipoContenedor = pojo.tipoContenedor,
            modelo = pojo.modelo,
            descripcionModelo = pojo.descripcionModelo,
            cantidad = pojo.cantidad,
            lote = pojo.lote,
            distrito = pojo.distrito,
            barrio = pojo.barrio,
            tipoVia = pojo.tipoVia,
            nombre = pojo.nombre,
            numero = pojo.numero,
            coordenadaX = pojo.coordenadaX,
            coordenadaY = pojo.coordenadaY,
            TAG = pojo.TAG
        )
    }

    /**
     * Funcion que paasndole un objeto contenedores Varios dto
     * te devuelve un objeto contenedores varios
     */
    fun tdoToContenedoresVarios(dto: ContenedoresVariosDTO): ContenedoresVarios {

        return ContenedoresVarios(
            codigoInternoSituado = dto.codigoInternoSituado,
            tipoContenedor = dto.tipoContenedor,
            modelo = dto.modelo,
            descripcionModelo = dto.descripcionModelo,
            cantidad = dto.cantidad,
            lote = dto.lote,
            distrito = dto.distrito,
            barrio = dto.barrio,
            tipoVia = dto.tipoVia,
            nombre = dto.nombre,
            numero = dto.numero,
            coordenadaX = dto.coordenadaX,
            coordenadaY = dto.coordenadaY,
            TAG = dto.TAG
        )
    }
}




