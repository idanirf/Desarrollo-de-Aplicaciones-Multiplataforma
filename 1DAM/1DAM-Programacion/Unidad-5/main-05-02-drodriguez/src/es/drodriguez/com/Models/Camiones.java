package es.drodriguez.com.Models;

public class Camiones extends Vehiculos{
    private int mma;

    public Camiones(String marca, String modelo, String color, String matricula, int mma) {
        super(marca, modelo, color, matricula);
        this.mma=mma;
    }


    public int getMma() {
        return mma;
    }

    public void setMma(int mma) {
        this.mma = mma;
    }

    public String tipodeMercancia(){
        if( mma >=3500){
            return "Mercancía peligrosa";
        }else{
            return "Puedes estar tranquilo con la mercancía";
        }
    }

    @Override
    public String toString() {
        return "\n" + "Camión" + "\n" + "Marca: " + getMarca() + "\n" + "Modelo: " +getModelo() + "\n" + "Color: " + getColor() +
                "\n" + "Matricula: " + getMatricula() + "\n" +"Masa máxima autorizada de su camión: " + getMma() +"\n" + "Tipo de mercancía: " + tipodeMercancia();
    }
}
