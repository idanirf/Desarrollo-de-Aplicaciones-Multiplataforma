package es.drodriguez.com;


import java.io.Serializable;
import java.util.Scanner;

public class Paquete implements Serializable {
    private int numeroUno;
    private int numeroDos;
    private String operacion;


    public Paquete(){
        this.numeroUno = 0;
        this.numeroDos = 0;
        this.operacion = "";
    }

    public Paquete(int numeroUno, int numeroDos, String operacion) {
        this.numeroUno = numeroUno;
        this.numeroDos = numeroDos;
        this.operacion = operacion;
    }


    public int getNumeroUno() {

        return numeroUno;
    }

    public void setNumeroUno(int numeroUno) {
        this.numeroUno = numeroUno;
    }

    public int getNumeroDos() {

        return numeroDos;
    }

    public void setNumeroDos(int numeroDos) {
        this.numeroDos = numeroDos;
    }

    public String getOperacion() {

        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }




    public void visible() {
        System.out.println("Numero Uno: " + numeroUno + " Numero Dos: " + numeroDos + " Operacion: " + operacion);
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "numeroUno=" + numeroUno +
                ", numeroDos=" + numeroDos +
                ", operacion='" + operacion + '\'' +
                '}';
    }
}

