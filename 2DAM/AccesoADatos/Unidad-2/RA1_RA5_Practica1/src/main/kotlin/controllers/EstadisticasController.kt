package controllers

interface EstadisticasController <T> {
    fun isPrecipitaciones(t : T): Boolean

    fun getSumaPrecipitacion(t : T): Float ?

    fun getTemperaturaMedia(t : T): Float ?

    fun getTemperaturaMinimaMomento(t : T): String ?

    fun getTemperaturaMaxima(t : T): Float ?

    fun getTemperaturaMaximaMomento(t : T): String ?

    fun getTemperaturaMaximaLugar(t : T): String ?

    fun getTemperaturaMinimaLugar(t : T): String ?

    fun getTemperaturaMinima(t : T): Float ?
}