package Models;

import es.drodriguez.com.CreatorRandom;

public class Alumnas extends Personas {
    //Constructor
    public Alumnas(){
        setName(CreatorRandom.nameGrilsAleatorios());
        setApellido1(CreatorRandom.personasApellidos());
        setApellido2(CreatorRandom.personasApellidos());
        setEdad(CreatorRandom.edadAleatorio());
    }


    @Override
    public String toString() {
        return "Nombre " + getName() + " Apellidos " + getApellido1() + " " + getApellido2() + " edad de: " + getEdad();
    }
}
