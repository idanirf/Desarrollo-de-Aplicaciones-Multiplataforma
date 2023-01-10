package es.drodriguez.com.Models;

public class Perros extends Animales{

    public Perros(String nombre, String raza, float peso, String color) {
        super(nombre, raza, peso, color);
        this.sacarPaseo();
    }

    @Override
    public String hacerRuido() {
        super.hacerRuido();
        return "Soy un perro 🐕 y ladro 🔈";
    }

    @Override
    public boolean hacerCaso() {
        super.hacerCaso();
        int ok = random();
        if (ok <=90 ){
            return true;
        }else{
            return false;
        }
    }

    public String sacarPaseo() {
        return "Soy un perro que sale de paseo 🐕🚶‍️‍";
    }

    public int random(){
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return "Nombre: " +getNombre() +"\n" + "Raza: " + getRaza() + "\n"+ "Color: " + getColor() + "\n"+  hacerRuido() + "\n"  + hacerCaso() + "\n" + sacarPaseo() + "\n";
    }
}
