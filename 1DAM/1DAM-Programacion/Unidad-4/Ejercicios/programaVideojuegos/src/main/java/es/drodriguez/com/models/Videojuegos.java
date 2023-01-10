package es.drodriguez.com.models;

import es.drodriguez.com.creator.videojuegosCreator;
import es.drodriguez.com.models.tiendaVideojuegos;



import java.util.Objects;

public class Videojuegos {
    //Declaración variables.
    String titulo = "";
    String plataforma = "";
    float precio = 0;
    private int stock = 0;

    //Declaración constructor.
    public Videojuegos(String titulo, String plataforma, float precio) {
        setTitulo(titulo);
        setPlataforma(plataforma);
        setPrecio(precio);
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null && titulo.trim().length() > 0 && titulo.trim().length() < 100) {
            this.titulo = titulo;
        } else {
            System.err.println("Debes introducir al menos un carácter.");
        }
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        if (plataforma == "pc" || plataforma == "xbox" || plataforma == "play" || plataforma == "switch") {
            this.plataforma = plataforma;
        } else {
            throw new IllegalArgumentException(String.format("Debes introducir una plataforma correcta."));
        }
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        if (precio >= 0 && precio <= 199.99f) {
            this.precio = precio;
        } else {
            throw new IllegalArgumentException(String.format("Debes introducir un precio correcto."));

        }
    }

    @Override
    public String toString() {
        return "Videojuego: " + titulo + ", para la plataforma: " + plataforma + " y el precio es de: " + precio + "€";
    }
}
