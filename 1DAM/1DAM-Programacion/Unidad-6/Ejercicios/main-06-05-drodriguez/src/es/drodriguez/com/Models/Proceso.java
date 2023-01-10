package es.drodriguez.com.Models;

public class Proceso {
    //Contador de proceso
    private static int contador;
    private String Pnumero;
    private int quantum;


    public Proceso() {
        contador++;
        quantum = 0;
        Pnumero = "P" +  contador;
    }

    public Proceso(int quantum) {
        //aumentamos contador de proceso
        contador++;
        Pnumero = "P" +  contador;
        this.quantum = quantum;
    }

    public Proceso(String Pnumero, int quantum){
        this.Pnumero = Pnumero;
        this.quantum = quantum;
        contador++;
    }

    public String getPnumero() {
        return Pnumero;
    }

    public void setPnumero(String pnumero) {
        Pnumero = pnumero;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public String toString() {
        return "P" + Pnumero + " " + "Q" + quantum;
    }
}
