package es.drodriguez.com.creator;

import es.drodriguez.com.models.Alumnos;
import utils.Input;

public class AlumnoCreator {
    //Crear alumno leyendo datos por consola
    public Alumnos createFromTerminal(){
        // Leemos el nombre
        String nombre = leerNombre();
        //Leemos la edad
        int edad = leerEdad();
        //Leemos la nota
        float nota = leerNota();
        //Crear alumno
        Alumnos alumno = new Alumnos(nombre, edad, nota);
        //Devolver alumno
        return alumno ;
    }

    public Alumnos createRandom(){
        // Leemos el nombre
        String nombre = leerNombre();
        //Leemos la edad
        int edad = leerEdad();
        //Leemos la nota
        float nota = leerNota();
        Alumnos alumno = new Alumnos(nombre, edad, nota);
        return alumno ;
    }

    private float leerNota() {
        Input input = new Input();
        boolean ok = false;
        float nota = 0;
        do {
            System.out.println("Introduce la nota del alumno: ");
            nota = input.readFloat();
            if (nota < 0 || nota > 10) {
                System.out.println("La nota debe estar entre 0 y 10");
            } else {
                ok = true;
            }
        } while (!ok);
        return nota;
    }

    private int leerEdad() {
        Input input = new Input();
        boolean ok = false;
        int edad = -1;
        do {
            System.out.println("Introduce la edad del alumno: ");
            edad = input.readInt();
            if (edad <= 0 || edad >= 100){
                System.out.println("La edad se tiene que encontrar en 1 y 99");
            } else {
                ok = true;
            }
        } while (!ok);
        return edad;
    }

    private String leerNombre(){
        Input input = new Input();
        boolean ok = false;
        String nombre = "";
        do {
            System.out.println("Introduce el nombre del alumno: ");
            nombre = input.readLine();
            if (nombre.length()>0 && nombre.length()<100){
                System.out.println();
            }else {
                System.err.println("Introduce un nombre correcto: ");
            }
        } while (!ok);
        return nombre;
    }

}
