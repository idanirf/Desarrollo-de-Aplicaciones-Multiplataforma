package es.drodriguez.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //En este ejemplo vemos como se lee la salida de un proceso.
        try {
            var pb = new ProcessBuilder();
            Process p1 = pb.command("wsl.exe", "ls /Users").start();
            //Ahora leemos la salida de java y se la pasamos al proceso
            // JAVA salida -> PROCESO entrada
            InputStream in = p1.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine())!= null) {
                System.out.println(line);
            }
            br.close();
            p1.waitFor();

        } catch (IOException e ){
            System.out.println("No hay ningún proceso");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}