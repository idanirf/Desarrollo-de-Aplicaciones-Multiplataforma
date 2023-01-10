package es.drodriguez.com.Models;

import es.drodriguez.com.Comparator.AvionesComparator;
import es.drodriguez.com.tda.Cola;

import java.util.LinkedList;

public class Pista {
    /**
     * @description creamos la fachada del programa
     */
    public void programa() throws InterruptedException {
        Cola<Aviones> AV = new Cola<Aviones>();
        var newAvion = new Aviones();

        int probabilidad;
        int NUMERO_AVIONES = 20;
        for(int i = 0; i<NUMERO_AVIONES;i++) {
            probabilidad = (int) (Math.random() * 100) + 1;
            if (probabilidad < 70) {
                AV.encolar(new Comerciales());
            } else {
                AV.encolar(new Privados());
            }
            Thread.sleep(2000);
            System.out.println(AV);
        }
        System.out.println();
        System.out.println();

        System.out.println("Listado de aviones que han despegado 🛫 según número de pasajeros 👨‍👩‍👧‍👦 de mayor ➕ a menor ➖: ");

        LinkedList<Aviones> ordenados = new LinkedList<>(AV);
        ordenados.sort(new AvionesComparator());
        for(Aviones a :ordenados) {
            System.out.println(a);
        }
    }
}
