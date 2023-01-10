package es.drodriguez.com.models;

import java.util.Objects;

public class Alumnos {
    // Declaración variables
    private String nombre = "";
    private int edad = 0;
    private float nota = 0.0f;
    private String calificacion = "";



    public Alumnos(String nombre, int edad, float nota) {
        setNombre(nombre);
        setEdad(edad);
        setNota(nota);
        this.calificacion = calcularCalificacion();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && nombre.trim().length() > 0 && nombre.trim().length() < 100) {
            this.nombre = nombre;
        } else {
            System.err.println("Debes introducir al menos un carácter");
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 0 && edad <= 100) {
            this.edad = edad;
        } else {
            System.err.println("La edad introducida no es correcta");
        }
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        if (nota >= 0 && nota <= 10) {
            this.nota =  (float) Math.round(nota * 100) / 100;
            calificacion = calcularCalificacion();
        } else {
            System.err.println("La nota introducida no es correcta");
        }
    }

    public String getCalificacion() {
        return calificacion;
    }

    public String calcularCalificacion() {
        if (nota >= 0 && nota < 4) {
            return "suspenso";
        } else if (nota >= 5 && nota < 7) {
            return "suficiente";
        } else if (nota >= 7 && nota < 9) {
            return "bien";
        } else {
            return "sobresaliente";
        }
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", nota=" + nota +
                ", calificacion='" + calificacion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumnos alumno = (Alumnos) o;
        return edad == alumno.edad && Float.compare(alumno.nota, nota) == 0 && nombre.equals(alumno.nombre);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre, edad, nota);
    }
}
