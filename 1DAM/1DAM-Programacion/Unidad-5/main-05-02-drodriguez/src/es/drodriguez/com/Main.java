package es.drodriguez.com;

import es.drodriguez.com.Models.Camiones;
import es.drodriguez.com.Models.Motocicletas;
import es.drodriguez.com.Models.Turismos;
import es.drodriguez.com.Models.Vehiculos;

public class Main {

    public static void main(String[] args) {
        Vehiculos coche = new Turismos("BMW", "X1", "BLANCO", "1234LPJ", 9);
        System.out.println(coche);
        Vehiculos camion = new Camiones("AUDI", "GRANDE", "AMARILLO", "5254LLJ", 2400);
        System.out.println(camion);
        Vehiculos motocicletas = new Motocicletas("FERRARI", "FR4", "ROJO", "9137LTY",200);
        System.out.println(motocicletas);
    }
}
