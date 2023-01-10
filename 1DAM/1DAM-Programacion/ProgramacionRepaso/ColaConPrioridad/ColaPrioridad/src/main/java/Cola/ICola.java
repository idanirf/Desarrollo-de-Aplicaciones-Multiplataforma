package Cola;

public interface ICola<T> {
    //Encolar elementos en la cola
    void encolar(T entity);
    //Desencolar elementos de la cola
    T desencolar();
    //Tamaño de la cola Prioridad
    int longitud (T entity);

}
