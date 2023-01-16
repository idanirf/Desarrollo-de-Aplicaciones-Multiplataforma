package es.drodriguez.com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        // ¿Cómo enviar datos entre diferentes procesos
        try{
            // Le pasamos los datos al primer proceso
            var pb = new ProcessBuilder();
            Process p1 = pb.command("wsl.exe","ls /Users/link/").start();
            InputStream in = p1.getInputStream();
            Process p2 = pb.command("wsl.exe","grep Documents").start();
            OutputStream ou = p2.getOutputStream();
            int b;

            // Concatenamos ls con grep es decir le pasamos ls a grep
            while ((b = in.read())!= -1) {
                ou.write(b);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}