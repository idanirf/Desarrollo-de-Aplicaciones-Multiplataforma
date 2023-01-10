package es.drodriguez.com.Models;

public abstract class Animales {
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

    public String comer(){
        return "Soy un animal que come  🍽";

    }

    public String dormir(){
        return "Soy un animal que duerme 😴💤";

    }

    public String hacerRuido(){

        return "Soy un animal que hace ruido 📢";
    }

    public boolean hacerCaso(){

        return false;
    }
}
