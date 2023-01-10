package es.drodriguez.com.Models;

public class DVD extends Ficha {
    private String director;
    private int año;
    private String tipo;

    public DVD(int id, String titulo, String director, int año, String tipo) {
        super(id, titulo);
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

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n" + "DVD" + "\n" +
                "Id: " + getId() + "\n" +
                "Titulo: " + getTitulo() + "\n" +
                "Director: " + getDirector() + "\n" +
                "Año: " + getAño() + "\n" +
                "Tipo: " + getTipo() + "\n";
    }
}
