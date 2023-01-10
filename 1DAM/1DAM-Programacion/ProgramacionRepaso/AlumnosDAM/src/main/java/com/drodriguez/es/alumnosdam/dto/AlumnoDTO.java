package com.drodriguez.es.alumnosdam.dto;

import com.drodriguez.es.alumnosdam.models.Alumno;
import com.drodriguez.es.alumnosdam.models.PROMOCION;


import java.io.Serializable;
import java.time.LocalDate;

public class AlumnoDTO implements Serializable {
    private int ID;
    private String dni;
    private String nombreApellidos;
    private Double nota;
    private LocalDate fechaNacimiento;
    private PROMOCION promociona;

    public AlumnoDTO(Alumno alumno) {
        this.ID = alumno.getID();
        this.dni = alumno.getDni();
        this.nombreApellidos = alumno.getNombreApellidos();
        this.nota = alumno.getNota();
        this.fechaNacimiento = alumno.getFechaNacimiento();
        this.promociona = alumno.getPromociona();
    }

    public AlumnoDTO(int ID, String dni, String nombreApellidos, double nota, LocalDate fechaNacimiento, PROMOCION promociona) {
        this.ID = ID;
        this.dni = dni;
        this.nombreApellidos = nombreApellidos;
        this.nota = nota;
        this.fechaNacimiento = fechaNacimiento;
        this.promociona = promociona;
    }

    public Alumno fromDTO(){
        return new Alumno(ID, dni, nombreApellidos, nota, fechaNacimiento, promociona);
    }

    public String toFile(){
        return ID + "," + dni + "," + nombreApellidos + "," + nota + "," + fechaNacimiento + "," + promociona;
    }

}
