package es.drodriguez.com;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        float numero;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime el número que quieres averiguar si es positivo: ");
        numero = sc.nextFloat();
        esPositivo(numero);
    }
    //Función para comprobar sí el numero es positivo o no.
    private static void esPositivo(float numero) {
        if (numero >= 0) {
            System.out.println("El numero es positivo.");
        } else {
            if (numero < 0) {
                System.out.println("El numero es negativo");
            }

        }
    }
}