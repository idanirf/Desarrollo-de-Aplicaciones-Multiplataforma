package es.drodriguez.com;

import es.drodriguez.com.models.Fracciones;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("Iniciando calculadora de fracciones....");
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int seleccion = 0;

        while (!exit){
            boolean ok = false;
            System.out.println("1. Simplificar");
            System.out.println("2. Sumar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Exit");

            System.out.println();

            Fracciones m1 = new Fracciones();
            Fracciones m2 = new Fracciones();

            System.out.print("Introduce numerador 1: ");
            m1.getNumerador();
            System.out.print("Introduce denominador 1: ");
            m1.getDenominador();

            System.out.print("Introduce numerador 2: ");
            m2.getNumerador();
            System.out.print("Introduce denominador 2: ");
            m2.getDenominador();

            do {
                try {
                    System.out.println("Introduce la opción:");
                    seleccion = sc.nextInt();
                    ok = true;
                } catch (Exception e) {
                    System.out.println("El dato introducido no es válido, intentalo de nuevo");
                    sc.next();
                }
            } while (!ok);


            switch (seleccion){
                case 1:
                    System.out.println("Fracción 1 simplificada: " + m1.simplificar(m1));
                    System.out.println("Fracción 2 simplificada: " + m2.simplificar(m2));

                    break;
                case 2:
                    System.out.println(Fracciones.sumar(m1,m2));
                    break;
                case 3:
                    System.out.println(Fracciones.multiplicar(m1,m2));
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }
}