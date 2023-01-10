package models;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static models.EstadoSala.libre;

public class Butaca {
    private int fila;
    private int columna;
    private EstadoSala estado;


    public EstadoSala getEstado() {
        return estado;
    }

    public void setEstado(EstadoSala estado) {
        this.estado = estado;
    }

    public Butaca() {
        butacaRandom();
    }

    public void estadoRandom(EstadoSala estado){
        int probabilidad = (int) (Math.random()* 100 +1);
        if (probabilidad <=30){
            this.estado = EstadoSala.ocupada;
        } else if (probabilidad <= 30){
            this.estado = EstadoSala.reservada;
        } else if (probabilidad >=60){
            this.estado = EstadoSala.libre;
        }
    }

    public void butacaRandom(){
        this.fila = (int) (Math.random() * 5 +1);
        this.columna = (int) (Math.random() * 9 +1);
        estadoRandom(estado);

    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        String imprimir = " ";
        if (this.estado == EstadoSala.libre){
            imprimir = colorize("[L]", GREEN_BACK(), BLACK_TEXT());
        } else if (this.estado == EstadoSala.ocupada){
            imprimir = colorize("[O]", RED_BACK(), BLACK_TEXT());
        } else{
            imprimir = colorize("[R]", YELLOW_BACK(), BLACK_TEXT());

        }
        return imprimir;
    }
}
