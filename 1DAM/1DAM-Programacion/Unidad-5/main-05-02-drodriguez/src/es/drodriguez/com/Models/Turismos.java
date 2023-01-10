package es.drodriguez.com.Models;

public class Turismos extends Vehiculos{
    private int numeroPlazas;


    public Turismos(String marca, String modelo, String color, String matricula, int numeroPlazas) {
        super(marca, modelo, color, matricula);
        this.numeroPlazas= numeroPlazas;

    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public String tipodeUso(){
        if( numeroPlazas >=9){
            return "Uso profesional";
        }else{
            return "Uso particular";
        }
    }

    @Override
    public String toString() {
        return "Coche" + "\n" + "Marca: " + getMarca() + "\n" + "Modelo: " +getModelo() + "\n" + "Color: " + getColor() +
                "\n" + "Matricula: " + getMatricula() + "\n" +"Número de plazas: " + getNumeroPlazas() +"\n" + "Tipo de uso: " + tipodeUso();
    }
}
