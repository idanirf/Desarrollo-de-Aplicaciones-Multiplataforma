package Models;

import java.util.Comparator;
import java.util.Objects;

public class Personas implements Comparator<Personas> {
    private String name;
    private String Apellido1;
    private String Apellido2;
    private int edad;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApellido1(String Apellido1){this.Apellido1 = Apellido1;}

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido2(String Apellido2){this.Apellido2 = Apellido2;}

    public String getApellido2() {
        return Apellido2;
    }

    public void setEdad(int edad){this.edad = edad;}


    public int getEdad() {
        return edad;
    }

    @Override
    public int compare(Personas o1, Personas o2) {
        return o1.getName().compareTo(o2.getName()) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personas personas = (Personas) o;
        return edad == personas.edad && Objects.equals(name, personas.name) && Objects.equals(Apellido1, personas.Apellido1) && Objects.equals(Apellido2, personas.Apellido2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Apellido1, Apellido2, edad);
    }
}
