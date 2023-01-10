package es.drodriguez.com;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Alumnos [] vectorDatos = new Alumnos[2];
        registroAlumnos(vectorDatos);
        printVector(vectorDatos);
    }

    private static void registroAlumnos(Alumnos[] vectorDatos) {
        for (int i = 0; i < vectorDatos.length; i++) {
            vectorDatos[i]=leerAlumno();
        }
    }

    private static void printVector(Alumnos[] vectorDatos) {
        for (int i = 0; i < vectorDatos.length; i++) {
            printVector(vectorDatos[i]);
        }
    }

    private static Alumnos leerAlumno() {
        Scanner sc = new Scanner(System.in);
        Alumnos alum = new Alumnos();
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

    private static void printVector(Alumnos alum) {
        System.out.println("Nombre del alumno es: " + alum.name + "\n" +
                " Apellidos: " + alum.apellido + "\n" +
                " Curso: " + alum.curso + "\n" +
                " Nacido en el año: " + alum.fechaNacimiento + ".");
    }
}