package models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "DatosInforme")
class ListaDatosMadrid(
    @field:Element(name = "TemperaturaMedia")
    @param:Element(name = "TemperaturaMedia")
    var temperaturaMedia: String?,

    @field:Element(name = "TemperaturaMaxima")
    @param:Element(name = "TemperaturaMaxima")
    var temperaturaMáxima: String?,

    @field:Element(name = "TemperaturaMaximaLugar")
    @param:Element(name = "TemperaturaMaximaLugar")
    var temperaturaMáximaLugar: String?,

    @field:Element(name = "TemperaturaMaximaMomento")
    @param:Element(name = "TemperaturaMaximaMomento")
    var temperaturaMáximaMomento: String?,

    @field:Element(name = "TemperaturaMinima")
    @param:Element(name = "TemperaturaMinima")
    var temperaturaMinima: String?,

    @field:Element(name = "TemperaturaMinimaLugar")
    @param:Element(name = "TemperaturaMinimaLugar")
    var temperaturaMinimaLugar: String?,

    @field:Element(name = "TemperaturaMinimaMomento")
    @param:Element(name = "TemperaturaMinimaMomento")
    var temperaturaMinimaMomento: String?,

    @field:Element(name = "HuboPrecipitacion")
    @param:Element(name = "HuboPrecipitacion")
    var siHuboPrecipitación: String?,

    @field:Element(name = "Precipitaciones")
    @param:Element(name = "Precipitaciones")
    var precipitacion: String?
)