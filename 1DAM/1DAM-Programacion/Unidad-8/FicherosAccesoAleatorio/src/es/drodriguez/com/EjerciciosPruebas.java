package es.drodriguez.com;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class EjerciciosPruebas {
    public static void añadir() {
        Scanner sc = new Scanner(System.in);
        RandomAccessFile archivo = null;
        int numero;
        try {
            archivo = new RandomAccessFile("numeros.dat", "rw");
            mostrar();
            System.out.println("Introdcue el número que quieres añadir al final");
            numero = sc.nextInt();
            archivo.seek(archivo.length());
            archivo.writeInt(numero);
            mostrar();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void modificar() {
    }

    /*
     * Se encarga de mostrar el contenido que tenemos escrito en nuestro fichero de texto, o del tipo que sea ...
     */
    public static void mostrar() {
        RandomAccessFile archivo = null;
        try {
            archivo = new RandomAccessFile("numeros.dat", "r");
            int readEntero;
            try {
                // nos vamos a posicionar al principio del archivo
                archivo.seek(0);
                while (true) {
                    readEntero = archivo.readInt();
                    System.out.println(readEntero);
                }
            } catch (EOFException e) {
                //Cuando lleguemos al final del fichero
                System.out.println("Final del fichero");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
    Con este código puedo modificar todas las palabras que cumplan con la que hayamos pasado,
     una vez introducida la palabra nos hace el cambio.
     El fichero se lee línea x línea y si cumple con el patrón se realiza el cambio.
     */
    public static void modificarPalabra() {
        Scanner sc = new Scanner(System.in);
        RandomAccessFile archivo = null;
        String palabra;
        String cadena;
        StringBuilder auxiliarBuilder;
        long posicion = 0;
        int indice;

        try {
            //Abrimos el fichero de texto para realizar las acciones de lectura y escritura
            archivo = new RandomAccessFile("texto.txt", "rw");
            //Ahora vamos a introducir la palabra que vamos a buscar en el archivo
            System.out.println("Introduce la palabra que quieres buscar: ");
            //Leemos la línea y omitimos espacios al inicio y final.
            palabra = sc.nextLine().trim();

            //comenzamos con la lectura del archivo
            cadena = archivo.readLine(); //leemos la primera línea de nuestro archivo
            while (cadena != null) { //leemos el archivo hasta que lleguemos al final de él.
                indice = cadena.indexOf(palabra); //estamos situados en la línea, vamos a buscar la palabra.
                while (indice != -1) { //seguimos leyendo sí la línea contiene esa palabra
                    //Sí encuentro la palabra en la línea pasamos la línea a StringBuilder para poder modificar esa línea
                    auxiliarBuilder = new StringBuilder(cadena);
                    auxiliarBuilder.replace(indice, indice + palabra.length(), palabra.toUpperCase());
                    cadena = auxiliarBuilder.toString();

                /*
                Nos vamos al inicio de la línea y la modificamos completamente
                Guardamos la posición actual de donde empieza la línea, esta la guardamos en la variable posición
                 */
                    archivo.seek(posicion);
                    archivo.writeBytes(cadena);

                    //!!!! COMPROBAR SI LA PALABRA SE REPITE EN ESA MISMA LÍNEA!!!
                    indice = cadena.indexOf(palabra);
                }
                posicion = archivo.getFilePointer();
                cadena = archivo.readLine();
            }
            //A partir de aquí es el catch de siempre.
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
