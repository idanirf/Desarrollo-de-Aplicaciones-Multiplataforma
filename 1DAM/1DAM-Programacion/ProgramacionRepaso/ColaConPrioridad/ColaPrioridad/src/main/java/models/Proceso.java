package models;

import java.util.Objects;

public class Proceso {
    private int id;
    private int prioridad;
    public Proceso() {
        this.id = idAleatorio();
        this.prioridad = prioridadAleatorio();
    }

    //Creamos id's aleatorios de 1 a 100
    public static int idAleatorio(){
        return (int)Math.floor(Math.random()*((1-(1+1))+(9)));
    }
    //La prioridad del proceso puede estar entre 1 y 9
    public static int prioridadAleatorio(){
        return (int)Math.floor(Math.random()*((1-(1+1))+(9)));
    }

    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proceso)) return false;
        Proceso proceso = (Proceso) o;
        return getId() == proceso.getId() && getPrioridad() == proceso.getPrioridad();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrioridad());
    }

    @Override
    public String toString() {
        return "Proceso: " +
                "ID " + id +
                " P: " + prioridad;
    }
}
