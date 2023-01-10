package es.drodriguez.com.Models;

public class Libro extends Ficha {
    private String autor;
    private String editorial;

    public Libro(int id, String titulo, String autor, String editorial) {
        super(id, titulo);
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro" + "\n" +
                "Id: " + getId() + "\n" +
                "Titulo" + getTitulo() + "\n" +
                "Autor: " + autor + "\n" +
                "Editorial='" + editorial + "\n";
    }
}
