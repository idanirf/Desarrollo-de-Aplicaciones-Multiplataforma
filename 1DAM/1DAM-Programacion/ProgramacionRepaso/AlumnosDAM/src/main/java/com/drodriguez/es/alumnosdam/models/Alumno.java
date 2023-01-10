package com.drodriguez.es.alumnosdam.models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Alumno {
    //Atributos
    private IntegerProperty ID;
    private StringProperty dni;
    private StringProperty nombreApellidos;
    private DoubleProperty nota;
    private ObjectProperty<LocalDate> fechaNacimiento;

    private ObjectProperty<PROMOCION>promociona;

    //Constructor

    public Alumno(int ID, String dni, String nombreApellidos, double nota, LocalDate fechaNacimiento, PROMOCION promociona) {
        this.ID = new SimpleIntegerProperty(ID);
        this.dni = new SimpleStringProperty(dni);
        this.nombreApellidos = new SimpleStringProperty(nombreApellidos);
        this.nota = new SimpleDoubleProperty(nota);
        this.fechaNacimiento = new SimpleObjectProperty<>(fechaNacimiento);
        this.promociona = new SimpleObjectProperty<>(promociona);
    }

    public Alumno(String dni, String nombreApellidos, LocalDate fechaNacimiento, double nota, PROMOCION promocion) {
        this.dni = new SimpleStringProperty(dni);
        this.nombreApellidos = new SimpleStringProperty(nombreApellidos);
        this.fechaNacimiento = new SimpleObjectProperty<>(fechaNacimiento);
        this.nota = new SimpleDoubleProperty(nota);
        this.promociona = new SimpleObjectProperty<>(promocion);
    }

    public Alumno(Alumno alumno) {
    }

    //Getter and Setter
    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getNombreApellidos() {
        return nombreApellidos.get();
    }

    public StringProperty nombreApellidosProperty() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos.set(nombreApellidos);
    }

    public double getNota() {
        return nota.get();
    }

    public DoubleProperty notaProperty() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota.set(nota);
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento.get();
    }

    public ObjectProperty<LocalDate> fechaNacimientoProperty() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento.set(fechaNacimiento);
    }

    public PROMOCION getPromociona() {
        return promociona.get();
    }

    public ObjectProperty<PROMOCION> promocionaProperty() {
        return promociona;
    }

    public void setPromociona(PROMOCION promociona) {
        this.promociona.set(promociona);
    }

    //toString
    @Override
    public String toString() {
        return "Alumno{" +
                "ID=" + ID +
                ", dni=" + dni +
                ", nombreApellidos=" + nombreApellidos +
                ", nota=" + nota +
                ", fechaNacimiento=" + fechaNacimiento +
                ", promociona=" + promociona +
                '}';
    }
}
