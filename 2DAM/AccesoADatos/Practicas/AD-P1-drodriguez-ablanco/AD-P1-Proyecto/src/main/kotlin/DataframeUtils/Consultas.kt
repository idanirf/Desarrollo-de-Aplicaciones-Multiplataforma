package DataframeUtils

import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import java.nio.file.Path


class Consultas {
    fun getToneladasPorResiduo(filasMr: DataFrame<Any?>, columnasMr: List<String>): DataFrame<Any?> {
        var  columnasMr = filasMr.columnNames()
        logger.info("Suma de toneladas")
        return filasMr.groupBy(columnasMr.get(3))
            .aggregate { sum(columnasMr.get(6)) into "Suma" }
    }

    fun getMaximo(filasMr: DataFrame<Any?>, columnasMr: List<String>): DataFrame<Any?>{
        var  columnasMr = filasMr.columnNames()
        logger.info("maximo de toneladas")
        return filasMr.groupBy(columnasMr.get(3))
            .aggregate { max(columnasMr.get(6)) into "Maximo de Toneladas" }
    }
    fun getMinimo(filasMr: DataFrame<Any?>, columnasMr: List<String>): DataFrame<Any?>{
        var  columnasMr = filasMr.columnNames()
        logger.info("minimo de toneladas")
        return filasMr.groupBy(columnasMr.get(3))
            .aggregate { min(columnasMr.get(6)) into "Minimo de Toneladas" }
    }
    fun getMedia(filasMr: DataFrame<Any?>, columnasMr: List<String>): DataFrame<Any?> {
        var  columnasMr = filasMr.columnNames()
        logger.info("Media de toneladas")
        return filasMr.groupBy(columnasMr.get(3))
            .aggregate { mean(columnasMr.get(6)) into "Media de Toneladas" }
    }

    fun getContenedoresDeCadaTipo(filasCv: DataFrame<Any?>, nombresCv: List<String>): DataFrame<Any?>{
        var  nombresCv = filasCv.columnNames()
        logger.info("Número de contenedores de cada tipo que hay en este distrito")
        return  filasCv.groupBy(nombresCv.get(1))
            .aggregate { count() into  "Numero de contenedores de ese tipo" }
    }

    fun getNumeroContenedoresPorDistrito(filasCv: DataFrame<Any?>, nombreCol: List<String>): DataFrame<Any?> {
        var  nombresCv = filasCv.columnNames()
        logger.info("nuemro de contenedores por dstrito")
        return filasCv.groupBy(nombreCol.get(6)).aggregate { count() into "Numero de contenedores" }
    }

    fun getmediaDeContenedoresDeCadaTipo(filasCv: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {
        var  nombresCv = filasCv.columnNames()

        logger.info("Media de contenedores de cada tipo que hay en cada distrito")

        return filasCv.groupBy(nombreCol.get(6), nombreCol.get(1))
            .aggregate { mean(nombreCol.get(10)) into "Media" }

    }

    fun getmediaToneladasAnuales(filasMr: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {

        var  nombresCv = filasMr.columnNames()
        var columnaAño = filasMr.columnNames().get(0)
        logger.info(
            "Media de toneladas anuales de recogidas por" +
                    " cada tipo de basura agrupadas por distrito"
        )

         return filasMr.groupBy(columnaAño, nombreCol.get(5), nombreCol.get(3)).sortBy(nombreCol.get(5))
            .aggregate { mean(nombreCol.get(6)) into "Media de toneladas" }



    }

    fun getmaxToneladasPorDistrito(filasMr: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {

        var  nombreCol = filasMr.columnNames()
        logger.info(
            "Máximo de toneladas anuales de recogidas por cada tipo\n" +
                    "    de basura agrupadas por distrito."
        )

            var f = filasMr.groupBy(nombreCol.get(0), nombreCol.get(4), nombreCol.get(3))
            .aggregate { max(nombreCol.get(6)) into "maximo"}.sortBy(nombreCol.get(4))

        return f

    }

    fun getminToneladasPorDistrito(filasCv: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {

        var  nombreCol = filasCv.columnNames()
        logger.info(
            " mínimo de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )


        var año = filasCv.columnNames().get(0)
        var f = filasCv.groupBy(año, nombreCol.get(5), nombreCol.get(3))
            .aggregate { min(nombreCol.get(6)) into "minimo"}.sortBy(nombreCol.get(5))

        return f
    }

    fun getMedToneladasPorDistrito(filasCv: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {

        var  nombresCv = filasCv.columnNames()
        logger.info(
            " media de toneladas anuales de recogidas por cada tipo\\n\" +\n" +
                    "                    \"    de basura agrupadas por distrito. "
        )
        var año = filasCv.columnNames().get(0)
        var f = filasCv.groupBy(año, nombreCol.get(5), nombreCol.get(3))
            .aggregate { mean(nombreCol.get(6)) into "media"}.sortBy(nombreCol.get(5))

        return f
    }

    fun getsumToneladasPorDistrito(filasCv: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {
        var  nombreCol = filasCv.columnNames()
        logger.info(
            " suma de todas las toneladas. "
        )
        return filasCv.groupBy(nombreCol.get(5))
            .aggregate {  sum(nombreCol.get(6)) into "Toneladas Toales"}
    }

    fun sumToneladasPorDistritoPorTipo(filasCv: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {
        var  nombreCol = filasCv.columnNames()
        logger.info("Por cada distrito obtener para cada tipo de residuo la cantidad recogida.")
        return filasCv.groupBy(nombreCol.get(5),nombreCol.get(3))
            .aggregate { sum(nombreCol.get(6)) into "Suma por distrito y tipo"}.sortBy(nombreCol.get(5))
    }

    fun getMediaToneladasMensuales(filasMr: DataFrame<Any?>, nombreCol: List<String>):  DataFrame<Any?> {
        var  nombreCol = filasMr.columnNames()
        logger.info( "media de toneladas mensuales de recogida de basura por distrito")

        return  filasMr.groupBy(nombreCol.get(1), nombreCol.get(5), nombreCol.get(3)).sortBy(nombreCol.get(5))
            .aggregate { mean(nombreCol.get(6)) into "Media de toneladas" }
    }

    fun getDesToneladasPorDistrito(filasMr: DataFrame<Any?>, nombreCol: List<String>): DataFrame<Any?> {
        var  nombreCol = filasMr.columnNames()
        logger.info("desviación de toneladas anuales de recogidas por cada tipo de basura agrupadas por distrito.")
        var año = filasMr.columnNames().get(0)
        var f = filasMr.groupBy(año, nombreCol.get(5), nombreCol.get(3))
            .aggregate { std(nombreCol.get(6)) into "desviacion"}.sortBy(nombreCol.get(5))

        return f
    }

    fun getEstadisticasTotalPorAño(
        maxToneladasPorDistrito: DataFrame<Any?>,
        minToneladasPorDistrito: DataFrame<Any?>,
        medToneladasPorDistrito: DataFrame<Any?>,
        desvToneladasPorDistrito: DataFrame<Any?>,
        nombreCol: List<String>
    ): DataFrame<Any?>? {


        var f = maxToneladasPorDistrito.join(minToneladasPorDistrito)
            .join(medToneladasPorDistrito).join(desvToneladasPorDistrito)
        return f

    }

    companion object {
        fun getDesviacion(filasMr: DataFrame<Any?>, columnasMr: List<String>):  DataFrame<Any?> {
            var  columnasMr = filasMr.columnNames()
            logger.info("Desviacion de toneladas")
            return filasMr.groupBy(columnasMr.get(3))
                .aggregate { max(columnasMr.get(6)) into "Desciacion de Toneladas" }
        }

        fun joinEstadisticas(
            estadisticasPorResiduoMax: DataFrame<Any?>,
            estadisticasPorResiduoMin: DataFrame<Any?>,
            estadisticasPorResiduoMed: DataFrame<Any?>,
            estadisticasPorResiduoDesv: DataFrame<Any?>,
            directoriodeResumen: Path,
            columnasMr: List<String>
        ): DataFrame<Any?>? {


            var datosDesvYMed = estadisticasPorResiduoDesv.join(estadisticasPorResiduoMed,columnasMr.get(3).toString())
            var datosDesvYMedYMin = datosDesvYMed.join(estadisticasPorResiduoMin,columnasMr.get(3).toString())
            var datosUnion = datosDesvYMedYMin.join(estadisticasPorResiduoMax,columnasMr.get(3).toString())
            return datosUnion
        }
    }


}