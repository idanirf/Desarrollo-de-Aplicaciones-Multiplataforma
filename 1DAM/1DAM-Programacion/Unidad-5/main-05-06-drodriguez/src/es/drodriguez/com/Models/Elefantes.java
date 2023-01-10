package es.drodriguez.com.Models;

public class Elefantes extends Animales {

    public Elefantes(String nombre, String raza, float peso, String color) {
        super(nombre, raza, peso, color);
    }

    @Override
    public String toString() {
        return "Nombre: " +getNombre() +"\n" + "Raza: " + getRaza() + "\n"+ "Color: " + getColor() + "\n"+  hacerRuido() + "\n"  + dormir() + "\n" + comer() + "\n";
    }
}
