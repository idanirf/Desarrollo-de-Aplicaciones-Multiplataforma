package com.dam.gestionalmacendam.servicies.printers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class HtmlPrinterReception implements IPrinter{
    //Reception a imprimir y sus lineReception
    Reception o ;
    LineReceptionRepository repository = LineReceptionRepository.getInstance(DataBaseManager.getInstance());

    //para crear el fichero y el redactor
    File fichero = null;
    PrintWriter f = null;

    //para gardar la ruta del fichero
    private final Path relativePath = Paths.get("");
    private final String absolutePath = relativePath.toAbsolutePath().toString();
    private final String directory = absolutePath + File.separator + "Reception";

    //para que cada archivo tenga el uuid de el order o reception y si es order o reception
    private String uuid ;
    private String title = "Recepcion";
    private  String file = directory + File.separator + title + "." + uuid + ".html";

    //documento
    String document = "";

    public HtmlPrinterReception(Reception o ) {
       this.o = o;
       this.uuid = o.getRIC();
        createDocument();
    }

    /**
     * crea un documento en html
     * @return true si ha consegido realidar el documento y false si no
     */
    @Override
    public boolean createDocument() {
        try {
            //creamos directorio donde meter fichero
            createDir();

            //crear el head con titulo y el empiece del body con el mismo titulo
            startWhitTitle("Tiquet de la Reception: " + o.getRIC());


            //añadir Todos articulo del order
            ObservableList<LineReception> lineR = repository.SerachByReceptionsBelong(o.getRIC());
            for (LineReception lineReception : lineR){addArticle(lineReception);}


            //terminar el documento con el total de la compra o recepcion, finalizar el documento, y salbarlo
            finalDocument(lineR);

            printDocument();


            return true;

        }catch (Exception e){

            System.out.println("no se ha podido crear el docunmento ");
            e.printStackTrace();
            return false;

        }
    }

    private void createDir() {
        Path path = Paths.get(directory);
        if(!Files.exists(path)){
            try{
                Files.createDirectory(path);
            }catch (Exception e){
                System.out.println("no se ha podido crear ");
            }
        }
    }

    public void  startWhitTitle(String title) {

        document = document + "<!DOCTYPE html> <html lang=\"es\"> <head> " +
                "<meta charset=\"UTF-8\"> <meta name=\"GADAM\"> <title>" + title + "</title>" +
                " </head> <body>  <h1>" + title +" </h1> <h3>Ariculos : </h3>"+
               " <p> RLIC de articulo : PIC de articulo : Precio del Articulo: Cantidad del articulo:  Precio total de linea:</p>";

    }

    public void  addArticle(LineReception lineReception) {
        document = document + " <p> RLIC  : "+ lineReception.getRLIC() +
                " , PIC Articulo  : "+ lineReception.getArticlePIC().getValue().toString()+
                " , Precio" + " : "+ lineReception.getUnitPrice().doubleValue() +
                " , Cantidad : " + lineReception.getLoad().getValue().toString() +
                " , Precio total : "+ lineReception.getTotalPrice().getValue() +"</p>";


    }

    public void  finalDocument(List<LineReception> lineR) {

        //alomejor hay una forma mas sencilla de acerlo pero no la he encontrado
        //sumar todos los totales de linea

        Double totalr = 0D;
        for (int i = 0; i < lineR.size() ; i++){
            totalr += lineR.get(i).getTotalPrice().doubleValue();
        }

        document = document + "<br> <p> Total de Recepcion : "+ totalr +" </p>";
        document = document + "\n <br> <p> tiket realizada a impresion en :" +
                " "+ LocalDateTime.now() +" </p> <br> <p> Tiket de Recepcion  expedido por : Empresa GADAM </p>" ;


    }

    private void printDocument() {
        //creamos el fichero
        fichero = new File(file);

        //creamso el escritor
        try {
            f = new PrintWriter(new FileWriter(fichero,true));

            //escribios lo realizado en el documento
            f.println(document);

        }catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //cerrmos escritor
                if (f != null){
                    f.close();}
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
