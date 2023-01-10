package es.drodriguez.com.creator;

import es.drodriguez.com.models.Videojuegos;
import es.drodriguez.com.utils.Input;

public class videojuegosCreator {
    //Crear videojuegos aleatorios
    public Videojuegos constructorRandom() {
        //Aleatorio titulo
        String titulo = tituloRandom();
        String plataforma = plataformaRandomn();
        //Aleatorio el precio
        float precio = precioRandom();
        Videojuegos videojuego = new Videojuegos(titulo, plataforma, precio);
        return videojuego ;
    }

    //precios aleatorios
    public int precioRandom() {
        int porcentaje = (int) (Math.random() * 100);
        if (porcentaje < 10) {
            return 0 + (int) (Math.random() * ((0) + 1));
        } else if (porcentaje < 40) {
            return 20 + (int) (Math.random() * ((20 - 40) + 1));
        } else {
            return 50 + (int) (Math.random() * ((50 - 100) + 1));
        }
    }

    //plataformas aleatorias
    public String plataformaRandomn() {
        int porcentaje = (int) (Math.random() * 100);
        if (porcentaje < 10) {
            return "switch";
        } else if (porcentaje < 20) {
            return "xbox";
        } else if (porcentaje < 30) {
            return "pc";
        } else {
            return "play";
        }
    }

    //Titulo aleatorio
    public String tituloRandom() {
        String[] listaNombres ={"Juego feo", "Juego bonito", "Juego aburrido", "sims2", "LOL"};
        int posicion = (int) (Math.random() * listaNombres.length);
        return listaNombres[posicion];

    }

}
