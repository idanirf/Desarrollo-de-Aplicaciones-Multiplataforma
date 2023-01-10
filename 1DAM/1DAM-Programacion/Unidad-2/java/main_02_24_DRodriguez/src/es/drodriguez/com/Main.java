package es.drodriguez.com;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hola 1 DAM.");
        float[] notas = new float [5];

        //Metemos valores
        for (int i= 0; i<notas.length; i++) {
            notas[i]= (float)(Math.random()*10);
        }
        //Imprimir
        for (int i = 0; i< notas.length; i++) {
            System.out.println("Valor " + (i + 1) + ":" + notas[i]);
        }
        System.out.println("El valor de la posición 4: " + notas[3]);
        System.out.println("El valor de la posición 1: " + notas[0]);

        //Calcular la media
        float acumulador = 0;
        for (int i = 0; i < notas.length; i++)
            acumulador = acumulador +notas[i];
        System.out.println("El acumulado es: " +acumulador);

        float media =acumulador/notas.length;
        System.out.println("La media es: " +media);

    } //Main

}

