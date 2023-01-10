package Cola;


import models.Proceso;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Cola<T> extends ArrayDeque<T> implements ICola<T> {
    //Método para encolar elementos de la cola
    @Override
    public void encolar(T entity) {
        this.addLast(entity);
    }

    //Método para desencolar elementos de la cola
    @Override
    public T desencolar() {
        return this.pollFirst();
    }

    //Longitud que tiene la cola
    @Override
    public int longitud(T entity) {
        return this.size();
    }


}
