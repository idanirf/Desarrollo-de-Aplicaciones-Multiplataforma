package es.drodriguez.com.Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Privados extends Aviones {
    private String dueñoAvion;

    //Constructor
   public Privados() {
        super();
        this.dueñoAvion= nombreDueños();
   }

    /**
     * @return devuelve el nombre aleatorio de los dueños de un avión privado.
     */

    public static String nombreDueños(){
        ArrayList<String> listaDueños = new ArrayList(Arrays.asList("María del Carmen Sánchez", "Jaime García", "Juan Alvarez ", "José Manuel Ortiz", "Patricia Rodríguez", "Pilar Gómez"));
        return listaDueños.get((int)(Math.random() * listaDueños.size()));
    }

    public String getDueñoAvion() {
        return dueñoAvion;
    }

    public void setDueñoAvion(String dueñoAvion) {
        this.dueñoAvion = dueñoAvion;
    }

    @Override
    public String toString() {
        return "\n" + "Privados: " + " ID 🆔: " + getIdVuelo() + " Velocidad 💨: " + getVelocidad() + " Pasajeros 👨‍👩‍👧‍👦: " + getNumeroPasajeros()+
                " Propietario 👩‍✈️👨‍✈️: " + dueñoAvion;
    }
}
