package DataframeUtils

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import interchange.Csv
import interchange.Jsonc
import interchange.Xmlc
import logger
import mappers.MaperModeloResiduo
import mappers.MapperContenedoresVarios
import models.ContenedoresVarios
import models.ModeloResiduo
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.read
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.readJson
import java.nio.file.Path

class GetDataFrame {

    fun dataFrameModeloResiduo(pathMR: Path, district: String): DataFrame<Any?>? {

        if (pathMR.toString().endsWith(".csv")) {
            logger.info("buscando distrito en el fichero Contenedores varios csv")
            return DataFrame.readCSV(pathMR.toFile(), ';')
                .filter { x -> x.getValue<String>("Nombre Distrito").equals(district, true) }


        } else if (pathMR.toString().endsWith(".json")||pathMR.toString().endsWith(".Json")) {
            logger.info("buscando distrito \$district en el fichero Modelo Residio json ")
            logger.info("buscando fichero Modelo Residio json ")

            //pasamos a objetodto
            var dto = Jsonc().readJsontoModeloresiduoDto(pathMR)
            //pasmoa a objeto cara castear Toneladas
            var ob = ArrayList<ModeloResiduo>()
            dto.stream().forEach{x -> ob.add(MaperModeloResiduo().tdoToModrloResiduo(x))}
            var dF = ob.toDataFrame()

            return dF
                .filter{  x -> x.getValue<String>(dF.columnNames().get(5)).equals(district, true) }

        } else if (pathMR.toString().endsWith(".xml")){

            logger.info("buscando los datos del fichero contenedores varios xml ")

            //descargamos datos a dto
            var dto = Xmlc().xmlToModeloresiduoDto(pathMR)

            //descargamos datos a pojo
            var ob =  ArrayList<ModeloResiduo>()
            dto.stream().forEach{x -> ob.add(MaperModeloResiduo().tdoToModrloResiduo(x))}

            var dataFrame = ob.toDataFrame()
            var columnas = dataFrame.columnNames()

            return dataFrame.filter { x -> x.getValue<String>(columnas.get(5)).equals(district, true) }

        }
        logger.info("error devolvemos null")
        return null
    }

    fun dataframeContenedoresVarios(pathCV: Path, district: String): DataFrame<Any?>? {

            if (pathCV.toString().endsWith(".csv")) {

                logger.info("buscando distrito en el fichero Contenedores varios csv")
                var df = DataFrame.readCSV(pathCV.toFile(), ';')
                return df.filter { x -> x.getValue<String>(df.columnNames().get(6)).equals(district, true) }


            } else if (pathCV.toString().endsWith(".json")) {

                logger.info("buscando distrito en el fichero Contenedores varios json")
               var df= DataFrame.readJson(pathCV.toFile())
                    return df.filter { x -> x.getValue<String>(df.columnNames().get(6)).equals(district, true) }

            } else if (pathCV.toString().endsWith(".xml")){
                logger.info("buscando los datos del fichero contenedores varios xml ")

                //descargamos datos a dto
                var dto = Xmlc().xmlToContenedoresVariosDto(pathCV)

                //descargamos datos a pojo
                var ob =  ArrayList<ContenedoresVarios>()
                dto.stream().forEach{x -> ob.add(MapperContenedoresVarios().tdoToContenedoresVarios(x))}

                var dataFrame = ob.toDataFrame()
                var columnas = dataFrame.columnNames()

                return dataFrame.filter { x -> x.getValue<String>(columnas.get(6)).equals(district, true) }






            }
        logger.info("error devolvemos null")
        return null
    }

    fun dataFrameModeloResiduoTotal(pathMR: Path): DataFrame<Any?>? {

        if (pathMR.toString().endsWith(".csv")) {
            logger.info("buscando  MOdelo residuo csv")
            var dF =DataFrame.readCSV(pathMR.toFile(), ';')
            var casteo = dF.cast<ModeloResiduoDTO>()
            return casteo

        } else if(pathMR.toString().endsWith(".json")||pathMR.toString().endsWith(".Json")) {
            logger.info("buscando fichero Modelo Residio json ")

            //pasamos a objetodto
            var dto = Jsonc().readJsontoModeloresiduoDto(pathMR)
            //pasmoa a objeto cara castear Toneladas
            var ob = ArrayList<ModeloResiduo>()
            dto.stream().forEach{x -> ob.add(MaperModeloResiduo().tdoToModrloResiduo(x))}
            //pasamos de nuevo a dataframe
            var dF = ob.toDataFrame()
            var casteo= dF.cast<ModeloResiduoDTO>()
            return casteo

        } else if (pathMR.toString().endsWith(".xml")){
            logger.info("buscando los datos del fichero Modelo Residio xml ")

            //descargamos datos a dto
            var dto = Xmlc().xmlToModeloresiduoDto(pathMR)

            //descargamos datos a pojo
            var ob =  ArrayList<ModeloResiduo>()
            dto.stream().forEach{x -> ob.add(MaperModeloResiduo().tdoToModrloResiduo(x))}

            return ob.toDataFrame()
        }
        logger.info("error devolvemos null")
        return null
    }

    fun dataframeContenedoresVariosTotal(pathCV: Path) : DataFrame<Any?>?{


        if (pathCV.toString().endsWith(".csv")) {
            var lista = Csv().csvToContenedoresVarios(pathCV)

            logger.info("buscando  Contenedores varios csv")
            var dF =lista.toDataFrame()
            var casteo = dF.cast<ContenedoresVariosDTO>()

            return casteo

        } else if (pathCV.toString().endsWith(".json")) {
            logger.info("buscando  Contenedores varios json")
            var dF = DataFrame.readJson(pathCV.toFile())
            var casteo = dF.cast<ContenedoresVariosDTO>()

            return casteo

        } else  if (pathCV.toString().endsWith(".xml")){

            logger.info("buscando los datos del fichero contenedores varios xml ")

            //descargamos datos a dto
            var dto = Xmlc().xmlToContenedoresVariosDto(pathCV)

            //descargamos datos a pojo
            var ob =  ArrayList<ContenedoresVarios>()
            dto.stream().forEach{x -> ob.add(MapperContenedoresVarios().tdoToContenedoresVarios(x))}

            return ob.toDataFrame()

        }
        logger.info("error devolvemos null")
        return null
    }
}