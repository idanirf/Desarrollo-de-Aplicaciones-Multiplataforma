package es.drodriguez.com.models;

public class Alumno {
    private int id;
    private String nombre;
    private String curso;
    private static int contador = 0;

    public Alumno(String nombre, String curso) {
        this.id = ++contador;
        this.nombre = nombre;
        this.curso = curso;
    }

    //Constructor para la lectura de datos de los alumnos
    public Alumno(int id, String nombre, String curso) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Id: " + id + "Nombre: " + nombre + " Curso: " + curso;
    }

    public String toFile() {
        return id + ";" + nombre + ";" + curso;
    }
}
