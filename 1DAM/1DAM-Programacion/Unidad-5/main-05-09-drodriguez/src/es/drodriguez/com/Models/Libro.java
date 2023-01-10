package es.drodriguez.com.Models;

public class Libro extends Ficha {
    private  String autor;
    private  String editorial;

    public Libro(String titulo, String autor, String editorial) {
        super();
        this.autor = autor;
        this.editorial = editorial;
    }

    public Libro() {

    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    @Override
    public String toString() {
        return "\n" + "Libro" + "\n" +
                "Id: " + getId() + "\n" +
                "Titulo " + getTitulo() + "\n" +
                "Autor: " + getAutor() + "\n" +
                "Editorial " + getEditorial() + "\n";
    }

    public Libro titulo(String titulo){
        this.titulo = titulo;
        return this;
    }

    public Libro autor(String autor){
        this.autor = autor;
        return this;
    }

    public Libro editorial(String editorial){
        this.editorial = editorial;
        return this;
    }
}
