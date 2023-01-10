package es.drodriguez.com.Models;

public class Gatos extends Animales{
    public Gatos(String nombre, String raza, float peso, String color) {
        super(nombre, raza, peso, color);
    }

    @Override
    public String hacerRuido() {
        super.hacerRuido();
        return "Soy un gato 😼 y maúllo 🔈";
    }

    @Override
    public boolean hacerCaso() {
        super.hacerCaso();
        int ok = random();
        if (ok <=5 ){
            return true;
        }else{
            return false;
        }
    }

    public String toserBolaPelo(){
        return "Sou un gato 😸 que tose bola 🧶 de pelo";
    }

    public int random(){
        return (int) (Math.random() * 100);
    }

    @Override
    public String toString() {
        return "Nombre: " +getNombre() +"\n" + "Raza: " + getRaza() + "\n"+ "Color: " + getColor() + "\n"+  hacerRuido() + "\n"  + hacerCaso() + "\n" + toserBolaPelo() + "\n";
    }
}
