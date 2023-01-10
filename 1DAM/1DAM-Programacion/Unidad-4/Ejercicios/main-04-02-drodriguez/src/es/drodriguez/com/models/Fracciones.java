package es.drodriguez.com.models;

import java.util.Scanner;

public class Fracciones {
    private int numerador;
    private int denominador;

    public Fracciones() {
    }

    public Fracciones(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
    }


    public int getNumerador() {
        Scanner sc = new Scanner(System.in);
        numerador = sc.nextInt();
        return numerador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public int getDenominador() {
        Scanner sc = new Scanner(System.in);
        denominador = sc.nextInt();
        return denominador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }

    //Sacamos el maximo común divisor
    private int maximoCD() {
        int numer = Math.abs(numerador);
        int denomi = Math.abs(denominador);

        if (denomi == 0) {
            return numer;
        }
        int r;

        while (denomi != 0) {
            r = numer % denomi;
            numer = denomi;
            denomi = r;
        }
        return numer;
    }

    //Simplificamos la fracción
    public Fracciones simplificar(Fracciones r) {
        int dividir = maximoCD();
        r.numerador /= dividir;
        r.denominador /= dividir;
        return r;
    }

    //Sumamos las fracciones
    public static Fracciones sumar(Fracciones n1, Fracciones n2) {
        int a = n1.numerador * n2.denominador + n2.numerador * n1.denominador;
        int b = n1.denominador * n2.denominador;
        Fracciones resultado = new Fracciones(a, b);
        return resultado.simplificar(resultado);
    }

    //Multiplicamos las fracciones
        public static Fracciones multiplicar(Fracciones n1, Fracciones n2) {
        int a = n1.numerador * n2.numerador;
        int b = n1.denominador * n2.denominador;
        Fracciones resultado = new Fracciones(a, b);
        return resultado.simplificar(resultado);
    }

    //Creamos toString
    @Override
    public String toString() {
        return "Fracción: " + numerador + "/" + denominador;
    }
}
