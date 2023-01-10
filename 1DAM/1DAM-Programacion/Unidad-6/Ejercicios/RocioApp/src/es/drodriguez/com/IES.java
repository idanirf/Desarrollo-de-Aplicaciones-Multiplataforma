package es.drodriguez.com;

import Models.Aulas;
import Models.Personas;
import es.drodriguez.com.Utils.PersonasNameComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class IES {
    Scanner sc = new Scanner(System.in);
    public static IES instace = null;
    public static ArrayList<Aulas> aula = new ArrayList<>();
    private int seleccionAula;

    //Constructor
    public IES() {
        createdAulas();
    }

    //Instanciar IES para que se quede siempre creado
    public static IES getInstance() {
        if (instace == null) {
            instace = new IES();
        }
        return instace;
    }

    //Crear aulas
    public void createdAulas() {
        for (int i = 0; i < 10; i++) {
            aula.add(new Aulas());
        }
    }

    //Leer todas las aulas
    public String readAulas() {
        //Creamos un StringBuilder nos permite almacenar cadenas sin que se borren.
        StringBuilder read = new StringBuilder();
        for (int i = 0; i < aula.size(); i++) {
            read.append("Aula: ").append(i + 1).append("\n");
            read.append(aula.get(i).toString()).append("\n");
            read.append(aula.get(i).contadorAlumnos_as()).append("\n").append("\n");

        }
        return read.toString();
    }

    public int readKeyboardClass() {
        seleccionAula = sc.nextInt();
        do {
            try {
                System.out.print("Introduce el número del aula: ");
                seleccionAula = sc.nextInt();

            } catch (Exception e) {
                System.out.println("Recuerda que tenemos 10 aulas, intentalo de nuevo:");
                sc.next();
            }
        } while (seleccionAula < 1 || seleccionAula > 10);
        return seleccionAula;
    }

    //Ordenar aula
    public Aulas ordenarAlumnos(){
        int i;
        i = readKeyboardClass();
        Aulas p = new Aulas();
        p.orderPersonas(aula.get(i));
        return p;
    }
}
