package creator;

import models.Butaca;
import models.SalaCine;
import utils.input;

/*
public class cineCreator {
    public SalaCine cineCreatorConstructor() {
        //Lectura fila
        int fila = filaReader();
        //Lectura columna
        int columna = columnaReader();
        Butaca seleccion = new Butaca(fila, columna);
        return seleccion;
    }

    //Leer por teclado la columna
    private int columnaReader() {
        input input = new input();
        boolean ok = false;
        int columna = -1;
        do{
            System.out.println("Introduce la columna donde te quieres sentar entre 1 y 9: ");
            columna = input.readInt();
            if(columna <= 0 || columna > 9){
                System.out.println("Error al registrar la columna, debe estar entre 1 y 9");
            } else {
                ok = true;
            }
        }while(!ok);
        return columna;
    }

    //Leer por teclado la fila
    private int filaReader() {
        input input = new input();
        boolean ok = false;
        int fila = -1;
        do{
            System.out.println("Introduce la fila donde te quieres sentar, entre 1 y 5: ");
            fila = input.readInt();
            if(fila <= 0 || fila > 5){
                System.out.println("Error al registrar la fila, debe estar entre 1 y 5");
            } else {
                ok = true;
            }
        } while(!ok);
        return fila;
    }


}
*/
