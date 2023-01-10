package es.drodriguez.com.Models;

import java.util.ArrayList;
import java.util.Arrays;

public class Comerciales extends Aviones {
    private int numeroPersonal;
    private String nombreAerolinea;

    //Constructor

   public Comerciales() {
        super();
        this.numeroPersonal = personalRandom();
        this.nombreAerolinea = nombreAerolineas();
    }

    /**
     * @return devuelve número de tripulantes de vuelo.
     */

    public static int personalRandom() {
        return (int)Math.floor(Math.random()*(1-(230+1))+(230));
    }

    /**
     * @return nombres aleatorios de aerolíneas
     */

    public static String nombreAerolineas(){
        ArrayList<String> listaAerolineas = new ArrayList(Arrays.asList("Iberia", "Air Europa", "Vueling", "Iberia Express", "British Airways", "Air France", "Air Nostrum", "SRC Air"));
        return listaAerolineas.get((int)(Math.random() * listaAerolineas.size()));
    }

    public int getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(int numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    /**
     * @return devolvemos aviones comerciales
     */

    @Override
    public String toString() {
        return "\n" + "Comercial: " + " ID 🆔: " + getIdVuelo() + " Velocidad 💨: " + getVelocidad() + " Pasajeros 👨‍👩‍👧‍👦: " + getNumeroPasajeros()+
                " Tripulantes 👩‍✈️👨‍✈️: "  + numeroPersonal + " Nombre aerolínea ✈: " + nombreAerolinea;
    }
}
