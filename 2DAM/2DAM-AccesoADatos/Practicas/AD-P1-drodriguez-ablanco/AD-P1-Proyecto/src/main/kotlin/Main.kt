
import Resume.ResumenDataFrame
import chekData.CheckData
import com.sun.source.tree.TryTree
import dataOfUse.DataofUse
import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import html.CreateFileHtml

import interchange.Csv
import interchange.Jsonc
import interchange.Xmlc
import mappers.MaperModeloResiduo
import mappers.MapperContenedoresVarios
import models.ContenedoresVarios
import models.ModeloResiduo
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Logger


val logger: Logger = Logger.getLogger("Azahara y Dani Log")


//con esto lo probamos
//val path : String= Paths.get("").toAbsolutePath().toString()+ File.separator + "data"

//para probar el parser
//private val strings = arrayOf("parser", path, path+File.separator + "copia")

//para probar el resume

//private val strings = arrayOf("resumen", path, path+File.separator + "copia")

//para probar el resume district
//private val strings = arrayOf("resumen","CARABANCHEL", path, path+File.separator + "copia")


fun main(args: Array<String>) {

    logger.info(" Iniciando Programa")

    //eliminar
    //val args : Array<String> = strings

    //donde vamos a guardar los datos
    val stringOfData =Paths.get("").toAbsolutePath().toString()+ File.separator +
            "data"+File.separator +"DataOfAllUses"+File.separator +"datos.xml"




    val election : Int  = getElection(args)


    when (election){
        1 -> beginingParser(args,stringOfData)
        2 -> beginingSumary(args,stringOfData)
        3-> beginingSumary(args,stringOfData)
        4 -> opcionIncorrecta(stringOfData)
    }

}

/**
 * Función que se inicia cuando la opción escogida no es correcta:
 * guarda datos en Data of use y Lanza Mensaje de error
 */
fun opcionIncorrecta(stringOfData: String) {

    var tInit = System.currentTimeMillis();

    logger.info("La opción selecionada no es correcta ")

    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = "Error", exito = false , tiempoEjecucion = tDiference)
    logger.info(data.toString() + "escritos en DataOfUse")
    Xmlc().writeData( Path.of(stringOfData),data)

    println("Realizado sin éxito : La opción no es correcta.")

}

/**
 Función tomar los ficheros csv
del directorio origen y transformar en JSON y XML y CSV en el directorio destino.
 */
fun beginingParser(args: Array<String>, stringOfData : String) {

    var tInit = System.currentTimeMillis();
    var areCorrectDataInFiles = true

    logger.info(" Seleccionando y leyendo los ficheros ")


    var arrayListOfModeloResiduo : ArrayList<ModeloResiduoDTO>? = getModeloResiduotoCSV(args)
    var arrayListOfContenedoreVarios: ArrayList<ContenedoresVariosDTO>? = getContenedoresVariosCSV(args)


    logger.info(" leidos los dos ficheros ")

    if (arrayListOfModeloResiduo!=null){
        logger.info(" leidos correctamente el fichero de Modelo resudio")
        areCorrectDataInFiles = createFilesModeloresiduo(arrayListOfModeloResiduo, args)

        if (arrayListOfContenedoreVarios!=null){
            logger.info(" leidos correctamente el fichero de contenedores varios")
            areCorrectDataInFiles = createFilesContenedoreVarios(arrayListOfContenedoreVarios, args)
        }else{
            logger.info(" no se ha encontrado un fichero adecuado para la lectura de COntenedores varios")
            areCorrectDataInFiles = false
        }

    }else{
        logger.info(" no se ha encontrado un fichero adecuado para la lectura de Modelo residuo")
        areCorrectDataInFiles = false
    }

    
    logger.info("fin de tarea ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = "Parser", exito = areCorrectDataInFiles , tiempoEjecucion = tDiference)
    logger.info(data.toString())
    Xmlc().writeData( Path.of(stringOfData),data)
    logger.info("escrito datos")

    if (areCorrectDataInFiles){
        println("Realizado el Parser con exito.")
    }else{
        println("No realizado con exito: Los datos facilitados no son correctos.")
    }


}



private fun createFilesContenedoreVarios(
    arrayListOfContenedoreVarios: ArrayList<ContenedoresVariosDTO>,
    args: Array<String>): Boolean {
    try{

    logger.info(" creamos ficheros contenedores varios ")
    logger.info(" csv ")
    Csv().ContenedoresVariosToCsv(arrayListOfContenedoreVarios, Path.of(args[2]))
    logger.info(" json")
    Jsonc().contenedoresVariosToAJson(Path.of(args[2]), arrayListOfContenedoreVarios)
    logger.info(" xml ")
    Xmlc().contenedoresVariosDtoToXml(
        arrayListOfContenedoreVarios,
        Path.of(args[2] + File.separator + "contenedores_varios.xml")
    )

    }catch (e : Exception){
    logger.info("No se han hecho los ficheros correctamente")
        return false
    }
    return true
}

private fun createFilesModeloresiduo(
    arrayListOfModeloResiduo: ArrayList<ModeloResiduoDTO>,
    args: Array<String>
) : Boolean {
    try {
        logger.info(" creamos ficheros Modelo residuo ")
        logger.info(" csv ")
        Csv().ModeloRosiduoToCsv(arrayListOfModeloResiduo, Path.of(args[2]))
        logger.info(" json")
        Jsonc().modeloResiduoDtoToAJson(Path.of(args[2]), arrayListOfModeloResiduo)
        logger.info(" xml ")
        Xmlc().modeloResiduoDtoToXml(
            arrayListOfModeloResiduo,
            Path.of(args[2] + File.separator + "modelo_residuos.xml")
        )
    }catch (e : Exception){
        logger.info("No se han hecho los ficheros correctamente")
        return false
    }
    return true

}
fun getModeloResiduotoCSV(args: Array<String>): ArrayList<ModeloResiduoDTO>? {

    logger.info(" Cogiendo datos de archivo Modelo residuo ")

    var pathCorrecta = CheckData().encontrarFicherosCorrectosEnELDirectoriodeModeloResiduo(Path.of(args[1]))

    if (pathCorrecta!=null){
        try {
            return  Csv().csvToMoeloResiduo(pathCorrecta)
        }catch (e : Exception){
            logger.info("el fichero en csv no se ha podido leer porque no es el formato correcto.")
        }
    }
    return null
}

private fun getContenedoresVariosCSV(args: Array<String>): ArrayList<ContenedoresVariosDTO>? {
    logger.info(" cogiendo datos de contenedores Varios")
 
    var pathCorrecta = CheckData().encontrarFicherosCorrectosEnELDirectoriodeContenedoresVarios(Path.of(args[1]))

    if (pathCorrecta!=null){
        if (pathCorrecta.toString().endsWith(".csv")){
            return  Csv().csvToContenedoresVarios(pathCorrecta)
        }
    }
    return null
}

    fun doResumen(s: String, pathContenedoresVarios: Path, pathModeloResiduo: Path, stringOfData: String, directoriodeResumen: Path): String {

    logger.info("los datos de la path son correctos")

    var exito = false

    var html : String = ""

    if (s.equals("")){

        logger.info("entramos a la opcion resume all  porque no hay distrito $s")
        html = ResumenDataFrame().resumenFrame(pathModeloResiduo, pathContenedoresVarios,directoriodeResumen)

    }else{

        logger.info("entramos a la opcion resume distrito porque el distrito es  $s")
        html = ResumenDataFrame().resumeDistrictFrame(pathModeloResiduo, pathContenedoresVarios, s,directoriodeResumen)
    }


    logger.info("fin de tarea ")

    return html
        
        
    }

    /**
 *
resumen path parh
puede leer de csv json o xml, pero tiene que tener todos los datos
funcion que comprueva los args y si son ciestos debe tomar la información
de los contenedores y de la recogida, independientemente de la extensión que tenga (si no
corresponde a la extensión o al formato deberá indicar error) y deberá procesarla
generando en directorio_destino un resumen.html, aplicándoles los estilos
 */
fun  beginingSumary(args: Array<String>, stringOfData: String) {
    logger.info("entramos en beginingSumaryAll")

    //para ver el tiempo que tarda
    var tInit = System.currentTimeMillis();

    //para ver si ha tenido exito o no
    var exito = false
    var tipoOpcion ="resumen"
    var directorioDeorigen = Path.of(args[1])
    var directoriodeResumen = Path.of(args[2])
    var distrito = ""


    if (args.size==4){
        tipoOpcion="resumen district"
        directorioDeorigen = Path.of(args[2])
        directoriodeResumen = Path.of(args[3])
        logger.info("la opcion es resumen district porque los args son 4")
        distrito= args[1]
    }

        //1.sacar todos los ficheros del directorio

        if (Files.notExists(directorioDeorigen) && !Files.isDirectory(directorioDeorigen)){
            logger.info("el path de los archivos No exixte o NO es un directorio")

        }else{
            logger.info("el path de los archivos exixte y es un directorio")

            var pathModeloResiduo : Path? = null
            try {
                pathModeloResiduo = CheckData().encontrarFicherosCorrectosEnELDirectoriodeModeloResiduo(directorioDeorigen)

            }catch (e: Exception){
                exito= false
            }


            if (pathModeloResiduo==null){
                logger.info("no hay ningun archivo en la path que contenga los datos necesarios ")
            }else{
                logger.info("exixte un fichero con los datos necesarios para modelo residuo, buscamos para contenedores varios")

                var pathContenedoresVarios : Path? = null
                try {
                    pathContenedoresVarios = CheckData().encontrarFicherosCorrectosEnELDirectoriodeContenedoresVarios(directorioDeorigen)
                }catch (e: Exception){
                    exito= false
                }



                if (pathContenedoresVarios==null){
                    logger.info("no exixte ningun fichero que contenga las columnas y en el orden necesarios para crear Contenedores vartios")

                    exito = false
                }else{
                    var html : String = ""

                    logger.info("exixten los dos archivos necesarios para hacer el resumen")
                    if(tipoOpcion == "resumen district"){


                        html = doResumen(args[1],pathContenedoresVarios,pathModeloResiduo, stringOfData,directoriodeResumen)
                    }else{

                        html = doResumen("",pathContenedoresVarios,pathModeloResiduo, stringOfData,directoriodeResumen)
                    }

                    if (html.equals("")){
                        logger.info("no se ha podido hacer el html")
                        exito=false
                    }else{
                        logger.info("convirtiendo a html")

                        exito = CreateFileHtml().create( distrito, directoriodeResumen, html)

                    }
                }
            }
        }


    logger.info("fin de tarea ")

    //para ver cuanto tarda
    var tFinal = System.currentTimeMillis();
    var tDiference= tFinal - tInit;

    var data = DataofUse(tipoOpcion = tipoOpcion, exito = exito , tiempoEjecucion = tDiference)
    logger.info(data.toString())
    Xmlc().writeData( Path.of(stringOfData),data)
    logger.info("escrito datos")

        if (exito){
            println("Realizado el Resumen con exito.")
        }else{
            println("No realizado Resumen con exito: Los datos facilitados no son correctos.")
        }

}
    fun doResume(s: String, pathContenedoresVarios: Path, pathModeloResiduo: Path, stringOfData: String, directoriodeResumen: Path): String {
        logger.info("los datos de la path son correctos")
        println("path de contenedores varios en do resumen es : " + pathContenedoresVarios)
        println("path de Modelo residuo en do resumen es : " + pathModeloResiduo)

        var tipoOpcion = ""
        var exito = false

        var html : String = ""

        if (s.equals("")){
            tipoOpcion="resume all"
            logger.info("entramos a la opcion resume all  porque no hay distrito $s")
            html = ResumenDataFrame().resumenFrame(pathModeloResiduo, pathContenedoresVarios,directoriodeResumen)

        }else{
            tipoOpcion="resume District"
            logger.info("entramos a la opcion resume distrito porque el distrito es  $s")
            html = ResumenDataFrame().resumeDistrictFrame(pathModeloResiduo, pathContenedoresVarios,s,directoriodeResumen)
        }

        logger.info("fin de tarea ")

        return html
    }


private fun getModeloResiduoDtoToFile(
    pathOfContenedoresVarios: Path,
    arrayListOfModeloResiduoDTO: List<ModeloResiduoDTO>,
    pathDeModeloResiduo: Path
) {
    var arrayListOfModeloResiduoDTO1 = arrayListOfModeloResiduoDTO
    if (pathOfContenedoresVarios.endsWith(".json")) {
        logger.info(" cogiendo datos de contenedores Varios desde json")
        arrayListOfModeloResiduoDTO1 = Jsonc()
            .readJsontoModeloresiduoDto(pathDeModeloResiduo)

    } else if (pathOfContenedoresVarios.endsWith(".csv")) {
        logger.info(" cogiendo datos de contenedores Varios desde csv")
        arrayListOfModeloResiduoDTO1 = Csv()
            .csvToMoeloResiduo(pathDeModeloResiduo)

    } else {
        logger.info(" cogiendo datos de contenedores Varios desde xml")
        arrayListOfModeloResiduoDTO1 = Xmlc()
            .xmlToModeloresiduoDto(pathDeModeloResiduo)
    }
}

private fun getContenedoresVariosDtoToFile(
    pathOfContenedoresVarios: Path,
    arrayListOfContenedoreVariosDTO: List<ContenedoresVariosDTO>
): List<ContenedoresVariosDTO> {
    var arrayListOfContenedoreVariosDTO1 = arrayListOfContenedoreVariosDTO
    if (pathOfContenedoresVarios.endsWith(".json")) {
        logger.info(" cogiendo datos de contenedores Varios desde json")
        arrayListOfContenedoreVariosDTO1 = Jsonc()
            .readJsontoContenedoresvariosDto(pathOfContenedoresVarios)

    } else if (pathOfContenedoresVarios.endsWith(".csv")) {
        logger.info(" cogiendo datos de contenedores Varios desde csv")
        arrayListOfContenedoreVariosDTO1 = Csv()
            .csvToContenedoresVarios(pathOfContenedoresVarios)

    } else {
        logger.info(" cogiendo datos de contenedores Varios desde xml")
        arrayListOfContenedoreVariosDTO1 = Xmlc()
            .xmlToContenedoresVariosDto(pathOfContenedoresVarios)
    }
    return arrayListOfContenedoreVariosDTO1
}

fun doMappetToContenedresVarios(array: ArrayList<ContenedoresVariosDTO>):
        ArrayList<ContenedoresVarios> {
    logger.info("entramos")

    var mapper = MapperContenedoresVarios()
    var arrayOfContenedoresVarios = ArrayList<ContenedoresVarios>()

    try {
        //aqui falla pero no se porque
        //por cada uno lo mapeamos y guardamos
         array.stream().forEach { x -> arrayOfContenedoresVarios.add(mapper.tdoToContenedoresVarios(x))}

    }catch (e: Exception){
        logger.info("no se ha conseguido pasar de modelo a object")
        e.printStackTrace()
    }
    return arrayOfContenedoresVarios

}

fun doMappetToModeloResiduo(array: ArrayList<ModeloResiduoDTO>):
        ArrayList<ModeloResiduo> {
    logger.info("entramos")

    var mapper = MaperModeloResiduo()
    var arrayOfModeloResiduo = ArrayList<ModeloResiduo>()

    try {
        //por cada uno lo mapeamos y guardamos
        array.stream().forEach(){ x -> arrayOfModeloResiduo.add(mapper.tdoToModrloResiduo(x))}

    }catch (e: Exception){
        logger.info("no se ha conseguido pasar de modelo a object")
        e.printStackTrace()
    }
    return arrayOfModeloResiduo
}


/**
pasados los parametros del programa devuelve un Int entre 1 y 3 que indica la elecion escogida
por la persona que ha pasado los parametros
3- resumende distrito
2- resumen total
1- parser
 */
fun getElection(args: Array<String>):Int{
    if (args.size<3){return 4}
    logger.info(" Entrado en get Elecion ")

    if(args.size == 4){
        logger.info(" como los args son 4 es la opcion resume district ")
        if(args[0]=="resumen"){
            logger.info("el rimer args en resume, por lo que la elecion en 3 resumen district")
            return 3
        }else{
            logger.info("la opcion no es correcta")
            return  4
        }
    }else if(args[0]=="resumen" && args.size == 3){
        logger.info(" como los args son 3  y es la primera opcion es resumen devuelve elecion 2 resumen all ")
        return 2
    }else if(args.size==3 && args[0]=="parser"){
        logger.info(" los args son 3 y opcion es parser por lo que es parser opcion 1 ")
        return 1
    }else{
        logger.info("los parametros pasados no son ninguno de los anteriores")
        return 4}

}

