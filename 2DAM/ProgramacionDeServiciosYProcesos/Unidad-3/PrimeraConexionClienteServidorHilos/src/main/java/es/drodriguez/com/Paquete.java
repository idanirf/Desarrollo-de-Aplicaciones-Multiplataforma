package es.drodriguez.com;

public class Paquete implements java.io.Serializable {
    private String nombre;
    private String apellido;
    private int edad;

    public Paquete() {
        this.nombre = "La Dele";
        this.apellido = "Rodriguez";
        this.edad = 21;
    }

    public Paquete(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public void visible() {
        System.out.println("Nombre: " + nombre + " Apellido: " + apellido + " Edad: " + edad);
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                '}';
    }
}

