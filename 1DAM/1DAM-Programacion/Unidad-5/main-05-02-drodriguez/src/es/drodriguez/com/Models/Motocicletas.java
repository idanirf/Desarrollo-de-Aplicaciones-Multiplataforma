package es.drodriguez.com.Models;

public class Motocicletas extends Vehiculos{
    private int cilindrada;
    public Motocicletas(String marca, String modelo, String color, String matricula, int cilindrada) {
        super(marca, modelo, color, matricula);
        this.cilindrada=cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String permisoCirculacion(){
        if( cilindrada >=125){
            return "Necesitas permiso de circulación";
        }else{
            return "No es necesario permiso de circulación";
        }
    }

    @Override
    public String toString() {
        return "\n" + "Motocicletas" + "\n" + "Marca: " + getMarca() + "\n" + "Modelo: " +getModelo() + "\n" + "Color: " + getColor() +
                "\n" + "Matricula: " + getMatricula() + "\n" +"Cilindrada: " + getCilindrada() +"\n"
                + "¿Necesario permiso de circulación?  " + permisoCirculacion();
    }
}
