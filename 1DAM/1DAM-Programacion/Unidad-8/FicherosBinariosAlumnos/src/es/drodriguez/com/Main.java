package es.drodriguez.com;

import es.drodriguez.com.controllers.AlumnosController;
import es.drodriguez.com.models.Alumno;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AlumnosController alum = new AlumnosController();

        alum.getDir();

        List<Alumno> alumnos = List.of(
                new Alumno("Dani", "1DAM"),
                new Alumno("Jeremy", "1DAM"),
                new Alumno("Azahara", "1DAM")
        );

        alum.guardarAlumnos1a1(alumnos, false);
        List<Alumno> alumRes = alum.leerAlumno1a1();
        for (Alumno a : alumRes) {
            System.out.println(a);
        }

        alum.guardarListaAlumnos(alumnos, false);
        List<Alumno> alumRes1 = alum.leerListaAlumnos();
        for (Alumno a : alumRes1) {
            System.out.println(a);
        }
        alum.backup();
    }
}
