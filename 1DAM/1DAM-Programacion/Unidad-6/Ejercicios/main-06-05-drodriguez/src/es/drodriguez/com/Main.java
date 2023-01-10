package es.drodriguez.com;

import es.drodriguez.com.Models.Cola;
import es.drodriguez.com.Models.Proceso;

public class Main {

    public static void main(String[] args) {
        Cola<Proceso>PROCESO = new Cola<Proceso>();
        int temporizador = 0;

        while (temporizador <= 60){
            System.out.println(PROCESO);
            if (temporizador % 2 == 0) {
                System.out.println("Se ha iniciado un nuevo proceso");
                var newProceso = new Proceso((int) (Math.random() * 10) +2);
                System.out.println("**Proceso**" + newProceso.getPnumero());
                PROCESO.encolar(newProceso);
            }
            if (temporizador % 4 == 0) {
                Proceso aux = PROCESO.desencolar();
                System.out.println("Procesando: " + aux.getPnumero() + " " + aux.getQuantum());
                aux.setQuantum(aux.getQuantum() - 2);
                if (aux.getQuantum() > 0) {
                    PROCESO.encolar(aux);
                } else {
                    System.out.println(aux.getPnumero() + "procesado");
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            temporizador++;
        }
        System.out.println(PROCESO);
        System.out.println();
        System.out.println();
        System.out.println("Vaciar cola");
        while (!PROCESO.isVacia()){
            System.out.println(PROCESO.toString());
            PROCESO.desencolar();
        }
    }
}
