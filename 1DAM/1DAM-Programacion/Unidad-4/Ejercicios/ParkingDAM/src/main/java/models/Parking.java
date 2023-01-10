package models;

import com.diogonunes.jcolor.Attribute;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class Parking {
    private int NUMERO_PLAZAS = pedirTamanoParking();
    private Coche [] CochesEstacionados;


    public void setCochesEstacionados(Coche[] cochesEstacionados) {
        CochesEstacionados = cochesEstacionados;
    }

    public Parking() {
        CochesEstacionados = new Coche[NUMERO_PLAZAS];
    }

    public int getNUMERO_PLAZAS() {
        return NUMERO_PLAZAS;
    }

    public void setNUMERO_PLAZAS(int NUMERO_PLAZAS) {
        this.NUMERO_PLAZAS = NUMERO_PLAZAS;
    }

    public Coche[] getCochesEstacionados() {
        return CochesEstacionados;
    }

    public void setVectorPlazas(Coche[] CochesEstacionados) {
        this.CochesEstacionados = CochesEstacionados;
    }

    private int pedirTamanoParking() {
        Scanner sc = new Scanner(System.in);
        int tamano=0;
        do {
            try {
                System.out.println("Introduce el número de plazas del parking: ");
                tamano = sc.nextInt();
                System.out.println("El parking dispone de:  " + tamano + "  plazas.");
            }catch (Exception e) {
                System.out.println("Dato incorrecto");
                sc.next();
            }
        } while (tamano <= 0);
        return tamano;
    }


    public void crearCoche() {
        Coche newCoche = new Coche();
        for(int i = 0; i < CochesEstacionados.length; i++){
            if (CochesEstacionados[i] != null){
            } else if (CochesEstacionados[i]==null){
                CochesEstacionados[i] = leerDatos();
                return;
            }
        }
    }

    public void borrarCoche() {
        Coche newCoche = new Coche();
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < CochesEstacionados.length; i++){
            System.out.print("Introduce la plaza: ");
            i=sc.nextInt()-1;
            if (CochesEstacionados[i] != null){
                CochesEstacionados[i] = null;
                return;
            } else if (CochesEstacionados[i]==null){
                System.out.println(colorize("No hemos encontrado ningún coche en esta plaza.",RED_TEXT()));
            }
        }
    }

    public Coche leerDatos(){
        Scanner sc = new Scanner(System.in);
        Coche crearCoche = new Coche();

            try{
                System.out.print("Introduce matricula: ");
                crearCoche.getMatricula();
                System.out.println("Introduce color");
                crearCoche.getColor();
                return crearCoche;

            }catch (Exception e) {
                System.out.println("Datos inválidos, introduce de nuevo matricula y color: ");
                sc.next();

            }

        return crearCoche;
    }

    public void OrdenarFecha() {
        Coche aux;
        for (int i = 0; i < CochesEstacionados.length; i++) {
            for (int j = i + 1; j < CochesEstacionados.length; j++) {

                if (CochesEstacionados[i] != null && CochesEstacionados[j] != null &&
                        CochesEstacionados[i].getNow().minusDays(12).compareTo(CochesEstacionados[j].getNow()) > 0) {
                    aux = CochesEstacionados[i];
                    CochesEstacionados[i] = CochesEstacionados[j];
                    CochesEstacionados[j] = aux;
                }
            }
        }
    }

    public void imprimirParking() {
            for (Coche columna: CochesEstacionados ) {
                if (columna == null) {
                    System.out.print(colorize("[L]", Attribute.BLUE_BACK(), BLACK_TEXT()));
                } else {
                    System.out.print(colorize("[0]",RED_BACK(),BLACK_TEXT()));
                }
            }
        System.out.println();
        }
    }


