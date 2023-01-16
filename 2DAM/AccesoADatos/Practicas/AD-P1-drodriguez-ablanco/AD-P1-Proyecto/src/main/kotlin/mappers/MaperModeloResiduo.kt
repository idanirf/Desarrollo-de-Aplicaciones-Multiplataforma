package mappers

import dto.ModeloResiduoDTO

import enums.Meses
import enums.TipoResiduo
import logger
import models.ModeloResiduo

class MaperModeloResiduo {

    /**
     * Funcion que pasandole un objeto Modeo residuo
     * te devuelve un objeto Modeo residuo dto
     */
    fun modeloResituoToDto(pojo: ModeloResiduo): ModeloResiduoDTO {

        return ModeloResiduoDTO(
            pojo.año,
            pojo.mes.toString(),
            pojo.lote,
            pojo.residuo.toString(),
            pojo.distrito,
            pojo.nombreDistrito,
            pojo.toneladas.toString()
        )
    }

    /**
     * Funcion que pasandole un objeto Modeo dto residuo
     * te devuelve un objeto Modeo residuo
     */
    fun tdoToModrloResiduo(dto: ModeloResiduoDTO): ModeloResiduo {
        return ModeloResiduo(
            dto.año,
            dto.mes,
            dto.lote,
            dto.residuo,
            dto.distrito,
            dto.nombreDistrito,
            pasarAFloat(dto.toneladas)
        )

    }

    /**
     * funcion que pasndole una string la pasa a float
     * y si no es posible devuelve mull
     */
    private fun pasarAFloat(toneladas: String?): Float? {
        var to = toneladas?.replace(",",".")
        return to?.toFloatOrNull()
    }

    //funcionalidades futuras de mejora
    private fun getMes(s: String?): Meses? {
        logger.info(" entrado en get mes")
        if (s == null) return null
        when (s) {
            "ENERO" -> return Meses.ENERO
            "FEBRERO" -> return Meses.FEBRERO
            "MARZO" -> return Meses.MARZO
            "ABRIL" -> return Meses.ABRIL
            "MAYO" -> return Meses.MAYO
            "JUNIO" -> return Meses.JUNIO
            "JULIO" -> return Meses.JULIO
            "AGOSTO" -> return Meses.AGOSTO
            "SEPTIEMBRE" -> return Meses.SEPTIEMBRE
            "OCTUBRE" -> return Meses.OCTUBRE
            "NOVIEMBRE" -> return Meses.NOVIEMBRE
            "DICIEMBRE" -> return Meses.DICIEMBRE
            "enero" -> return Meses.ENERO
            "febrero" -> return Meses.FEBRERO
            "marzo" -> return Meses.MARZO
            "abril" -> return Meses.ABRIL
            "mayo" -> return Meses.MAYO
            "junio" -> return Meses.JUNIO
            "julio" -> return Meses.JULIO
            "agosto" -> return Meses.AGOSTO
            "septiembre" -> return Meses.SEPTIEMBRE
            "octubre" -> return Meses.OCTUBRE
            "noviembre" -> return Meses.NOVIEMBRE
            "diciembre" -> return Meses.DICIEMBRE
            else -> return null
        }
        return null
    }

    //funcionalidades futuras de mejora
    private fun getTipoResiduo(s: String?): TipoResiduo? {
        logger.info(" entrado en get tipo residuo")
        if (s == null) return null

        when (s) {
            "RESTO" -> return TipoResiduo.RESTO
            "ENVASES" -> return TipoResiduo.ENVASES
            "VIDRIO" -> return TipoResiduo.VIDRIO
            "ORGÁNICA" -> return TipoResiduo.ORGANICA
            "PAPEL Y CARTON" -> return TipoResiduo.PAPEL_Y_CARTÓN
            "PUNTOS LIMPIOS" -> return TipoResiduo.PUNTOS_LIMPIOS
            "CARTON COMERCIAL" -> return TipoResiduo.CARTÓN_COMERCIAL
            "VIDRIO COMRCIAL" -> return TipoResiduo.VIDRIO_COMERCIAL
            "PILAS" -> return TipoResiduo.PILAS
            "ANIMALES_MUERTOS" -> return TipoResiduo.ANIMALES_MUERTOS
            "RCD" -> return TipoResiduo.RCD
            "CONTENEDORES DE ROPA USADA" -> return TipoResiduo.CONTENEDORES_DE_ROPA_USADA
            "REIDUOS DEPOSITADOS EN MIGAS CALIENTES" -> return TipoResiduo.RESIDUOS_DEPOSITADOS_EN_MIGAS_CALIENTES
            else -> return null
        }
        return TipoResiduo.DESCONOCIDO
    }


}



