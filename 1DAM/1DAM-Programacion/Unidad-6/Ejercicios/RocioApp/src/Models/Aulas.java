package Models;

import es.drodriguez.com.Utils.PersonasNameComparator;

import java.util.*;

public class Aulas  {
    Scanner sc = new Scanner(System.in);
    ArrayList<Personas>personasAula;

    //Constructor de las aulas
    public Aulas(){
        personasAula = new ArrayList<>();
        createdPersonas();
    }

    public void createdPersonas(){
        for (int i = 0; i < 15; i++){
            int porcentaje = (int) (Math.random() * 100);
            if (porcentaje<60){
                personasAula.add(new Alumnas());
            }else{
                personasAula.add(new Alumnos());
            }
        }
    }

    //Devolver por pantalla las aulas con alumnos
   @Override
    public String toString(){
        StringBuilder print = new StringBuilder();
        for (Personas personas : personasAula){
            if (personas instanceof Alumnos){
                print.append(personas).append("\n");
            }else if (personas instanceof Alumnas){
                print.append(personas).append("\n");
            }
        }
        return print.toString();
    }

    //Contador de número de alumnos y alumna que tiene un aula.
    public String contadorAlumnos_as(){
        int alumnos = 0;
        int alumnas = 0;
        for (Personas personas : personasAula){
            if (personas instanceof Alumnos){
                alumnos++;
            }else{
                alumnas++;
            }
        }
        return "\n" + "El número de alumnos del aula es de: " + alumnos + "\n" + "El número de alumnas del aula es de: " + alumnas;
    }

    public void orderPersonas(Aulas aulas){
        PersonasNameComparator personasC = new PersonasNameComparator();
        personasAula.sort(personasC);
       /* for (Personas p : personasAula){
            System.out.println(p);
        }*/
        personasAula.sort(new Personas());
    }
}
