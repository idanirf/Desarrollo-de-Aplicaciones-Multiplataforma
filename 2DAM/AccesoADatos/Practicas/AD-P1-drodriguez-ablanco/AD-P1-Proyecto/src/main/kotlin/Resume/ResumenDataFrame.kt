package Resume
import DataframeUtils.Consultas
import DataframeUtils.GetDataFrame
import DataframeUtils.Graficos
import html.CreateHtml
import logger
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import java.nio.file.Path
import java.time.LocalDate


class ResumenDataFrame {

    /**
    funcion que devuelve un resumen en html de el distrito selecionado
    información:
    - Titulo: Resumen de recogidas de basura y reciclaje de “Distrito”
    - Fecha de generación: Fecha y hora en formato español.
    - Autores: Nombre y apellidos de los dos autores.
    - Número de contenedores de cada tipo que hay en este distrito.
    - Total de toneladas recogidas en ese distrito por residuo.
    - Gráfico con el total de toneladas por residuo en ese distrito.
    - Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.
    - Gráfica del máximo, mínimo y media por meses en dicho distrito.
    - Tiempo de generación del mismo en milisegundos.
     */
    fun resumeDistrictFrame(pathMR: Path, pathCV: Path, district: String, directoriodeResumen: Path): String {

        var tInit = System.currentTimeMillis();

        logger.info("Obtenemos datos del fichero correspondiente a Mr")
        var filasMr = GetDataFrame().dataFrameModeloResiduo(pathMR, district)


        logger.info("Obtenemos datos del fichero correspondiente a Cv")
        var filasCv = GetDataFrame().dataframeContenedoresVarios(pathCV, district)

        var toneladasPorResiduo : DataFrame<Any?>? = null
        var graficoDeTotalToneladas : Any? = null
        var estadisticasPorResiduoMax : DataFrame<Any?>? = null
        var estadisticasPorResiduoMin : DataFrame<Any?>? = null
        var estadisticasPorResiduoMed : DataFrame<Any?>? = null
        var estadisticasPorResiduoDesv : DataFrame<Any?>? = null
        var graficoDemaxMinMedYDes : Any? = null
        var contenedoresPorTipo : DataFrame<Any?>? = null
        var estadisticasTotales : DataFrame<Any?>? = null


        logger.info("Comprobando que  Mr no este vacio")
        if (filasMr?.count() == 0) {
            logger.info("no existe el distrito en el fichero modelo residuo, " +
                        "por lo que las consultas relacionadas no se harán"
            )
        } else if (filasMr != null) {
            var columnasMr = filasMr.columnNames()

                logger.info("existe el distrito en los ficheros en modelo Residuo")

                toneladasPorResiduo = Consultas().getToneladasPorResiduo(filasMr,columnasMr)

                graficoDeTotalToneladas = Graficos().doGraficoTotalToneladas(toneladasPorResiduo,directoriodeResumen,columnasMr)

                estadisticasPorResiduoMax = Consultas().getMaximo(filasMr,columnasMr)

                estadisticasPorResiduoMin = Consultas().getMinimo(filasMr,columnasMr)

                estadisticasPorResiduoMed = Consultas().getMedia(filasMr,columnasMr)

                estadisticasPorResiduoDesv = Consultas.getDesviacion(filasMr,columnasMr)

                estadisticasTotales = Consultas.joinEstadisticas( estadisticasPorResiduoMax,
                    estadisticasPorResiduoMin,
                    estadisticasPorResiduoMed,
                    estadisticasPorResiduoDesv,
                    directoriodeResumen,columnasMr)

                graficoDemaxMinMedYDes = Graficos().doGraficoDeEstadicticas(
                    estadisticasTotales,
                    directoriodeResumen,
                    columnasMr)

            }
        logger.info("Comprobando que  Cv no este vacio")
            if (filasCv?.count() == 0) {
                logger.info("no existe el distrito en el fichero Contenedores varios, " +
                            "por lo que las consultas relacionadas no se harán")
            } else if (filasCv != null) {

                logger.info("existe el distrito en los ficheros en contenedoresvarios")
                var nombresCv= filasCv.columnNames()

                contenedoresPorTipo = Consultas().getContenedoresDeCadaTipo(filasCv,nombresCv)

            }

        var tFinal = System.currentTimeMillis();
        var tDiference= tFinal - tInit;
        var momentoDeRealizacion = LocalDate.now()


        logger.info("pasando datos al html")
        var html : String = CreateHtml().htmlResumeDistrict(
            toneladasPorResiduo,
            graficoDeTotalToneladas ,
            estadisticasTotales,
            graficoDemaxMinMedYDes,
            contenedoresPorTipo,
            tDiference,
            momentoDeRealizacion
        )

        logger.info("Html Sting creado")

        return html
    }


    /**
            funcion que devuelve un resumen en html del contenido de la secuencia
            resumen.html, aplicándoles los estilos que creas
            oportunos, con la siguiente información:
            - Titulo: Resumen de recogidas de basura y reciclaje de Madrid
            - Fecha de generación: Fecha y hora en formato español.
            - Autores: Nombre y apellidos de los dos autores.
            - Número de contenedores de cada tipo que hay en cada distrito.
            - Media de contenedores de cada tipo que hay en cada distrito.
            - Gráfico con el total de contenedores por distrito.
            - Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
            distrito.
            - Gráfico de media de toneladas mensuales de recogida de basura por distrito.
            - Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
            de basura agrupadas por distrito.
            - Suma de todo lo recogido en un año por distrito.
            - Por cada distrito obtener para cada tipo de residuo la cantidad recogida.
            - Tiempo de generación del mismo en milisegundos.
             */
    fun resumenFrame(pathMR: Path, pathCV: Path, directoriodeResumen: Path): String {

                var tInit = System.currentTimeMillis();

                logger.info("Obtenemos datos del fichero correspondiente a Mr")
                var filasMr: DataFrame<Any?>? = GetDataFrame().dataFrameModeloResiduoTotal(pathMR)

                logger.info("Obtenemos datos del fichero correspondiente a Cv")
                var filasCv: DataFrame<Any?>? = GetDataFrame().dataframeContenedoresVariosTotal(pathCV)

                var numeroContenedoresPorDistrito : DataFrame<Any?>? = null
                var mediaDeContenedoresDeCadaTipo : DataFrame<Any?>? = null
                var  garficoDecontenedoresPorDistrito : Any? = null
                var mediaToneladasAnuales : DataFrame<Any?>? = null
                var mediaToneladasMensuales : DataFrame<Any?>? = null
                var graficoMediaToneladasMensuales : Any? = null
                var maxToneladasPorDistrito : DataFrame<Any?>? = null
                var minToneladasPorDistrito : DataFrame<Any?>? = null
                var medToneladasPorDistrito : DataFrame<Any?>? = null
                var sumToneladasPorDistrito : DataFrame<Any?>? = null
                var totalEstadirticasPorDistrito : DataFrame<Any?>? = null
                var sumToneladasPorDistritoPorTipo : DataFrame<Any?>? = null
                var totalEstadisticasPorAño : DataFrame<Any?>? = null
                var desvToneladasPorDistrito : DataFrame<Any?>? = null


                logger.info("Comprobando que  Cv no este vacio")
                if (filasCv?.count() == 0) {
                    logger.info("no existe datos contenedores varios")
                } else if (filasCv != null) {
                    logger.info("existe datos en contenedores varios")
                    var nombreCol = filasCv.columnNames()

                    numeroContenedoresPorDistrito = Consultas().getNumeroContenedoresPorDistrito(filasCv, nombreCol)

                    mediaDeContenedoresDeCadaTipo = Consultas().getmediaDeContenedoresDeCadaTipo(filasCv, nombreCol)

                    garficoDecontenedoresPorDistrito = Graficos().doGraficoDeMedia(numeroContenedoresPorDistrito, directoriodeResumen, nombreCol)

                }
                logger.info("Comprobando que  Mr no este vacio")
                if (filasMr?.count() == 0) {
                    logger.info("no existe datos Modelo Residuo")
                } else if (filasMr != null) {
                    var nombreCol= filasMr.columnNames()

                    logger.info("existe datos en Modelo Residuo")
                    mediaToneladasMensuales = Consultas().getMediaToneladasMensuales(filasMr,nombreCol)

                    graficoMediaToneladasMensuales = Graficos().getgraficoMediaToneladasMensuales(mediaToneladasMensuales, directoriodeResumen,nombreCol)

                    mediaToneladasAnuales = Consultas().getmediaToneladasAnuales(filasMr,nombreCol)

                    maxToneladasPorDistrito = Consultas().getmaxToneladasPorDistrito(filasMr,nombreCol)

                    minToneladasPorDistrito = Consultas().getminToneladasPorDistrito(filasMr,nombreCol)

                    medToneladasPorDistrito = Consultas().getMedToneladasPorDistrito(filasMr,nombreCol)

                    sumToneladasPorDistrito = Consultas().getsumToneladasPorDistrito(filasMr,nombreCol)

                    desvToneladasPorDistrito = Consultas().getDesToneladasPorDistrito(filasMr,nombreCol)

                    sumToneladasPorDistritoPorTipo = Consultas().sumToneladasPorDistritoPorTipo(filasMr,nombreCol)

                    totalEstadisticasPorAño = Consultas().getEstadisticasTotalPorAño(
                        maxToneladasPorDistrito,
                        minToneladasPorDistrito,
                        medToneladasPorDistrito,
                        desvToneladasPorDistrito,nombreCol
                    )

                }

                var tFinal = System.currentTimeMillis();
                var tDiference= tFinal - tInit;
                var momentoDeRealizacion = LocalDate.now()

                logger.info("pasando datos al html")
                var html : String = CreateHtml().htmlResume(
                    numeroContenedoresPorDistrito ,
                    mediaDeContenedoresDeCadaTipo,
                    garficoDecontenedoresPorDistrito,
                    mediaToneladasAnuales,
                    graficoMediaToneladasMensuales ,
                    totalEstadisticasPorAño ,
                    sumToneladasPorDistrito ,
                    sumToneladasPorDistritoPorTipo,
                    tDiference,
                    momentoDeRealizacion
                )
                logger.info("Html Sting creado")
                return html
            }

    }

