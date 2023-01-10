package com.dam.gestionalmacendam.servicies.printers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
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

public class HtmlPrinterOrder implements IPrinter{
    //order a imprimir y sus lineorder
    Order o ;
    LineOrderRepository repository = LineOrderRepository.getInstance(DataBaseManager.getInstance());

    //para crear el fichero y el redactor
    File fichero = null;
    PrintWriter f = null;

    //para gardar la ruta del fichero
    private final Path relativePath = Paths.get("");
    private final String absolutePath = relativePath.toAbsolutePath().toString();
    private final String directory = absolutePath + File.separator  + "order";

    //para que cada archivo tenga el uuid de el order o reception y si es order o reception
    private String uuid ;
    private String title = "Pedido";
    private String file = "";

    //documento
    String document = "";

    public HtmlPrinterOrder(Order o ) {
       this.o = o;
       this.uuid = o.getOIC();
       this.file=this.directory + File.separator + this.title + "." + this.uuid + ".html";
        createDocument();
        System.out.println(file);
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
            startWhitTitle("Tiquet de la Order: " + o.getOIC());


            //añadir Todos articulo del order
            ObservableList<LineOrder> lineOrders = repository.searchByUuidOrder(o.getOIC());
            for (LineOrder lineOrder : lineOrders){ addArticle(lineOrder);}

            //terminar el documento con el total de la compra o recepcion, finalizar el documento, y salbarlo
            finalDocument(lineOrders);

            printDocument();


            System.out.println(document);
            return true;

        }catch (Exception e){

            System.out.println("no se ha podido crear el docunmento ");
            e.printStackTrace();
            return false;

        }
    }

    private void createDir() {
        System.out.println("create dir");
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
        System.out.println("start title");
        document = document + "<!DOCTYPE html> <html lang=\"es\"> <head> " +
                "<meta charset=\"UTF-8\"> <meta name=\"GADAM\"> <title>" + title + "</title>" +
                " </head> <body>  <h1>" + title +" </h1> <h3>Ariculos : </h3>"+
               " <p> OLIC de articulo -- Nombre de articulo -- Precio del Articulo -- Cantidad del articulo --  Precio total de linea </p>";

    }

    public void  addArticle(LineOrder lineOrder) {
        System.out.println("add article");
        document = document + " <p> OLIC  : "+ lineOrder.getOLIC() +
                " , Nombre  : "+ lineOrder.getArticle().getValue() +
                " , Precio : "+ lineOrder.getUnitPrice().getValue().toString() +
                " , Cantidad : " + lineOrder.getLoad().getValue().toString() +
                " , Precio total : "+ lineOrder.getTotalPrice().getValue().toString() +"</p>";


    }

    public void  finalDocument(List<LineOrder> lineOrder) {
        System.out.println("final document");

        //alomejor hay una forma mas sencilla de acerlo pero no la he encontrado
        //sumar todos los totales de linea

        Double totalOrder = 0D;
        for (int i = 0; i < lineOrder.size() ; i++){
           totalOrder += lineOrder.get(i).getTotalPrice().doubleValue();
        }

        document = document + "<br> <p> Total de Pedido : "+ totalOrder+" </p>";
        document = document + "\n <br> <p> Tiket realizado a impresion en :" +
                " "+ LocalDateTime.now() +" </p> <br> <p> Tiket de Pedido  expedido por : Empresa GADAM </p> " +
                "</body> </html>"
        ;


    }

    private void printDocument() {
        System.out.println("print document");
        //creamos el fichero
        fichero = new File(file);
        System.out.println("guardado en "+ file);

        //creamso el documento fichero
        try {

            f = new PrintWriter(new FileWriter(fichero,false));

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
