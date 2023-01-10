package es.drodriguez.com.Models;

public class Revistas extends Ficha {
    private int numeroRevista;
    private int añoPublicacion;

    public Revistas(int id, String titulo, int numeroRevista, int añoPublicacion) {
        super(id, titulo);
        this.numeroRevista = numeroRevista;
        this.añoPublicacion = añoPublicacion;
    }

    public int getNumeroRevista() {
        return numeroRevista;
    }

    public void setNumeroRevista(int numeroRevista) {
        this.numeroRevista = numeroRevista;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    @Override
    public String toString() {
        return "\n" + "Revistas" + "\n" +
                "Id: " + getId() + "\n" +
                "Titulo" + getTitulo() + "\n" +
                "Número revista: " + getNumeroRevista() + "\n" +
                "Año Publicación: " + getAñoPublicacion() + "\n";
    }
}
