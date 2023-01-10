import Cola.Cola;
import comparator.ProcesoComparator;
import models.Proceso;

import java.util.ArrayList;

public class Controller {
    public void controller() throws InterruptedException {
        Cola<Proceso> colaProceso = new Cola<Proceso>();
        //Indicamos el número máximo de procesos que se pueden encolar
        int NUMERO_PROCESOS = 10;

        //todo revisar como comprobar que ese id de proceso no se repita
        for (int i = 0; i < NUMERO_PROCESOS; i++) {
            if (new Proceso().getId() == colaProceso.hashCode()) {
                System.out.println("Se va a encolar un nuevo proceso, ciclo:" +i);
                //Se encola un nuevo proceso
                colaProceso.encolar(new Proceso());
            } else {
                System.out.println("Se va a encolar un nuevo proceso, ciclo:" +i);
                //Se encola un nuevo proceso
                colaProceso.encolar(new Proceso());
            }
            //Pongo una pausa para ver como van entrando los procesos a la cola, según la prioridad
            Thread.sleep(1000);
            //Creamos un array List para almacenar los procesos ordenados por prioridad, el nuevo arrayList proviene de
            // colaProceso.
            ArrayList<Proceso> colaProcesoPrioridad = new ArrayList<>(colaProceso);
            //Implementamos el sort para ordenar los procesos por prioridad
            colaProcesoPrioridad.sort(new ProcesoComparator());
            //Imprimimos la cola de procesos ordenados por prioridad
            for(Proceso a :colaProcesoPrioridad) {
                System.out.println(a);
            }
        }
        //Número de procesos iniciales en la cola
        System.out.println("Número de procesos encolados inicial: " + colaProceso.size());
        //Número de procesos encolados
        System.out.println();
        System.out.println("Número de procesos encolados final: " + colaProceso.size());
    }
}
