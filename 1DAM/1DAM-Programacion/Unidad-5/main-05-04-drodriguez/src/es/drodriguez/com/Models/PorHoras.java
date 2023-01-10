package es.drodriguez.com.Models;

public class PorHoras extends Empleados{
    private float horas;
    private float sueldoHora;


    public PorHoras(String nombre, String apellidos, float horas, float sueldoHora) {
        super(nombre, apellidos);
        this.horas = horas;
        this.sueldoHora = sueldoHora;
    }

    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public float getSueldoHora() {
        return sueldoHora;
    }

    public void setSueldoHora(float sueldoHora) {
        this.sueldoHora = sueldoHora;
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombre() +
                " Apellidos: " + getApellidos() + " Horas: " + getHoras() + " Sueldo Hora: " + getSueldoHora() + " €/h";
    }


}
