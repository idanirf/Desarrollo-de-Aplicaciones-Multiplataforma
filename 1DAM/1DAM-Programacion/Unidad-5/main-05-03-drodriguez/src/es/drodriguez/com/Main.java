package es.drodriguez.com;

import es.drodriguez.com.Models.DVD;
import es.drodriguez.com.Models.Ficha;
import es.drodriguez.com.Models.Libro;
import es.drodriguez.com.Models.Revistas;

public class Main {
    public static void main(String[] args) {

        //Nuevo libro
        Ficha libro = new Libro(1, "Coders", "Daniel Rodríguez", "MC-GRAWHILL");
        System.out.println(libro);

        //Nueva revista
        Ficha revista = new Revistas(2, "Coders-Read",1, 2022);
        System.out.println(revista);
        
        //Nuevo DVD
        Ficha DVD = new DVD(3, "Coders-DVD", "Daniel Rodríguez", 2022, "Ciencia");
        System.out.println(DVD);



    }
}

