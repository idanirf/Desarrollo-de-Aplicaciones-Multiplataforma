package es.drodriguez.com.Models;

public interface ICola<T> {
    void encolar(T item);

    T desencolar();

    boolean isVacia();

    int tamaño();

    T primero();

}
