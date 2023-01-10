package es.drodriguez.com;

import Models.Aulas;

public class App {

    public static void main(String[] args) {
        System.out.println("Bienvenido al IES... Procesando datos");

        //Crear objeto IES
        IES ies = IES.getInstance();

        //Mostrar aulas del Instituto con los alumnos
        System.out.println(ies.readAulas());

        //Ordenar un aula pedida por teclado
        System.out.println(ies.ordenarAlumnos());


    }
}
