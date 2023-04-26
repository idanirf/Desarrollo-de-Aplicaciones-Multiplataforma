package controllers

import models.Aemet
import org.jetbrains.kotlinx.dataframe.api.*
import java.time.LocalDate

class DataframeController : EstadisticasController<GroupBy.Entry<Aemet, Aemet>> {

    override fun isPrecipitaciones(group: GroupBy.Entry<Aemet, Aemet>): Boolean {
        return group.group.sum("precipitacion")==0
    }

    override fun getSumaPrecipitacion(group: GroupBy.Entry<Aemet, Aemet>): Float {
        return group.group.sum("precipitacion").toFloat()
    }

    override fun getTemperaturaMedia(group: GroupBy.Entry<Aemet, Aemet>): Float {
        var sumaMin : Float = group.group.sum("temperaturaMin").toFloat()
        var sumMax : Float = group.group.sum("temperaturaMax").toFloat()
        var count : Int = group.group.rowsCount()*2
        return  (sumaMin + sumMax)/count
    }

    override fun getTemperaturaMinimaMomento(group: GroupBy.Entry<Aemet, Aemet>): String {
        return group.group.filter { it.getValue<Float>("temperaturaMin").toString()
            .equals(getTemperaturaMinima(group).toString())}
            .get(0).getValue<LocalDate>("dia").toString()
    }

    override fun getTemperaturaMaxima(group: GroupBy.Entry<Aemet, Aemet>): Float {
        return group.group.map { it.getValue<Float>("temperaturaMax") }.max()
    }

    override fun getTemperaturaMaximaMomento(group: GroupBy.Entry<Aemet, Aemet>): String {
        return group.group
            .filter { x -> x.getValue<Float>("temperaturaMax").toString()
                .equals(getTemperaturaMaxima(group).toString())}.get(0).getValue<String?>("dia").toString()
    }

    override fun getTemperaturaMaximaLugar(group: GroupBy.Entry<Aemet, Aemet>): String {
        return  group.group.filter { x -> x.getValue<Float>("temperaturaMax").toString()
            .equals(getTemperaturaMaxima(group).toString()) }.get(0).getValue<String>("localidad").toString()
    }

    override fun getTemperaturaMinimaLugar(group: GroupBy.Entry<Aemet, Aemet>): String {
        return group.group.filter { it.getValue<Float>("temperaturaMin").toString()
            .equals(getTemperaturaMinima(group).toString()) }.get(0).getValue<String>("localidad").toString()
    }


    override fun getTemperaturaMinima(group: GroupBy.Entry<Aemet, Aemet>): Float {
        return group.group.map{ it.getValue<Float>("temperaturaMin") }.min()
    }
}