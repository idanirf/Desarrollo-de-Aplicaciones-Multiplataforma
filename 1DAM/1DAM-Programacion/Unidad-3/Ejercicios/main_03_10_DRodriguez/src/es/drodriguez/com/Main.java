package es.drodriguez.com;

import java.util.Locale;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String abecedario = "abcdefghijklmnñopqrstuvwyxz";
        String abecedarioMayusculas = abecedario.toUpperCase();
        String numeros = "0123456789";
        String mensaje = pedirCadena();
        mensaje = mensaje.toUpperCase();
        String codificado = "";
        char[] mensaje_array = mensaje.toCharArray();
        for (int i = 0; i < mensaje_array.length; i++) {
            char caracter = mensaje_array[i];
            if (stringContieneCaracter(caracter, abecedarioMayusculas)) {
                codificado += reemplaza(caracter, abecedarioMayusculas);
            } else if (stringContieneCaracter(caracter, numeros)) {
                codificado += reemplaza(caracter, numeros);
            } else {
                codificado += caracter;
            }
        }
        System.out.println(codificado);
    }

    public static String pedirCadena() {
        System.out.println("Introduce la cadena de caracteres que queieres codificar: ");
        Scanner sc = new Scanner(System.in);
        String cadenaUsuario = sc.nextLine();
        return cadenaUsuario;
    }

    public static boolean stringContieneCaracter(char caracter, String cadena) {
        return cadena.indexOf(caracter) != -1;
    }

    public static char reemplaza(char caracter, String caracteres) {
        char toReturn;
        int posicionCaracter = caracteres.indexOf(caracter);
        if (posicionCaracter >= caracteres.length() - 1) {
            toReturn = caracteres.charAt(0);
        } else {
            toReturn = caracteres.charAt(posicionCaracter + 1);
        }
        return toReturn;
    }
}



