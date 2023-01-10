package utils;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class input {

    //Lectura de una línea por teclado
    Scanner sc = new Scanner(System.in);
    public String readLine(){
        String line = "";
        do {
            line = sc.nextLine();
        }while(line.length() > 0);
        return line;
    }

    //Lectura de un entero por teclado
    public int readInt(){
        int i = 0;
        boolean ok = false;
        do{
            try{
                i = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println(colorize("Error, no has introducido un número entero", RED_TEXT()));
                sc.next();
            }
        } while (!ok);
        return i;
    }
}
