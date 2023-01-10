package es.drodriguez.com.Models;

import es.drodriguez.com.Controller.BibliotecaController;
import es.drodriguez.com.Exceptions.BibliotecaExceptions;
import es.drodriguez.com.Utils.Console;
import java.util.List;
import java.util.Scanner;


public class Menu {
    private static final BibliotecaController controller = BibliotecaController.getInstance();
    Scanner sc = new Scanner(System.in);
    String nombreUser = Console.getString("Introduce tu usuario: ");


    public void menu() throws BibliotecaExceptions {
        do{
            System.out.println("MENÚ DE OPCIONES BIBLIOTECA:");
            System.out.println("Introuce la opción deseada de 1-5");
            System.out.println("1. Añadir Item");
            System.out.println("2. Buscar Item");
            System.out.println("3. Eliminar item");
            System.out.println("4. Listado de la biblioteca");
            System.out.println("5. Exit");
            String selecion = Console.getString("Selecciona una opción del menú [1-5]: ");

            var expresionRegularMenu = "[1-5]";
            if (selecion.matches(expresionRegularMenu))
            switch (selecion){
                case "1":
                    menuDos();
                    break;
                case "2":
                    findElement();
                    break;

                case "3":
                    borrarElemento();
                    break;

                case "4":
                    listaBiblioteca();
                    break;

                case "5":
                    exit();
                    break;
            }
        } while (true);

    }

    public int menuDos(){
        System.out.println("1. DVD 2.Libro 3.Revista");
        int tipoItem = sc.nextInt();
        if (tipoItem == 1){
            crearDVD();

        } else if (tipoItem == 2) {
            crearLibro();
        }else if (tipoItem == 3) {
            crearRevista();
        }
        return tipoItem;
    }

    private void crearRevista() {
        String titulo = Console.getString("Introduce el titulo: ");
        String numeroRevista = Console.getString("Numero Revista: ");
        String añoPublicacion = Console.getString("Año publicación: ");

        Ficha revista = new Revistas()
                .titulo(titulo)
                .numeroRevista(Integer.parseInt(numeroRevista))
                .añoPublicacion(Integer.parseInt(añoPublicacion));
        try{
            controller.crearRevista((Revistas) revista);
            System.out.println(revista);
        }catch (Exception e){
            System.out.println("Error al crear registro");
        }
    }

    private static void crearDVD(){
        String titulo = Console.getString("Introduce el titulo: ");
        String director = Console.getString("Director: ");
        String año = Console.getString("Año de producción: ");
        String tipoItem = Console.getString("Tipo de película: ");

        Ficha dvd = new DVD()
                .titulo(titulo)
                .director(director)
                .año(año)
                .tipo(tipoItem);
        try{
            controller.crearDVD((DVD) dvd);
            System.out.println(dvd);
        }catch (Exception e){
            System.out.println("Error al crear registro");
        }
    }

    private static void crearLibro(){
        String titulo = Console.getString("Introduce el titulo: ");
        String autor = Console.getString("Autor: ");
        String editorial = Console.getString("Editorial: ");

        Ficha libro = new Libro()
                .titulo(titulo)
                .autor(autor)
                .editorial(editorial);
        try{
            controller.crearLibro((Libro) libro);
            System.out.println(libro);
        }catch (Exception e){
            System.out.println("Error al crear registro");
        }
    }

    private static void listaBiblioteca(){
        System.out.println("Mostrando el listado de la biblioteca: ");
        List<Ficha> ficha = controller.getAllBiblioteca();
        System.out.println("Stock biblioteca: " + ficha.size());
        for (Ficha fichas: ficha){
            System.out.println(ficha);
        }
    }

    private static void borrarElemento(){
        System.out.println("Borrar elemento de la biblioteca: ");
        String titulo = Console.getString("Nombre del elemento de la biblioteca que quieres borrar: ");
        try {
            var resultado = controller.borrarElement(titulo);
            System.out.println("El elemento ha sido borrado de la biblioteca");
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Error al borrar el elemento de la biblioteca");
        }
    }

    private static void findElement() throws BibliotecaExceptions {
        String nombre = Console.getString("Nombre: ");
        var resultado = controller.getFichaByNombre(nombre);
        System.out.println(resultado);
    }

    private static void exit(){
        System.out.println("Bye, bye!");
        System.exit(0);
    }
}
