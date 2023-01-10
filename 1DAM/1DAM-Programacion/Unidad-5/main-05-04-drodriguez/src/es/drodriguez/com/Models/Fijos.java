package es.drodriguez.com.Models;

public class Fijos extends Empleados {
    private float sueldo;

    public Fijos(String nombre, String apellidos, float sueldo) {
        super(nombre, apellidos);
        this.sueldo= sueldo;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() +
                " Apellidos: " + getApellidos() + " Sueldo: " + getSueldo() + " €";
    }
}
