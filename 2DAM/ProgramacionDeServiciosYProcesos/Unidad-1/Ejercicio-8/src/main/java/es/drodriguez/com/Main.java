package es.drodriguez.com;

import java.io.*;

public class Main {
    public static void main(String[] args){
        try {
            var pb = new ProcessBuilder();
            Process p1 = pb.command("wsl.exe", "grep java").start();
            // ¿Cómo pasar la información a un proceso? -> con OutPutStream
            OutputStream ou = p1.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(ou));
            pw.println("java");
            ou.close();

            // Una vez que le pasamos la información al proceso, sacamos la información
            // que le hayamos metido con InputStream como hemos visto en el ejemplo-7.

            InputStream in = p1.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine())!= null) {
                System.out.println(line);

            }
            br.close();
            p1.waitFor();
        // Excepciones del proceso
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}