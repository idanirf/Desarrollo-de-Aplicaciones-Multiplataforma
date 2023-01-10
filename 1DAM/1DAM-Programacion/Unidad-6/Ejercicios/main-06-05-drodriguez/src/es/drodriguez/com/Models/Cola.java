package es.drodriguez.com.Models;

import java.util.*;

public class Cola<T> extends ArrayDeque implements ICola<T> {


    @Override
    public void encolar(T item) {
        this.addLast(item);
    }

    @Override
    public T desencolar() {
        return (T) this.pollFirst();
    }

    @Override
    public boolean isVacia() {
        return this.isEmpty();
    }

    @Override
    public int tamaño() {
        return this.size();
    }

    @Override
    public T primero() {
        return (T) this.peekFirst();
    }

    @Override
    public String toString() {
        return "Cola{ " + super.toString() + " }";
    }
}
