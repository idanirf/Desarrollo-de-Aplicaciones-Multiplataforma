import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            // Vamos a obtener el directorio de trabajo. El de este proyecto.
            String path = System.getProperty("user.dir");
            System.out.println("Path del directorio: " + path);
            // Descomponemos la dirección del directorio hasta Ejercicio-6.iml
            String dirPath = path + File.separator+"Ejercicio-6.iml";
            System.out.println(dirPath);
            // Creamos un proceso y llamamos al comando code para abrir Visual Studio Code
            var pb = new ProcessBuilder();
            // Le indicamos que es wsl.exe el comando con el que abrimos el comando la ruta y el start.
            Process proceso1 = pb.command("wsl.exe", "code", dirPath).start(); ;
            int res = proceso1.waitFor();
            System.out.println(res);
        // EXCEPCIONES DE Process waitFor() en el caso de que se interrumpa y excepción de normall.
        } catch(IOException e){
            System.out.println("No se puede lanzar el proceso, se ha producido un error");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
