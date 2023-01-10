package es.drodriguez.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        Alumno alumno1 = leerAlumno();
        System.out.println(alumno1.informe() + alumno1.edad());
        Alumno alumno2 = leerAlumno();
        System.out.println(alumno2.informe() + alumno2.edad());
        Alumno alumno3 = leerAlumno();
        System.out.println(alumno3.informe() + alumno3.edad());
    }

    private static Alumno leerAlumno() {
        Scanner sc = new Scanner(System.in);
        Alumno alum = new Alumno();
        System.out.println("Introduce el nombre del alumno: ");
        alum.name = sc.nextLine();
        System.out.println("Introduce apellidos del alumno: ");
        alum.apellido = sc.nextLine();

        boolean ok = false;

        do {
            System.out.println("Introduce el curso: ");
            alum.curso = sc.next();
            if (alum.curso.equals("1")){
                ok = true;
            } else if (alum.curso.equals("2")){
                ok = true;
            }
        } while (!ok);

        ok = false;
        do {
            System.out.println("Introduce el grupo: ");
            alum.grupo = sc.next();
            if (alum.grupo.equals("DAW")){
                ok = true;
            } else if (alum.grupo.equals("DAM")){
                ok = true;
            }
        } while (!ok);


        System.out.println("Introduce el año de nacimiento: ");
        alum.fechaNacimiento = sc.nextInt();

        return alum;
    }
}
