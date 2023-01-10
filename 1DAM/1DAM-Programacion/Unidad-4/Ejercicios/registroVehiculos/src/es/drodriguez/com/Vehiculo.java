package es.drodriguez.com;

class Vehiculo {
    String marca;
    String modelo;
    String matricula;
    int anioFabricacion;
    String cilindrada;
    String combustible;
    String antiguedad(){
        return " Su antiguedad es: " + (2022 - anioFabricacion) + " años.";
    }
    String ficha() {
        return "La marca es: " + marca + " el modelo es: " + modelo + " la matricula es: " + matricula + " el año de fabriacion es: "
                + anioFabricacion + " la cilindrada es: " + cilindrada + " el tipo de combustuble es: " + combustible;
    }
}
