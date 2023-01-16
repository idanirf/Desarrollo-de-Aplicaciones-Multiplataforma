package html

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.html
import org.jetbrains.kotlinx.dataframe.io.toHTML
import java.time.LocalDate

class CreateHtml {



    fun htmlResumeDistrict(
        toneladasPorResiduo: DataFrame<Any?>?,
        graficoDeTotalToneladas: Any?,
        estadisticasTotales: DataFrame<Any?>?,
        graficoDemaxMinMedYDes: Any?,
        contenedoresPorTipo: DataFrame<Any?>?,
        tDiference: Long,
        momentoDeRealizacion: LocalDate
    ): String {


        var html : String = ("""
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <title>Estadísticas</title>
            </head>
            <body>
            <div style="background-color:  #86b754 ; border-width: 10px; border-style: solid; border-color: #599459 ; text-align: center;
             top: 0;  width: 100%;   position:fixed; line-height:100%; text-color: #355835 ;">
                <h1>Resumen de recogidas de basura y reciclaje de Madrid</h1>
            </div>
            <div style="background-color: #b9f3b9  ;">
                //quiero que este div pueda desplazarse hacia arriva y hacia abajo
                //quiero que los dos divs de dentro se distingan por colores
                <div>

                    <ul style="list-style-type: none">
                        <li>
                            <br>
                            <br>
                            <br>
                            <h2>Contenidos</h2>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;" >1. Número de contenedores de cada tipo que hay en este distrito.</a> <br>
                           
                        </li>
                        <li> <br>
                            <a href="" style="color: #355835 ;">2. Total de toneladas recogidas en ese distrito por residuo.</a> <br>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;">3.Gráfico con el total de toneladas por residuo en ese distrito.</a>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;">4.Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.</a>
                        </li>
                        <li> <br>
                            <a href=""style="color: #355835 ;">5.Gráfica del máximo, mínimo y media por meses en dicho distrito.</a>
                        </li>
                        

                    </ul>
                    <br>
                    <br>
                </div>
                <div>

                    <ul>
                        <li>
                            <h2>1. Número de contenedores de cada tipo que hay en este distrito.</h2>
                             <br>
                             ${contenedoresPorTipo?.html()}

                        </li>
                        <li>
                            <h2>2. Total de toneladas recogidas en ese distrito por residuo.</h2>
                            <br>
                             ${toneladasPorResiduo?.html()} 
                            
                        </li>
                        <li>
                            <h2>3.Gráfico con el total de toneladas por residuo en ese distrito.</h2>
                             <br>
                             <img src=" $graficoDeTotalToneladas" alt ="grafica1">
                            
                             
                     
                        </li>
                        <li>
                            <h2>4.Máximo, mínimo , media y desviación por mes por residuo en dicho distrito.</h2>
                            <br>
                           ${estadisticasTotales?.toHTML()}
                           
                            
                     
                        </li>
                        <li>
                            <h2>5.Gráfica del máximo, mínimo y media por meses en dicho distrito.</h2>
                            <br>
                            <img src=" $graficoDemaxMinMedYDes" alt ="grafica1">
                        </li>
                        
                    </ul>

                </div>
            </div>

            <div style="background-color: #804000 ; border-width: 2px; border-style: solid; border-color: #000000; text-align: center;
             bottom: 0;  width: 100%;  font-size: 15px; line-height:60%">
                <p>Autores: Azahara Blanco Rodríguez y Daniel Rodriguez Sanchez</p>
                <p> Fecha de generación de documento: $momentoDeRealizacion Tiempo de generación del mismo en milisegundos : $tDiference </p>
            </div>



            </body>
            </html>
        """.trimIndent()




        )
        return html

    }

    fun htmlResume(numeroContenedoresPorDistrito: DataFrame<Any?>?,
                   mediaDeContenedoresDeCadaTipo: DataFrame<Any?>?,
                   garficoDecontenedoresPorDistrito: Any?,
                   mediaToneladasAnuales: DataFrame<Any?>?,
                   graficoMediaToneladasMensuales: Any?,
                   totalEstadisticasPorAño: DataFrame<Any?>?,
                   sumToneladasPorDistrito: DataFrame<Any?>?,
                   sumToneladasPorDistritoPorTipo: DataFrame<Any?>?,
                   tDiference: Long, momentoDeRealizacion: LocalDate?): String {


        var html = """<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Estadísticas</title>
</head>
<body>
<div style="background-color:  #86b754 ; border-width: 10px; border-style: solid; border-color: #599459 ; text-align: center;
 top: 0;  width: 100%;   position:fixed; line-height:100%; text-color: #355835 ;">
    <h1>Resumen de recogidas de basura y reciclaje de Madrid</h1>
</div>
<div style="background-color: #b9f3b9  ;">
    //quiero que este div pueda desplazarse hacia arriva y hacia abajo
    //quiero que los dos divs de dentro se distingan por colores
    <div>

        <ul style="list-style-type: none">
            <li>
                <br>
                <br>
                <br>
                <h2>Contenidos</h2>
            </li>
            <li> <br>
                <a href=""style="color: #355835 ;" >Número de contenedores de cada tipo que hay en cada distrito.</a> <br>
                
            </li>
            <li> <br>
                <a href="" style="color: #355835 ;">Media de contenedores de cada tipo que hay en cada distrito.</a> <br>
                
            </li>
            <li> <br>
                <a href=""style="color: #355835 ;">Gráfico con el total de contenedores por distrito.</a>
           
            </li>
            <li> <br>
                <a href=""style="color: #355835 ;">Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
                    distrito.</a>
                    
            </li>
            <li> <br>
                <a href=""style="color: #355835 ;"> Gráfico de media de toneladas mensuales de recogida de basura por distrito.</a>
           
            </li>
            <li> <br>
                <a href=""style="color: #355835 ;">Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
                    de basura agrupadas por distrito.</a>
                   
            </li>
            <li> <br>
                <a href=""style="color: #355835 ;">Suma de todo lo recogido en un año por distrito.</a>
                
            </li>
            <li> <br>
                <a href=""style="color: #355835 ;">Por cada distrito obtener para cada tipo de residuo la cantidad recogida.</a>
            </li>

        </ul>
        <br>
        <br>
    </div>
    <div>

        <ul>
            <li>
                <h2>Número de contenedores de cada tipo que hay en cada distrito.</h2>
                 ${numeroContenedoresPorDistrito?.html()}

            </li>
            <li>
                <h2>Media de contenedores de cada tipo que hay en cada distrito.</h2>
                 ${mediaDeContenedoresDeCadaTipo?.html()}
            </li>
            <li>
                <h2>Gráfico con el total de contenedores por distrito.</h2>
                 <img src=" ${garficoDecontenedoresPorDistrito}" alt ="grafica1">
            </li>
            <li>
                <h2>Media de toneladas anuales de recogidas por cada tipo de basura agrupadas por
                    distrito.</h2>
                    ${mediaToneladasAnuales?.html()}
            </li>
            <li>
                <h2>Gráfico de media de toneladas mensuales de recogida de basura por distrito.</h2>
                 <img src=" ${graficoMediaToneladasMensuales}" alt ="grafica1">
            </li>
            <li>
                <h2>Máximo, mínimo , media y desviación de toneladas anuales de recogidas por cada tipo
                    de basura agrupadas por distrito.</h2>
                     ${totalEstadisticasPorAño?.html()}
            </li>
            <li>
                <h2>Suma de todo lo recogido en un año por distrito.</h2>
                 ${sumToneladasPorDistrito?.html()}
            </li>
            <li>
                <h2>Por cada distrito obtener para cada tipo de residuo la cantidad recogida.</h2>
                ${sumToneladasPorDistritoPorTipo?.html()}
            </li>

        </ul>

    </div>
</div>

<div style="background-color: #804000 ; border-width: 2px; border-style: solid; border-color: #000000; text-align: center;
 bottom: 0;  width: 100%;  font-size: 15px; line-height:60%">
    <p>Autores: Azahara Blanco Rodríguez y Daniel Rodriguez Sanchez</p>
    <p> Fecha de generación de documento: $momentoDeRealizacion. Tiempo de generación del mismo en milisegundos : $tDiference</p>
</div>



</body>
</html>"""

        return html
    }

}