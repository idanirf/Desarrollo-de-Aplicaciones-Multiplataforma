package es.drodriguez.com;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] vector = {9, 1, 2, 4, 10};
        esMayor(vector);
        esMenor(vector);
        esMedia(vector);
        ejercicio1_i();
        ejercicio1_j();
        ordenacionBurbuja(vector);
        ordenacionSeleccion(vector);
        ordenacionInsercion(vector);
        int [] ordenadoQuicksort =  quickSort(vector, 0, vector.length-1);
        System.out.println("El vector ordenado por QUICKSORT es:" +Arrays.toString(ordenadoQuicksort));

    }

    /**
     *
     * @param vector vector que utilizamos para realizar las operaciones con él.
     * @author Daniel Rodríguez Fernández
     * @Date 09/11/2021
     */

    private static void imprimirVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            System.out.println(vector[i] + " ");
        }
    }

    private static void esMayor(int[] vector) {
        //Inicialmente, suponemos que el primer elemento es el máximo porque no conocemos ningún otro
        float maximo = vector[0];
        /*
        Con un for, vamos a ir recorriendo todas las posiciones del array
        desde i=1 (porque el 0 ya lo hemos mirado) hasta i=4
         */
        for (int i = 1; i < vector.length; i++) {
            //vector[i] es el elemento en la posición i
            //y tenemos que mirar si es mayor que el máximo que tenemos guardado (inicialmente, vector[0])
            if (vector[i] > maximo) {
                //como el elemento i del vector es mayor a máximo
                //tenemos vencedor y por lo tanto nuevo máximo
                //pero ojo, que no quiere decir que le ganen otros elementos
                //cuando sigamos mirando el array
                System.out.println(" Nuestro anterior valor máximo era " + maximo + ". Ahora lo es " + vector[i]);
                maximo = vector[i];
            }
        }
        //ya hemos acabado de mirar todas las posiciones del array y por lo tanto maximo es insuperable
        System.out.println("El valor máximo total del array (o vector) es " + maximo);
    }

    private static void esMenor(int[] vector) {
        float minimo = vector[0];
        for (int i = 1; i < vector.length; i++) {
            if (vector[i] < minimo) {
                System.out.println("El valor mínimo anterior es: " + minimo + ". Ahora es: " + vector[i]);
                minimo = vector[i];
            }
        }
        System.out.println("El numero menor es: " + minimo);
    }

    private static void esMedia(int[] vector) {
        float media = 0;
        for (int i = 0; i < vector.length; i++) {
            //+= hace la suma de todos los datos almacenados que hay dentro del vector
            media += vector[i];
        }
        //La media va a ser la suma de todos los valores dentro del vector entre la longitud del vector
        media /= vector.length;
        System.out.println("La media del vector es: " + media);
    }

    private static void ejercicio1_i() {
        int[] array_prueba = new int[]{3, 7, 2, 4, 5, 1};
        int elemento_buscado = 8;
        int posicion_buscada = busquedaElementoLineal(array_prueba, elemento_buscado);
        System.out.println("La posicion buscada es " + posicion_buscada);
    }

    /**
     * Devuelve el índice del elemento si lo encuentra y -1 si no lo encuentra
     *
     * @param vector: Array de elementos en los que buscar
     * @param elemento: Elemento que buscamos
     */
    private static int busquedaElementoLineal(int[] vector, int elemento) {
        int indice = -1;
        boolean encontrado = false;
        for (int i = 0; i < vector.length && !encontrado; i++) {
            if (elemento == vector[i]) {
                //si son iguales, hemos encontrado el elemento
                //actualizaremos el indice que devolveremos en el return
                indice = i;
                //y pondremos 'encontrado' a true para salir del bucle
                encontrado = true;
            }
        }
        return indice;
    }

    private static void ejercicio1_j() {
        int[] vector = new int[]{1, 4, 5, 6, 9, 11};
        int elementoBuscado = 8;
        int posicionBuscada = busquedaRecursiva(vector, elementoBuscado);
        System.out.println("La posicion buscada es " + posicionBuscada);
    }

    /**
     * Devuelve el índice del elemento si lo encuentra y -1 si no lo encuentra
     *
     * @param vector: Array ordenada de elementos en los que buscar
     * @param elemento: Elemento que buscamos
     */
    private static int busquedaRecursiva(int[] vector, int elemento) {
        return busquedaRecursiva(vector, elemento, 0, vector.length - 1);
    }

    private static int busquedaRecursiva(int[] vector, int elemento, int posicionInicial, int posicionFinal) {
        //tendremos que mirar el elemento del medio
        //si el elemento del medio, teniendo en cuenta que el vector está ordenado,
        //es más pequeño que el elemento que buscamos, el elemento que buscamos tendrá que estar
        //A LA DERECHA
        //sino, a la izquierda
        int posicionMedio = (posicionFinal + posicionInicial) / 2;
        System.out.println("\n=============================\n");
        System.out.println("Nueva iteración. La posición de enmedio es " + posicionMedio + " y el elemento ahí es " + vector[posicionMedio]);
        if (vector[posicionMedio] == elemento) {
            System.out.println("Hemos encontrado el número en la posición " + posicionMedio);
            return posicionMedio;
        } else if (posicionInicial == posicionFinal) {
            //el array pequeño que estamos mirando es de tamaño 1 y no hemos encontrado el elemento
            //asi que devolvemos -1
            System.out.println("Posición inicial es " + posicionInicial + " y final " + posicionFinal + ". Son iguales y no hemos encontrado el elemento. El elemento no está.");
            return -1;
        } else if (vector[posicionMedio] < elemento) {
            //miraremos a la derecha
            System.out.println("Como " + elemento + " es mayor a " + vector[posicionMedio] + ", seguiremos mirando a la derecha");
            return busquedaRecursiva(vector, elemento, posicionMedio + 1, posicionFinal);
        } else {
            //vector[posicionMedio] > elemento
            //miraremos a la izquierda
            System.out.println("Como " + elemento + " es menor a " + vector[posicionMedio] + ", seguiremos mirando a la izquierda");
            return busquedaRecursiva(vector, elemento, posicionInicial, posicionMedio - 1);
        }
    }

    private static void ordenacionBurbuja(int[] vector) {
        int temporal = 0;
        int longitud = vector.length;
        //i = 1, porque j puede ser = i y vamos a realizar operaciones con j-1 y no puede haber posición negativa.
        for (int i = 1; i < longitud; i++) {
            for (int j = longitud-1; j >= i; j--) {
                if (vector[j] < vector[j - 1]) {
                    temporal = vector[j];
                    vector[j] = vector[j - 1];
                    vector[j - 1] = temporal;
                }
            }
        }
        System.out.println("La cadena ordenada POR BURBUJA es: "+Arrays.toString(vector));
    }
    private static void ordenacionSeleccion(int[]vector){
        int temporal;
        int posicionMinima;
        int longitud = vector.length;
        for (int i = 0; i < longitud-1; i++) {
            posicionMinima = i;
            for (int j = i + 1; j < longitud; j++) {
                if (vector[j] < vector[posicionMinima]) {
                    posicionMinima = j;
                    temporal = vector[i];
                    vector[i] = vector[posicionMinima];
                    vector[posicionMinima] = temporal;
                }
            }
        }
        System.out.println("La cadena ordenada POR SELECCIÓN es: "+Arrays.toString(vector));
    }
    private static void ordenacionInsercion(int []vector){
        int temporal;
        int longitud = vector.length;
        for(int i = 0; i < longitud; i++) {
            temporal = vector[i];
            int j = i - 1;
            while ((j >= 0) && (temporal < vector[j])) {
                vector[j + 1] = vector[j];
                j--;
            }
            vector[j + 1] = temporal;
        }
        System.out.println("El vector ordenado POR INSERCIÓN es: " + Arrays.toString(vector)) ;
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
