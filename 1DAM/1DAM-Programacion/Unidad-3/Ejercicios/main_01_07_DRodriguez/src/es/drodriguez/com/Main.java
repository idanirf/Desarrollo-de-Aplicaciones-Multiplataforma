package es.drodriguez.com;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int []vector ={2,4,5,0,1,3,8,6};
        int [] ordenadoQuicksort =  quickSort(vector, 0, vector.length-1);
        System.out.println("El vector ordenado por QUICKSORT es:" + Arrays.toString(ordenadoQuicksort));
    }
    public static int [] quickSort(int vector[], int posicionIzquierda, int posicionDerecha) {

        int pivote=vector[posicionIzquierda]; // tomamos primer elemento como pivote
        int i=posicionIzquierda;         // i realiza la búsqueda de izquierda a derecha
        int j=posicionDerecha;         // j realiza la búsqueda de derecha a izquierda
        int aux;

        while(i < j){                          // mientras no se crucen las búsquedas
            while(vector[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
            while(vector[j] > pivote) j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                aux= vector[i];                      // los intercambia
                vector[i]=vector[j];
                vector[j]=aux;
            }
        }

        vector[posicionIzquierda]=vector[j];      // se coloca el pivote en su lugar de forma que tendremos
        vector[j]=pivote;      // los menores a su izquierda y los mayores a su derecha

        if(posicionIzquierda < j-1)
            quickSort(vector,posicionIzquierda,j-1);          // ordenamos subarray izquierdo
        if(j+1 < posicionDerecha)
            quickSort(vector,j+1,posicionDerecha);          // ordenamos subarray derecho
        return vector;
    }
}
