package es.drodriguez.com;

import es.drodriguez.com.controllers.AlumnoController;
import es.drodriguez.com.models.Alumno;

import java.util.List;

public class Main {
    public static void main(String[] args) {
	AlumnoController alu = new AlumnoController();
    alu.getDir();

    List<Alumno> alumnos = List.of(
            new Alumno ("daniel", "1DAM"),
            new Alumno("azahara", "1DAM"),
            new Alumno("jeremy", "1DAM")
        );

        alu.escribirFicheroTexto(alumnos, false);

        List<Alumno> alumnosResulado = alu.leerAlumno();
        for (Alumno a : alumnosResulado) {
            System.out.println(a);
        }

    alu.backup();
    }
}
