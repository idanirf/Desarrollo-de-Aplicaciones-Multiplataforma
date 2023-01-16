import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) {
        try {
            // La clase Desktop nos permite abrir Ficheros y Archivos del sistema.
            Desktop des = null;
            // Instanciamos Desktop
            des = Desktop.getDesktop();
            // Con el navegador buscaremos la siguiente dirección
            des.browse(new URI("http://www.google.com"));

            // Creamos las excepciones necesarias.
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
