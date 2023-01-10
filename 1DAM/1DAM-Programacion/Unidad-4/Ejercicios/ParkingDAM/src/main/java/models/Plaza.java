package models;

import java.util.Arrays;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Attribute.BLACK_TEXT;

public class Plaza {
    private int NumeroPlaza;
    private Coche [] listaCoche;

    public Plaza(int numeroPlaza, Coche[] listaCoche) {
        NumeroPlaza = numeroPlaza;
        this.listaCoche = listaCoche;
    }

    public int getNumeroPlaza() {
        return NumeroPlaza;
    }

    public void setNumeroPlaza(int numeroPlaza) {
        NumeroPlaza = numeroPlaza;
    }

    public Coche[] getListaCoche() {
        return listaCoche;
    }

    public void setListaCoche(Coche[] listaCoche) {
        this.listaCoche = listaCoche;
    }


    @Override
    public String toString() {
        return "Plaza{" +
                "NumeroPlaza=" + NumeroPlaza +
                ", listaCoche=" + Arrays.toString(listaCoche) +
                '}';
    }
}
