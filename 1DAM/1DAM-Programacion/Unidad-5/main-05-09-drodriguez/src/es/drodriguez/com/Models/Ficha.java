package es.drodriguez.com.Models;

import java.util.Scanner;

public abstract class Ficha {
    Scanner sc = new Scanner(System.in);
    private final int id;
    public String titulo;
    private static int idcontador = 0;

    public Ficha() {
        this.id = ++idcontador;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Ficha{" +
                "id " + getId() +
                ", titulo " + getTitulo() + '\'' +
                '}';
    }

    public Ficha titulo(String titulo){
        this.titulo = titulo;
        return this;
    }

}
