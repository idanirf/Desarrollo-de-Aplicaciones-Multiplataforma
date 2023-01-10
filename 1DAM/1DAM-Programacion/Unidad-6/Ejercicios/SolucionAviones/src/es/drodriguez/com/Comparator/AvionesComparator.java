package es.drodriguez.com.Comparator;

import es.drodriguez.com.Models.Aviones;

import java.util.Comparator;

    public class AvionesComparator implements Comparator<Aviones> {
        @Override
        public int compare(Aviones o1, Aviones o2) {
            return o2.getNumeroPasajeros() - o1.getNumeroPasajeros();
        }
    }
