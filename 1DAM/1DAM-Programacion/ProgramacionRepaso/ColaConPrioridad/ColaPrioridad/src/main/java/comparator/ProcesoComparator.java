package comparator;

import models.Proceso;

import java.util.Comparator;

public class ProcesoComparator implements Comparator<Proceso> {
    @Override
    public int compare(Proceso o1, Proceso o2) {
        //Comparamos entre proceso 1 y proceso 2 según la prioridad, si el proceso 2 es menor que el proceso 1.
        //Sí lo ponemos al revés pues, tendríamos los procesos ordenados de menor prioridad a mayor prioridad.
        return o2.getPrioridad() - o1.getPrioridad();
    }
}
