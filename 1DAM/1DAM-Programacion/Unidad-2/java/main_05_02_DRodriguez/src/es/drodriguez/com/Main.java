package es.drodriguez.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	float radio;
    float totalCircunferencia;
    float totalArea;
    Scanner sc = new Scanner(System.in);
    System.out.println("Dime el radio: ");
    radio = sc.nextFloat();
    esArea(radio);
    System.out.println("El area es:" +esArea(radio));
   esCircunferencia(radio);
   System.out.println("La circunferencia total es: " +esCircunferencia(radio));
    }
    private static double esArea(float radio) {
        final float NUMERO_PI = 3.14f;
        double areaTotal = NUMERO_PI*(radio*2);
        return areaTotal;
    }
    private static double esCircunferencia(float radio) {
        double circunferenciaTotal = 2 * (radio * 2);
        return circunferenciaTotal;
    }
}
