package es.drodriguez.com.Models;

public class Empleados {
    private String nombre;
    private String apellidos;

    public String getNombre() {
        return nombre;
    }

    public Empleados(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Nombre='" + nombre +
                ", Apellidos='" + apellidos;
    }
}
