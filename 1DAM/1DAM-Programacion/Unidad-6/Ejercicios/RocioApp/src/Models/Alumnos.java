package Models;

import es.drodriguez.com.CreatorRandom;

public class Alumnos extends Personas {
    //Constructor
    public Alumnos(){
        setName(CreatorRandom.nameBoysAleatorios());
        setApellido1(CreatorRandom.personasApellidos());
        setApellido2(CreatorRandom.personasApellidos());
        setEdad(CreatorRandom.edadAleatorio());
    }


    @Override
    public String toString() {
        return "Nombre " + getName() + " Apellidos " + getApellido1() + " " + getApellido2() + " edad de: " + getEdad();
    }
}
