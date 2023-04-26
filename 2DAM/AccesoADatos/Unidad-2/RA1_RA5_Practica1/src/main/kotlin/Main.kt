import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import controllers.DataframeController
import models.*
import org.jetbrains.kotlinx.dataframe.api.*
import org.simpleframework.xml.core.Persister
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalTime
import java.util.stream.Collectors
import kotlin.collections.forEach

fun main(args: Array<String>) {
    var inArgs = ArrayList<String>()

    println("Lectura de datos AEMET")

    inArgs.add("-d")
    inArgs.add(
        "C:" + File.separator + "Users" + File.separator + "danie" + File.separator + "Proyectos" +
                File.separator + "RA1_RA5_Practica1" + File.separator + "data" +
                File.separator + "destino"
    )
    inArgs.add("-o")
    inArgs.add(
        "C:" + File.separator + "Users" + File.separator + "danie" + File.separator + "Proyectos" +
                File.separator + "RA1_RA5_Practica1" + File.separator + "data" +
                File.separator + "origen"
    )
    var pathProvisionalEntrada = ""
    var pathProvisionalSalida = ""
    var completeList = ArrayList<Aemet>()
    val serializer = Persister()
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    if (inArgs.size != 4) {
        println("Los args introducidos no son correctos")
        System.exit(0)
    } else {
        if (inArgs.get(0) == "-d" && inArgs.get(2) == "-o") {
            pathProvisionalEntrada = inArgs[3]
            pathProvisionalSalida = inArgs[1]
        } else if (args.get(2) == "-d" && inArgs.get(0) == "-o") {
            pathProvisionalEntrada = inArgs[1]
            pathProvisionalSalida = args[3]
        } else {
            println("Hemos comprobado los args y no son correctos")
            System.exit(0)
        }
    }


    if (Path.of(pathProvisionalEntrada).toFile().exists()
        && Path.of(pathProvisionalEntrada).toFile().isDirectory
        && Path.of(pathProvisionalEntrada).toFile().canRead()
    ) {
        var files = Files.list(Path.of(pathProvisionalEntrada))
        files.forEach { f ->
            if (f.toFile().isFile && f.toFile().canRead()) {
                getData(f, completeList)
            }
        }
    } else {
        println("Directorio de entrada no encontrado/No se encuentran los ficheros de lectura")
        System.exit(0)
    }

    if (Path.of(pathProvisionalSalida).toFile().exists()
        && Path.of(pathProvisionalSalida).toFile().isDirectory
        && Path.of(pathProvisionalSalida).toFile().canWrite()
    ) {
    } else {
        try {
            Files.createDirectory(Path.of(pathProvisionalSalida))
        } catch (e: Exception) {
            println("No se ha podido crear el directorio de origen de datos")
            System.err.println("El directorio de origen de datos no se puede crear")
            System.exit(0)
        }
    }

    println("Creando CSV general")
    createdCsv(completeList)
    println("Fichero CSV creado")
    println("Creando XML general")
    createdXMLMalaForma(completeList)
    println("Fichero XML creado")
    println("Creando JSON general")
    createdJsonMalaForma(completeList)
    println("Fichero JSON creado")
    println("Creando informe")
    println("Informe creado")
    generarInformePorDiaYLocalidad(completeList)
    generarInformeTemperaturaMinimaPorDiaYLocalidad(completeList)
    generarInformeTemperaturaMaximaPorProvincia(completeList)
    generarInformeTemperaturaMinimaPorProvincia(completeList)
    generarInformeTemperaturaMediaPorProvincia(completeList)
    generarInformePrecipitacionMediaPorProvincia(completeList)
    generarInformeLugaresLluviaPorProvincia(completeList)
    generarInformeTemperaturaMediaMadrid(completeList)
    generarInformeTemperaturaMaximaMediaTotal(completeList)
    generarInformeTemperaturaMinimaMediaTotal(completeList)
    generarInformeTemperaturaMaximaAntesDeLas15(completeList)
    generarInformeTemperaturaMinimaDespuesDeLas1730(completeList)
    informeMadrid(completeList, pathProvisionalSalida, serializer, moshi)
}

fun getData(f: Path?, completeList: ArrayList<Aemet>) {
    if (f != null) {
        var lineas = f.toFile().readLines()
        lineas.forEach { l ->
            var columnas = l.split(";")
            completeList.add(
                Aemet(
                    getDate(f),
                    columnas[0].trim(), //localidad
                    columnas[1].trim(), // provincia
                    columnas[2].toDouble(), // temperaturaMax
                    LocalTime.of(
                        columnas[3].split(":")[0].toInt(),
                        columnas[3].split(":")[1].toInt()
                    ), // horaRegistroTempMax
                    columnas[4].toDouble(), // temperaturaMin
                    LocalTime.of(
                        columnas[5].split(":")[0].toInt(),
                        columnas[5].split(":")[1].toInt()
                    ), // horaRegistroTempMin
                    columnas[6].toDouble(), // precipitacion
                )
            )
        }
    }
}

fun informeMadrid(completeList: ArrayList<Aemet>, pathProvisionalSalida: String, serializer: Persister, moshi: Moshi) {
    //datos a sacar
    var temperaturaMax = ""
    var temperaturaMaxLugar = ""
    var temperaturaMaxMomento = ""
    var temperaturaMin = ""
    var temperaturaMinLugar = ""
    var temperaturaMinMomento = ""
    var temperaturaMed = ""
    var isPrecipitaciones = ""
    var precipitacion = 0.0

    var listaDeDatosParaInformePadrid = ArrayList<DiaEnMadrid>()

    listaDeDatosParaInformePadrid.clear()
    completeList.stream()
        .filter { x -> x.provincia.equals("madrid", true) }
        .collect(Collectors.groupingBy<Aemet?, LocalDate?> { it.dia })
        .forEach { it ->
            temperaturaMax =
                it.value.stream().map { it.temperaturaMax ?: 0.0 }.max { o1, o2 -> (o1 - o2).toInt() }.get().toString()
            temperaturaMaxLugar =
                it.value.stream().filter { it.temperaturaMax.toString().equals(temperaturaMax, true) }.findFirst()
                    .get().localidad.toString()
            temperaturaMaxMomento =
                it.value.stream().filter { it.temperaturaMax.toString().equals(temperaturaMax, true) }.findFirst()
                    .get().horaRegistroTempMac.toString()

            temperaturaMin =
                it.value.stream().map { it.temperaturaMin ?: 0.0 }.min { o1, o2 -> (o1 - o2).toInt() }.get().toString()
            temperaturaMinLugar =
                it.value.stream().filter { it.temperaturaMin.toString().equals(temperaturaMin, true) }.findFirst()
                    .get().localidad.toString()
            temperaturaMinMomento =
                it.value.stream().filter { it.temperaturaMin.toString().equals(temperaturaMin, true) }.findFirst()
                    .get().horaRegistroTempMin.toString()

            var totalTemperaturas = 0.0
            it.value.stream().forEach { x ->
                totalTemperaturas =
                    ((x.temperaturaMax?.plus(x.temperaturaMin!!))?.div(2.0))?.plus(totalTemperaturas) ?: 0.0
            }
            temperaturaMed = (totalTemperaturas / it.value.size).toString()


            it.value.stream().forEach { precipitacion = precipitacion + it.precipitacion!! }
            isPrecipitaciones = (precipitacion != 0.0).toString()

            listaDeDatosParaInformePadrid.add(
                DiaEnMadrid(
                    fecha = it.key.toString(),
                    datosInforme = ListaDatosMadrid( //que tiene los siquientes datso,
                        temperaturaMedia = temperaturaMed,
                        temperaturaMáxima = temperaturaMax,
                        temperaturaMáximaLugar = temperaturaMaxLugar,
                        temperaturaMáximaMomento = temperaturaMaxMomento,
                        temperaturaMinima = temperaturaMin,
                        temperaturaMinimaLugar = temperaturaMinLugar,
                        temperaturaMinimaMomento = temperaturaMinMomento,
                        siHuboPrecipitación = isPrecipitaciones,
                        precipitacion = precipitacion.toString()
                    )
                )
            )
        }

    val fileInformeXmlStream = File(pathProvisionalSalida + File.separator + "InformeXmlStream.xml")

    if (!fileInformeXmlStream.exists()) {
        fileInformeXmlStream.createNewFile()
    }

    var informeStream = Informe(lista = listaDeDatosParaInformePadrid.toList())

    serializer.write(informeStream, fileInformeXmlStream)

    val fileInformeJsonStream = File(pathProvisionalSalida + File.separator + "InformeJsonStream.json")

    if (!fileInformeJsonStream.exists()) {
        fileInformeJsonStream.createNewFile()
    }

    val adapterInforme: JsonAdapter<List<DiaEnMadrid>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, DiaEnMadrid::class.java))

    var textoJsonInformeStream: String = adapterInforme.indent(" ").toJson(informeStream.lista)
    fileInformeJsonStream.writeText(textoJsonInformeStream)

    var dfController = DataframeController()

    var df = completeList.toDataFrame().cast<Aemet>()
        .filter { x -> x.getValue<String>("provincia").equals("madrid",true)}
        .groupBy("dia")
        .forEach { group ->
            //hacemos dia por cada uno

            listaDeDatosParaInformePadrid.add( DiaEnMadrid(   //al informe le añadimos el dia
                fecha = group.key.toString(),
                datosInforme = ListaDatosMadrid(    //que tiene los siquientes datso,
                    // esto lo hacemos para que la extructura sea tal cual se pidio en el xml
                    temperaturaMedia = temperaturaMed,
                    temperaturaMáxima = temperaturaMax,
                    temperaturaMáximaLugar = temperaturaMaxLugar,
                    temperaturaMáximaMomento = temperaturaMaxMomento,
                    temperaturaMinima = temperaturaMin,
                    temperaturaMinimaLugar = temperaturaMinLugar,
                    temperaturaMinimaMomento = temperaturaMinMomento,
                    siHuboPrecipitación = isPrecipitaciones,
                    precipitacion = precipitacion.toString()
                )
            )
            )
        }


    var informeDataframe = Informe(lista =listaDeDatosParaInformePadrid.toList() )

    val fileInformeMadXml = File(pathProvisionalSalida + File.separator + "InformeMadXmlDataFrame.xml")

    if (!fileInformeMadXml.exists()) {
        fileInformeMadXml.createNewFile()
    }

    var informeDataFrameJson = Informe(lista = listaDeDatosParaInformePadrid.toList())

    serializer.write(informeDataframe, fileInformeMadXml)

    val fileInformeJsonDataFrame = File(pathProvisionalSalida + File.separator + "InformeJsonDataFrame.json")

    if (!fileInformeJsonDataFrame.exists()) {
        fileInformeJsonDataFrame.createNewFile()
    }

    val adapterInformeDataFrame: JsonAdapter<List<DiaEnMadrid>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, DiaEnMadrid::class.java))

    var textoJsonInformeDataFrame: String = adapterInformeDataFrame.indent(" ").toJson(informeDataFrameJson.lista)
    fileInformeJsonDataFrame.writeText(textoJsonInformeDataFrame)
}



fun generarInformePorDiaYLocalidad(aemetList: List<Aemet>) {
    val informe = aemetList
        .filter { it.temperaturaMax != null }
        .groupBy { Pair(it.dia, it.localidad) }
        .mapValues {
            it.value.maxOfOrNull { aemet -> aemet.temperaturaMax ?: Double.NEGATIVE_INFINITY } ?: 0.0
        }

    informe.forEach { (key, value) -> println("Temperatura máxima el día ${key.first} en ${key.second}: $value") }
}

fun generarInformeTemperaturaMinimaPorDiaYLocalidad(aemetList: List<Aemet>) {
    val informe = aemetList
        .filter { it.temperaturaMin != null }
        .groupBy { Pair(it.dia, it.localidad) }
        .mapValues {
            it.value.minOfOrNull { aemet -> aemet.temperaturaMin ?: Double.POSITIVE_INFINITY } ?: 0.0
        }

    informe.forEach { (key, value) -> println("Temperatura mínima el día ${key.first} en ${key.second}: $value") }
}

fun generarInformeTemperaturaMaximaPorProvincia(aemetList: List<Aemet>) {
    val informe = aemetList
        .filter { it.temperaturaMax != null }
        .groupBy { Triple(it.dia, it.provincia, it.localidad) }
        .mapValues {
            it.value.maxByOrNull { aemet ->
                aemet.temperaturaMax ?: Double.NEGATIVE_INFINITY
            }
        }

    informe.values.forEach { aemet ->
        println("Temperatura máxima el día ${aemet?.dia} en ${aemet?.localidad} (${aemet?.provincia}): ${aemet?.temperaturaMax} a las ${aemet?.horaRegistroTempMac}")
    }
}

fun generarInformeTemperaturaMinimaPorProvincia(aemetList: List<Aemet>) {
    val informe = aemetList
        .filter { it.temperaturaMin != null }
        .groupBy { Triple(it.dia, it.provincia, it.localidad) }
        .mapValues {
            it.value.minByOrNull { aemet ->
                aemet.temperaturaMin ?: Double.POSITIVE_INFINITY
            }
        } // Obtener el objeto con la temperatura mínima de cada grupo

    informe.values.forEach { aemet ->
        println("Temperatura mínima el día ${aemet?.dia} en ${aemet?.localidad} (${aemet?.provincia}): ${aemet?.temperaturaMin} a las ${aemet?.horaRegistroTempMin}")
    }
}

fun generarInformeTemperaturaMediaPorProvincia(aemetList: List<Aemet>) {
    val informe = aemetList
        .filter { it.temperaturaMax != null && it.temperaturaMin != null } // Solo considerar registros con temperatura máxima y mínima
        .groupBy { Triple(it.dia, it.provincia, it.localidad) } // Agrupar por día, provincia y localidad
        .mapValues { grupo ->
            val temperaturaMedia = grupo.value.mapNotNull { it.temperaturaMax?.plus(it.temperaturaMin!!) }.average()
            grupo.value.firstOrNull()?.copy(
                temperaturaMax = temperaturaMedia,
                horaRegistroTempMac = null,
                temperaturaMin = null,
                horaRegistroTempMin = null,
                precipitacion = null
            )
        } // Obtener el objeto con la temperatura media de cada grupo

    informe.values.forEach { aemet ->
        println("Temperatura media el día ${aemet?.dia} en ${aemet?.localidad} (${aemet?.provincia}): ${aemet?.temperaturaMax}")
    }
}

fun generarInformePrecipitacionMediaPorProvincia(aemetList: List<Aemet>) {
    val informe = aemetList
        .filter { it.precipitacion != null } // Solo considerar registros con precipitación
        .groupBy { Pair(it.dia, it.provincia) } // Agrupar por día y provincia
        .mapValues { grupo ->
            val precipitacionMedia = grupo.value.mapNotNull { it.precipitacion }.average()
            Pair(grupo.key, precipitacionMedia)
        } // Obtener la precipitación media de cada grupo

    informe.values.forEach { (key, precipitacionMedia) ->
        println("Precipitación media el día ${key.first} en la provincia de ${key.second}: $precipitacionMedia")
    }
}

fun generarInformeLugaresLluviaPorProvincia(aemetList: List<Aemet>) {
    val informe = aemetList
        .filter { it.precipitacion != null } // Solo considerar registros con precipitación
        .groupBy { Pair(it.dia, it.provincia) } // Agrupar por día y provincia
        .mapValues { grupo ->
            val lugaresLluvia = grupo.value.count { it.precipitacion!! > 0 }
            Pair(grupo.key, lugaresLluvia)
        } // Obtener el número de lugares en los que llovió de cada grupo

    informe.values.forEach { (key, lugaresLluvia) ->
        println("El día ${key.first} en la provincia de ${key.second} llovió en $lugaresLluvia lugares.")
    }
}

fun generarInformeTemperaturaMediaMadrid(aemetList: List<Aemet>) {
    val registrosMadrid =
        aemetList.filter { it.provincia == "Madrid" && it.temperaturaMax != null && it.temperaturaMin != null }
    val temperaturaMedia = registrosMadrid.map { (it.temperaturaMax!! + it.temperaturaMin!!) / 2 }.average()

    println("La temperatura media en la provincia de Madrid es de $temperaturaMedia grados.")
}

fun generarInformeTemperaturaMaximaMediaTotal(aemetList: List<Aemet>) {
    val temperaturaMaximaList = aemetList.mapNotNull { it.temperaturaMax }
    val temperaturaMaximaMedia = temperaturaMaximaList.average()

    println("La temperatura máxima media total es de $temperaturaMaximaMedia grados.")
}

fun generarInformeTemperaturaMinimaMediaTotal(aemetList: List<Aemet>) {
    val temperaturaMinimaList = aemetList.mapNotNull { it.temperaturaMin }
    val temperaturaMinimaMedia = temperaturaMinimaList.average()

    println("La temperatura mínima media total es de $temperaturaMinimaMedia grados.")
}

fun generarInformeTemperaturaMaximaAntesDeLas15(aemetList: List<Aemet>) {
    val lugaresAntesDeLas15 = aemetList
        .groupBy { it.dia }
        .flatMap { (dia, registros) ->
            registros.filter {
                it.temperaturaMax != null && it.horaRegistroTempMac != null && it.horaRegistroTempMac.isBefore(
                    LocalTime.of(15, 0)
                )
            }
                .map { "Lugar: ${it.localidad}, Provincia: ${it.provincia}, Día: $dia, Hora: ${it.horaRegistroTempMac}, Temperatura Máxima: ${it.temperaturaMax} grados" }
        }

    println("Lugares donde la temperatura máxima fue antes de las 15:00 por día:")
    lugaresAntesDeLas15.forEach { println(it) }
}

fun generarInformeTemperaturaMinimaDespuesDeLas1730(aemetList: List<Aemet>) {
    val lugaresDespuesDeLas1730 = aemetList
        .groupBy { it.dia }
        .flatMap { (dia, registros) ->
            registros.filter {
                it.temperaturaMin != null && it.horaRegistroTempMin != null && it.horaRegistroTempMin.isAfter(
                    LocalTime.of(17, 30)
                )
            }
                .map { "Lugar: ${it.localidad}, Provincia: ${it.provincia}, Día: $dia, Hora: ${it.horaRegistroTempMin}, Temperatura Mínima: ${it.temperaturaMin} grados" }
        }

    println("Lugares donde la temperatura mínima fue después de las 17:30 por día:")
    lugaresDespuesDeLas1730.forEach { println(it) }
}


fun createdJsonMalaForma(completeList: ArrayList<Aemet>) {
    val archivo =
        File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "destino" + File.separator + "JsonUnionMalaForma.json")

    if (!archivo.exists()) {
        Files.createFile(archivo.toPath())
    } else {
        archivo.delete()
        Files.createFile(archivo.toPath())
    }

    var string = StringBuilder(
        "[\n"
    )
    completeList.forEach {
        string.append(
            "\t{\n" +
                    "\t\t\"dia\": \"" + it.dia.toString() + "\",\n" +
                    "\t\t\"localidad\": \"" + it.localidad + "\",\n" +
                    "\t\t\"provincia\": \"" + it.provincia + "\",\n" +
                    "\t\t\"temperaturaMax\": \"" + it.temperaturaMax.toString() + "\",\n" +
                    "\t\t\"horaRegistroTempMac\": \"" + it.horaRegistroTempMac.toString() + "\",\n" +
                    "\t\t\"temperaturaMin\": \"" + it.temperaturaMin.toString() + "\",\n" +
                    "\t\t\"horaRegistroTempMin\": \"" + it.horaRegistroTempMin.toString() + "\",\n" +
                    "\t\t\"precipitacion\": \"" + it.precipitacion.toString() + "\"\n" +
                    "\t},\n"
        )
    }
    string.append("]")
    archivo.writeText(string.toString())

}

fun createdXMLMalaForma(completeList: ArrayList<Aemet>) {
    val archivo =
        File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "destino" + File.separator + "XmlUnionMalaForma.xml")

    if (!archivo.exists()) {
        Files.createFile(archivo.toPath())
    } else {
        archivo.delete()
        Files.createFile(archivo.toPath())
    }

    var string = StringBuilder(
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<datos>\n"
    )
    completeList.forEach {
        string.append(
            "\t<dia>\n" +
                    "\t\t<dia>" + it.dia.toString() + "</dia>\n" +
                    "\t\t<localidad>" + it.localidad + "</localidad>\n" +
                    "\t\t<provincia>" + it.provincia + "</provincia>\n" +
                    "\t\t<temperaturaMax>" + it.temperaturaMax.toString() + "</temperaturaMax>\n" +
                    "\t\t<horaRegistroTempMac>" + it.horaRegistroTempMac.toString() + "</horaRegistroTempMac>\n" +
                    "\t\t<temperaturaMin>" + it.temperaturaMin.toString() + "</temperaturaMin>\n" +
                    "\t\t<horaRegistroTempMin>" + it.horaRegistroTempMin.toString() + "</horaRegistroTempMin>\n" +
                    "\t\t<precipitacion>" + it.precipitacion.toString() + "</precipitacion>\n" +
                    "\t</dia>\n"
        )
    }
    string.append("</datos>")
    archivo.writeText(string.toString())

}


private fun createdCsv(completeList: ArrayList<Aemet>) {
    val archivo =
        File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "destino" + File.separator + "CsvUnion.csv")

    if (!archivo.exists()) {
        Files.createFile(archivo.toPath())
    } else {
        archivo.delete()
        Files.createFile(archivo.toPath())
    }

    var string = StringBuilder(
        "dia," +
                "localidad," +
                "provincia," +
                "temperaturaMax," +
                "horaRegistroTempMac," +
                "temperaturaMin," +
                "horaRegistroTempMin," +
                "precipitacion\n"
    )
    completeList.forEach {
        string.append(
            it.dia.toString() + "," +
                    it.localidad + "," +
                    it.provincia + "," +
                    it.temperaturaMax.toString() + "," +
                    it.horaRegistroTempMac.toString() + "," +
                    it.temperaturaMin.toString() + "," +
                    it.horaRegistroTempMin.toString() + "," +
                    it.precipitacion.toString() + "\n"
        )
    }
    archivo.writeText(string.toString())
}


fun getDate(f: Path): LocalDate? {
    var year = f.toFile().name.trim().substring(5, 9).toIntOrNull()
    var month = f.toFile().name.trim().substring(9, 11).toIntOrNull()
    var day = f.toFile().name.trim().substring(11, 13).toIntOrNull()
    if (year != null && month != null && day != null) {
        return LocalDate.of(year, month, day)
    }
    return null
}



