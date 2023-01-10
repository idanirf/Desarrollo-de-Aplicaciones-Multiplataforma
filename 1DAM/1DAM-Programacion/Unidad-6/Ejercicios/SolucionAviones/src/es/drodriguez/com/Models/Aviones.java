package es.drodriguez.com.Models;

import static java.lang.Math.floor;

public class Aviones {
    private int idVuelo;
    private float velocidad;
    private int numeroPasajeros;

    /**
     * @return devuelve ids aleatorios.
     */

    public static int idRandom() {
       return (int)Math.floor(Math.random()*(1000-(1100+1))+(1100));
    }

    /**
     * @return devuelve la velocidad del avión.
     */

    public static int velocidadRandom() {
        return (int)Math.floor(Math.random()*(720-(1080+1))+(1080));
    }

    /**
     * @return devuelve número aleatorio de pasajeros del avión.
     */

    public static int pasajerosRandom() {
        return (int)Math.floor(Math.random()*(1-(230+1))+(230));
    }

    //Constructor

    public Aviones() {
        this.idVuelo = idRandom();
        this.velocidad = velocidadRandom();
        this.numeroPasajeros = pasajerosRandom();

    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    @Override
    public String toString() {
        return "\n" + "Comercial: " + " ID 🆔" + getIdVuelo() + " Velocidad 💨: " + getVelocidad() + " Pasajeros 👨‍👩‍👧‍👦: " + getNumeroPasajeros();
    }


}
