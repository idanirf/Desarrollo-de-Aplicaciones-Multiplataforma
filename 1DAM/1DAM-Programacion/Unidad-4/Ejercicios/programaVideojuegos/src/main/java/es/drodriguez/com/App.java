package es.drodriguez.com;

import es.drodriguez.com.models.Videojuegos;
import es.drodriguez.com.models.tiendaVideojuegos;

public class App
{

    public static void main( String[] args ) {
/*
        tiendaVideojuegos tienda = new tiendaVideojuegos("idanirf");
        System.out.println("Listado de videojuegos de la tienda");
        Videojuegos videojuego1 = new Videojuegos("GTAV","pc",12.00f);
        tienda.crearVideojuego(videojuego1);
        Videojuegos videojuego2 = new Videojuegos("LOL", "switch", 34.00f);
        tienda.crearVideojuego(videojuego2);
        tienda.borrarVideojuego(0);
        System.out.println(tienda.leerTodosVideojuegos());
*/
        tiendaVideojuegos tienda = new tiendaVideojuegos("idanirf");


        //programa realizado con random.
        tienda.initRandom();
        System.out.println(tienda.leerTodosVideojuegos());
        System.out.println();
        tienda.orderPrecioASC();
        System.out.println("Videojuegos ordenados por precio ascendente:");
        System.out.println(tienda.leerTodosVideojuegos());

        System.out.println();
        tienda.orderPrecioDESC();
        System.out.println("Videojuegos ordenados por precio descendente:");
        System.out.println(tienda.leerTodosVideojuegos());

        System.out.println();
        tienda.orderPlataformaASC();
        System.out.println("Videojuegos ordenados por plataforma ascendente:");
        System.out.println(tienda.leerTodosVideojuegos());

        System.out.println();
        tienda.orderPlataformaDESC();
        System.out.println("Videojuegos ordenados por plataforma descendente:");
        System.out.println(tienda.leerTodosVideojuegos());

        System.out.println();
        tienda.orderTituloASC();
        System.out.println("Videojuegos ordenados por titulo ascendente:");
        System.out.println(tienda.leerTodosVideojuegos());

        System.out.println();
        tienda.orderTituloDESC();
        System.out.println("Videojuegos ordenados por titulo ascendente:");
        System.out.println(tienda.leerTodosVideojuegos());

        System.out.println();
        tienda.orderTituloDESC();
        System.out.println("Videojuegos ordenados por titulo descendente:");
        System.out.println(tienda.leerTodosVideojuegos());

        System.out.println();
        System.out.println("Videojuegos que pertenecen a la plataforma PC💻: \n" + tienda.buscarPlataforma("pc"));

        System.out.println();
        System.out.println("Videojuegos buscados por sims2 🌇🌆: \n" + tienda.buscarTitulo("sims2"));

        System.out.println();
        System.out.println("Videojuegos con precio mayor a 45€ 💶: \n" + tienda.getPrecioMayor());

        System.out.println();
        System.out.println("Videojuegos con precio menor a 45€ 💲: \n" + tienda.getPrecioMenor());

        System.out.println();
        System.out.println("Videojuegos con igual a 0.00€, vamos que es graatiss 🤑💲💸: \n" + tienda.getPrecioEquals());

        System.out.println("Número de videojuegos por plataforma: ");
        System.out.println("Play 🥽: " + tienda.getNumeroPlay() + "\n" + "Switch 🕹: " +tienda.getNumeroSwitch() + "\n" + "PC 💻: " + tienda.getNumeroPC() + "\n" + "XBOX 🎮sa: " + tienda.getNumeroxbox());

















    }
}
