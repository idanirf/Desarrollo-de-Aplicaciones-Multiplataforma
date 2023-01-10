package es.drodriguez.com;

import es.drodriguez.com.Models.Animales;
import es.drodriguez.com.Models.Gatos;
import es.drodriguez.com.Models.Perros;

public class Main {

    public static void main(String[] args) {
        Animales nePerro = new Perros("Pepe", "razapepe", 12.00f, "vede");
        System.out.println(nePerro);
        Animales neGato = new Gatos("Juan", "gato pequeño", 6.00f,"azul");
        System.out.println(neGato);
    }
}
