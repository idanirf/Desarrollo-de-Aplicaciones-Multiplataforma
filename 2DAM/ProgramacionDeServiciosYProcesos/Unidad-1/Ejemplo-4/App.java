import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args){
        // Process. Es una clase abstracta que permite controlar los procesos nativos del sistema que
        // devuelven las clases Runtime y ProcessBuilder.
        try{
            Process process = Runtime.getRuntime().exec("cmd /c dir C:\\Users\\link");

            // Comenzamos el proceso
            StringBuilder out = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // Condición de lectura de la línea
            while ((line = in.readLine())!= null) {
                out.append(line);
            }
            // Ponemos en espera el proceso actual.
            int salida = process.waitFor();
            if (salida == 0){
                System.out.println(out);
                // Estado de salida del sistema
                System.exit(0);
            }
            // Código de error en caso de que se detenga el proceso sin finalizar.
            else {
                System.out.println(out);
                System.out.println(" Se ha producido un error");
                System.exit(1);
            }
            // Necesitamos las excepciones en el caso de que el proceso no finalice de forma correcta.
        } catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
