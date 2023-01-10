package es.drodriguez.com.Models;

public class AComision extends Empleados {
    private float ventas;
    private float porcentaje;

    public AComision(String nombre, String apellidos, float porcentaje, float ventas) {
        super(nombre, apellidos);
        this.porcentaje = porcentaje;
        this.ventas = ventas;
    }

    public float getVentas() {
        return ventas;
    }

    public void setVentas(float ventas) {
        this.ventas = ventas;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() +
                " Apellidos: " + getApellidos() + " Ventas: " + getVentas() + " Porcentaje: " + getPorcentaje() + " %";
    }
}
