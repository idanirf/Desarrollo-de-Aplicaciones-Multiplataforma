package es.drodriguez.com.Models;

public class Revistas extends Ficha {
    private int numeroRevista;
    private int añoPublicacion;

    public Revistas(String titulo, int numeroRevista, int añoPublicacion) {
        super();
        this.numeroRevista = numeroRevista;
        this.añoPublicacion = añoPublicacion;
    }

    public Revistas(){

    }


    public int getNumeroRevista() {
        return numeroRevista;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }


    @Override
    public String toString() {
        return "\n" + "Revistas" + "\n" +
                "Id: " + getId() + "\n" +
                "Titulo: " + getTitulo() + "\n" +
                "Número revista: " + getNumeroRevista() + "\n" +
                "Año Publicación: " + getAñoPublicacion() + "\n";
    }

    public Revistas titulo(String titulo){
        this.titulo = titulo;
        return this;
    }

    public Revistas numeroRevista(int numeroRevista){
        this.numeroRevista = numeroRevista;
        return this;
    }

    public Revistas añoPublicacion(int añoPublicacion){
        this.añoPublicacion = añoPublicacion;
        return this;
    }
}
