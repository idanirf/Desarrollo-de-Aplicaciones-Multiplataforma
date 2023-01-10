package es.drodriguez.com.models;

import java.util.Scanner;

public class Reloj {
    private int hora;
    private int minuto;
    private int segundo;

    public Reloj() {

    }
    public int getHora() {
        Scanner sc = new Scanner(System.in);
        hora = sc.nextInt();
            if (hora < 0 || hora > 23){
                System.err.println("Has introducido una hora superior a las 23 horas, se va a registrar de forma errónea.");
            } else if (hora==12){
                System.out.println("Las 24H en el estilo 24 horas");
            }
        return hora;
    }


    public void setHora(int hora) {
        if (hora < 0 || hora > 23){
            System.err.println("Tiempo introducido, superior a las 24:00");
        }else {
            this.hora = hora;
        }
    }

    public int getMinuto() {
        Scanner sc = new Scanner(System.in);
        minuto=sc.nextInt();
        if (minuto < 0 || minuto > 59){
            System.err.println("Error, has introducido un segundo superior a 59");
        }
        return minuto;
    }

    public void setMinuto(int minuto) {
        if (minuto < 0 || minuto > 59){
            System.err.println("Error, has introducido un minuto superior a 59");
        } else{
            this.minuto = minuto;
        }
    }

    public int getSegundo() {
        Scanner sc = new Scanner(System.in);
        segundo=sc.nextInt();
        if (segundo < 0 || segundo > 59){
                System.err.println("Error, has introducido un segundo superior a 59");
        }

        return segundo;
    }

    public void setSegundo(int segundo) {
        if (segundo < 0 || segundo > 59){
            System.err.println("Error, has introducido un minuto superior a 59");
        } else{
            this.segundo = segundo;
        }
    }

    public void actualizarPila(){
        System.out.print("Actualizando pila........");
    }

    @Override
    public String toString() {
        return "La hora almacenada es: " + " H: " + hora + " M: " + minuto + " S: " + segundo;
    }
}
