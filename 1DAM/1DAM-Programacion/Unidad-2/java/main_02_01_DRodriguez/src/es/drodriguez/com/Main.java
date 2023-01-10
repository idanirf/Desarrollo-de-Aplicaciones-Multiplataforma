package es.drodriguez.com;
import java.util.Scanner;public class Main {

    public static void main(String[] args) {
        float lado;
        float area;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el dato: ");
        lado = sc.nextFloat();
        area = lado *lado;
        System.out.println("El area es: " +area);
    }
}
