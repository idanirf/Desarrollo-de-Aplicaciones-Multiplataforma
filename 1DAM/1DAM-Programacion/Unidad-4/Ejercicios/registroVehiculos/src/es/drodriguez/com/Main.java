package es.drodriguez.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        Vehiculo vehiculo1 =  leerVehiculo();
        System.out.println(vehiculo1.ficha() + vehiculo1.antiguedad());
        Vehiculo vehiculo2 =  leerVehiculo();
        System.out.println(vehiculo2.ficha() + vehiculo2.antiguedad());

    }
    private static Vehiculo leerVehiculo() {
        Scanner sc = new Scanner(System.in);
        Vehiculo v = new Vehiculo();
        boolean ok = false;
        System.out.println("Dime la marca: ");
        v.marca = sc.nextLine();
        System.out.println("Dime el modelo: ");
        v.modelo = sc.nextLine();
        System.out.println("Dime la matricula: ");
        v.matricula = sc.nextLine();
        System.out.println("Dime la cilindrada: ");
        v.cilindrada = sc.nextLine();

        do {
            System.out.println("Dime el combustible --todo en minusculas: ");
            v.combustible = sc.next();
            if (v.combustible.equals("gasolina")) {
                ok = true;
            } else if (v.combustible.equals("diesel")) {
                ok = true;
            } else if (v.combustible.equals("hibrido")) {
                ok = true;
            } else if (v.combustible.equals("eléctrico")) {
                ok = true;
            } else if (v.combustible.equals("gas")) {
                ok = true;
            }
        } while (!ok);


        System.out.println("Introduce el año de fabricación: ");
        v.anioFabricacion = sc.nextInt();

        return v;
    }
}
