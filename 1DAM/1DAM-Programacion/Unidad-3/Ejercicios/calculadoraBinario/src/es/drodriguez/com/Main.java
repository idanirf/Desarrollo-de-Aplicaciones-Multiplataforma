package es.drodriguez.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       readNumber();
       //printResultado();
        int[]numeroBinario = readNumber();
        //toVector(String numberito);
        printBinario(numeroBinario);
        binarioToDecimal();


    }
    /**
     * @description en esta función vamos a leer por teclado los numeros introducidos a ser la
     * lectura de un número binario solo puede tener 0's y 1's, sí introducimos otro valor no va
     * a ser valido, por lo tanto entrará en un bucle hasta que hayamos introducido un número
     * que sea valido.
     */
    public static int[] readNumber(){
        //Declaramos el scanner para la lectura de datos por teclado
        Scanner in = new Scanner(System.in);
        String numberito;
        //Ahora leemos nuestro número pero solo puede contener 0's y 1's no otro valor.
        do {
            System.out.println("Introduzca el número en binario que quieres convertir en decimal: ");
            numberito = in.nextLine();
        } while(!esBinario(numberito));
        return toVector(numberito);
    }

    /**
     *
     * @param numberito cada caracter que vamos a comprobar si es 0 ó 1.
     * @return es binario true/false
     * @description en está función vamos a comprobar si el numero que hemos pasado es 0 o 1 es
     * decir si se trata de un número binario.
     */
    public static boolean esBinario(String numberito){
        boolean esBinario = true;
        for (int i = 0; i <numberito.length() && esBinario; i++){
            esBinario = numberito.charAt(i) == '0' || numberito.charAt(i) == '1';
        }
        return esBinario;
    }

    /**
     * @description en este vector vamos almacenar nuestro string y lo vamos a alamcenar como
     * numeros enteros para después poder operar con ellos.
     */
    public static int[]toVector(String numberito){
        int[]resultante = new int[numberito.length()];
        for (int i = 0; i < resultante.length; i++) {
            resultante[i] = Character.getNumericValue(numberito.charAt(i));
        }
        return resultante;
    }

    /**
     *
     * @param numeroBinario vamos a imprimir este número binario en pantalla.
     */
    public static void printBinario(int[]numeroBinario){
        for (int i: numeroBinario){
            System.out.print(i);
        }
        System.out.println();
    }

    public static int []binarioToDecimal(){
        
        return ;
    }
}
