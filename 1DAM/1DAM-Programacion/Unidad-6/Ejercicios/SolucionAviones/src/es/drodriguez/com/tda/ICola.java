package es.drodriguez.com.tda;

public interface ICola<T> {
    void encolar(T elem);

    T desencolar();

    boolean esVacia();

    int longitud();

    T primero();
}
