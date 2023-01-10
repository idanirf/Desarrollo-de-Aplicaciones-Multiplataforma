package es.drodriguez.com;

import es.drodriguez.com.models.Reloj;

public class App {

    public static void main(String[] args) {
        System.out.println("Iniciando Reloj.......-->");
        //Creamos objeto
        Reloj relojUser = new Reloj();

        //Leer datos por teclado
        System.out.print("Hora: ");
        relojUser.getHora();
        System.out.println();
        System.out.print("Minuto: ");
        relojUser.getMinuto();
        System.out.println();
        System.out.print("Segundo: ");
        relojUser.getSegundo();

        //Devolver la hora almacenada.
        System.out.println(relojUser);

        //Actualización pila
        relojUser.actualizarPila();


    }
}
