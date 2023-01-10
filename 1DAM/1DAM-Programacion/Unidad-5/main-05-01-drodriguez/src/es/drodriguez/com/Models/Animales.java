package es.drodriguez.com.Models;

public class Animales {
    private String nombre;
    private String raza;
    private float peso;
    private String color;

    public Animales(String nombre, String raza, float peso, String color) {
        this.nombre = nombre;
        this.raza = raza;
        this.peso = peso;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void vacunar(){

    }

    public void comer(){

    }

    public void dormir(){

    }

    public String hacerRuido(){

        return null;
    }

    public boolean hacerCaso(){

        return false;
    }
}
