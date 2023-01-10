package es.drodriguez.com.Models;

public class DVD extends Ficha {
    private String director;
    private String año;
    private String tipo;

    public DVD(){

    }

    public DVD(String titulo, String director, String año, String tipo) {
        super();
        this.director = director;
        this.año = año;
        this.tipo = tipo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public DVD titulo(String titulo){
        this.titulo = titulo;
        return this;
    }

    public DVD director(String director) {
        this.director = director;
        return this;
    }

    public DVD año(String año) {
        this.año = año;
        return this;
    }

    public DVD tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public String toString() {
        return "\n" + "DVD" + "\n" +
                "Id: " + getId() + "\n" +
                "Titulo: " + getTitulo() + "\n" +
                "Director: " + director + "\n" +
                "Año: " + año + "\n" +
                "Tipo: " + tipo + "\n";
    }
}