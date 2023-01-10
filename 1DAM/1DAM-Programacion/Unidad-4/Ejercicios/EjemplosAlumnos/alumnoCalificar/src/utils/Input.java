package utils;

import java.util.Scanner;

public class Input {
    Scanner sc = new Scanner(System.in);
    /*
    * Lee una línea por teclado
     */
    public String readLine() {
        String line = "";
        do {
            line = sc.nextLine();
        } while (line.length() ==0);
        return line;
    }

    /*
    * Lee un dato entero a través de teclado.
     */
    public int readInt() {
        int i = 0;
        boolean ok = false;
        do {
            try {
                i = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.err.println("Error: no es un entero");
                sc.next();
            }
        } while (!ok);
        return i;
    }

    public float readFloat() {
        float f = 0;
        boolean ok = false;
        do {
            try {
                f = sc.nextFloat();
                ok = true;
            } catch (Exception e) {
                System.err.println("Error: no es un float.");
                sc.next();
            }
        } while (!ok);
        return f;
    }

    public double readDouble() {
        double d = 0;
        boolean ok = false;
        do {
            try {
                d = sc.nextDouble();
                ok = true;
            } catch (Exception e) {
                System.err.println("Error: no es un entero");
                sc.next();
            }
        } while (!ok);
        return d;
    }

    public boolean readBoolean(){
        boolean b = false;
        boolean ok = false;
        do {
            try {
                b = sc.nextBoolean();
                ok = true;
            } catch (Exception e){
                System.err.println("Error: no es un boolean.");
                sc.next();
            }
        }while(!ok);
        return b;
    }
}
