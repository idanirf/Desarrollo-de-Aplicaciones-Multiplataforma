package es.drodriguez.com;

import es.drodriguez.com.models.Alumnos;

public class App 
{
    public static void main(String[] args) {
        Alumnos alumno1 = new Alumnos("Dani", 19, 9.5f);
        System.out.println(alumno1);
        Alumnos alumno2 = new Alumnos("Dani", 19, 9.5f);
        System.out.println(alumno2);
    }
}
