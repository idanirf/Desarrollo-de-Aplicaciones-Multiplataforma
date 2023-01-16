package interchange

import dto.ContenedoresVariosDTO
import dto.ModeloResiduoDTO
import enums.Meses
import enums.TipoContenedor
import enums.TipoResiduo
import logger
import mappers.MaperModeloResiduo
import java.io.*
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Logger
import java.util.stream.Collectors

class Csv {


    //---------------------------Modelo residuo---------------------------------------
    /**
    Función que pasndole una path y extrae un fichero con los datos de Modelo residio de la lista
     */
    public fun csvToMoeloResiduo(p : Path): ArrayList<ModeloResiduoDTO>{

        var lista = ArrayList<ModeloResiduoDTO>()

        try {
            logger.info("buscando si el fuchero existe en " + p.toString())
            if(Files.exists(p)){
                val br = BufferedReader(FileReader(p.toFile()))
                try {
                    logger.info(" fichero exixte ")

                    var lineas = br.readText()
                    logger.info(" lineas leidas")
                    var modelosResiduosCollection= Files.lines(p)
                        .skip(1)
                        .map(this::getModelRediduoDTO)
                        .collect(Collectors.toList());

                    if (modelosResiduosCollection.get(0)!=null){
                        modelosResiduosCollection.forEach { m ->
                            if (m != null) {
                                lista.add(m)
                            }
                        }
                    }

                }catch (e : Exception){

                }finally {
                    br.close()
                }

            }
        }catch (e : Exception) {
            println(" entro en la excepcion por csvtoobject")

        }
        return lista
    }

    /**
     * Función que combiente una lista de ModelResituo un fichero con los datos tipo csv
     */
    public fun ModeloRosiduoToCsv(a : ArrayList<ModeloResiduoDTO>, p : Path): File {
        logger.info(" entrado en Modelo residuo ToCsv")

        var listaString = StringBuilder().append("Año;Mes;Lote;Residuo;Distrito;Nombre Distrito;Toneladas\n")
        a.forEach { m -> listaString.append(getStringToModeloResiduoCSV(m)) }


        var fi: File = writeInFile(p, Path.of(p.toString()+File.separator+ "modelo_residuo.csv"), listaString)

        return fi
    }

    //-------------------------------------Contenedores varios ------------------------
    /**
    Función que pasndole una path y extrae un fichero con los datos de Cv de la lista
     */
    public fun csvToContenedoresVarios(p: Path): ArrayList<ContenedoresVariosDTO> {
        logger.info("Pasando la path a Mr")
        logger.info("iniciando")

        var lista = ArrayList<ContenedoresVariosDTO>()

        try {
            logger.info("cojiendo datos de "+ p)
            if (Files.exists(p)){
                val br = BufferedReader(FileReader(p.toFile()))
                try {

                    var line = br.readText()
                    var contenedoresVariosCollection = Files.lines(p)
                        .skip(1)
                        .map(this::getContenedoresVariosDto)
                        .collect(Collectors.toList())

                    if (contenedoresVariosCollection.get(0)!=null){
                        contenedoresVariosCollection.forEach { m ->
                            if (m != null) {
                                lista.add(m)
                            }
                        }
                    }

                }catch (e : Exception){
                    e.printStackTrace()

                }finally {
                    br.close()
                }

            }
        } catch (e: Exception){
            logger.severe(e.toString())
        }
        return  lista
    }


    /**
     * Función que combiente una lista de Cv un fichero con los datos tipo csv
     */
    public fun ContenedoresVariosToCsv(a : ArrayList<ContenedoresVariosDTO>, p : Path): File {
        logger.info(" entrado en contenedores varios ToCsv")


        var listaString = StringBuilder().append("codigoInternoSituado;tipoContenedor;modelo;descripcionModelo" +
                ";cantidad;lote;distrito;barrio;tipoVia;nombre;numero;coordenadaX;coordenadaY;TAG\n")
        a.forEach { m -> listaString.append(getStringToMContenedoresVarios(m)) }


        var fi: File = writeInFile(p, Path.of(p.toString()+File.separator+ "contenedores_Varios.csv"), listaString)

        return fi
    }
    //    -------------otros extras--------------------------------------------------

    /**
     * Función que crea y escrive el fichero csv
     */
    fun writeInFile(pathDeDirectorio: Path, pathDeFichero: Path, listaString: java.lang.StringBuilder): File {

        //ver si el directorio exixte, si no crearlo
        if(Files.exists(pathDeDirectorio) && (Files.isDirectory(pathDeDirectorio))){
            logger.info(" la carpeta donde guardar ficheros nuevos exixte y es un directorio")
        }else{
            logger.info(" creamos la carpeta porue no exsite para guardar ficheros ")
            Files.createDirectory(pathDeDirectorio)
            logger.info(" creada ")
        }



        logger.info("Escribiendo en fichero")
        var f: File
        if (Files.notExists(pathDeFichero)) {
            logger.info("El fichero no existe")
            f = File(pathDeFichero.toString())
            logger.info("creado")
        } else {
            logger.info("el fichero existe")
            f = pathDeFichero.toFile()
        }
        logger.info(" escribiendo en : " + pathDeFichero)
        val bw = BufferedWriter(FileWriter(f))
        try {
            logger.info("Escribiendo en el fichero")
            bw.write(listaString.toString())

        } catch (e: Exception) {
            logger.warning("El fichero existe")
        } finally {
            bw.close()
            logger.info("Cerrando el escritor del fichero")
        }
        logger.info("saliendo de writeFile")
        return f
    }


    /**
     * pasa de Mr A strig csv
     */
    private fun getStringToModeloResiduoCSV(m : ModeloResiduoDTO): String {
        return "${m.año};${m.mes};${m.lote};${m.residuo};${m.distrito};" +
                    "${m.nombreDistrito};${m.toneladas}\n"
    }

    /**
     * pasa de Cv A strig csv
     */
    private fun getStringToMContenedoresVarios(m: ContenedoresVariosDTO): String? {
        return "${m.codigoInternoSituado};${m.tipoContenedor};${m.modelo};${m.descripcionModelo};" +
                "${m.cantidad};${m.lote};${m.distrito};${m.barrio};${m.tipoVia};${m.nombre};"+
                "${m.numero};${m.coordenadaX};${m.coordenadaY};${m.TAG}\n"
    }

    /**
     * funcion que pasandole una linea de un scv te debuelve un Cv
     */
    private fun getContenedoresVariosDto(line: String): ContenedoresVariosDTO {
        val campos = line.split(";")
        return ContenedoresVariosDTO(
            codigoInternoSituado = campos[0],
            tipoContenedor = campos[1],
            modelo = campos[2],
            descripcionModelo = campos[3],
            cantidad = campos[4],
            lote = campos[5],
            distrito = campos[6],
            barrio = campos[7],
            tipoVia = campos[8],
            nombre = campos[9],
            numero = campos[10].toIntOrNull(),
            coordenadaX = campos[11],
            coordenadaY = campos[12],
            TAG = campos[13]
        )
    }

    /**
     * funcion que pasandole una linea de un scv te debuelve un ModeloResiduo
     */
    private fun getModelRediduoDTO(linea : String): ModeloResiduoDTO? {

        val campos  = linea.split(";")
        if(campoEsMesCorrecto(campos[1])){
            return ModeloResiduoDTO(
                año = campos[0],
                mes = campos[1],
                lote = campos[2],
                residuo = campos[3],
                distrito = campos[4],
                nombreDistrito = campos[5],
                toneladas = campos[6]

            )
        }
        return null
    }

    private fun campoEsMesCorrecto(s: String): Boolean {
        if (s.equals("ENERO",true)||
            s.equals("FEBRERO",true)||
            s.equals("MARZO",true)||
            s.equals("ABRIL",true)||
            s.equals("MAYO",true)||
            s.equals("JUNIO",true)||
            s.equals("JULIO",true)||
            s.equals("AGOSTO",true)||
            s.equals("SEPTIEMBRE",true)||
            s.equals("OCTUBRE",true)||
            s.equals("NOVIEMBRE",true)||
            s.equals("DICIEMBRE",true)
                ){
            return true
        }
        return false

    }

    //futuras funcionalidades de mejora
    private fun getMes(s: String): Meses? {
        logger.info(" entrado en get mes")
        when(s){
            "ENERO" -> return Meses.ENERO
            "FEBRERO"->return Meses.FEBRERO
            "MARZO"-> return  Meses.MARZO
            "ABRIL"-> return  Meses.ABRIL
            "MAYO" -> return  Meses.MAYO
            "JUNIO" -> return  Meses.JUNIO
            "JULIO" -> return  Meses.JULIO
            "AGOSTO" -> return  Meses.AGOSTO
            "SEPTIEMBRE"-> return  Meses.SEPTIEMBRE
            "OCTUBRE"-> return  Meses.OCTUBRE
            "NOVIEMBRE" ->return  Meses.NOVIEMBRE
            "DICIEMBRE" ->return  Meses.DICIEMBRE

        }
        return null
    }

    //futuras funcionalidades de mejora
    private fun getTipoResiduo(s: String): TipoResiduo {
        logger.info(" entrado en get tipo residuo")

        when(s){
            "RESTO" -> return TipoResiduo.RESTO
            "ENVASES"-> return TipoResiduo.ENVASES
            "VIDRIO"-> return TipoResiduo.VIDRIO
            "ORGÁNICA" -> return TipoResiduo.ORGANICA
            "PAPEL Y CARTON"-> return TipoResiduo.PAPEL_Y_CARTÓN
            "PUNTOS LIMPIOS"-> return TipoResiduo.PUNTOS_LIMPIOS
            "CARTON COMERCIAL"-> return TipoResiduo.CARTÓN_COMERCIAL
            "VIDRIO COMRCIAL"-> return TipoResiduo.VIDRIO_COMERCIAL
            "PILAS"-> return TipoResiduo.PILAS
            "ANIMALES_MUERTOS"-> return TipoResiduo.ANIMALES_MUERTOS
            "RCD"-> return TipoResiduo.RCD
            "CONTENEDORES DE ROPA USADA"-> return TipoResiduo.CONTENEDORES_DE_ROPA_USADA
            "REIDUOS DEPOSITADOS EN MIGAS CALIENTES"-> return TipoResiduo.RESIDUOS_DEPOSITADOS_EN_MIGAS_CALIENTES
        }
        return TipoResiduo.DESCONOCIDO
    }

    //futuras funcionalidades de mejora
    fun getEnumTipoContenedor(s: String): TipoContenedor {
        when (s) {
            "Envases" -> return TipoContenedor.ENVASES
            "Organica" ->return TipoContenedor.ORGANICA
            "Resto" -> return TipoContenedor.RESTO
            "Papel y carton" -> return TipoContenedor.PAPEL_Y_CARTÓN
            "Vidrio" -> return TipoContenedor.VIDRIO
        }
        return TipoContenedor.DESCONOCIDO
    }



}