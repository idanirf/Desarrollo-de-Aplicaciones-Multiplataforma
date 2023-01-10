package es.drodriguez.com;

import es.drodriguez.com.Models.AComision;
import es.drodriguez.com.Models.Empleados;
import es.drodriguez.com.Models.Fijos;
import es.drodriguez.com.Models.PorHoras;

public class Main {

    public static void main(String[] args) {
        System.out.println("Cargando empleados ...");
        System.out.println("⌛⌛⌛⌛⌛");
        System.out.println("⌛⌛⌛⌛⌛");
        System.out.println();
        Empleados fijos = new Fijos("Daniel", "Rodríguez",2400.00f);
        System.out.println(fijos);
        Empleados PorHoras = new PorHoras("Daniel", "Rodríguez", 20.00f, 18.00f);
        System.out.println(PorHoras);
        Empleados AComision = new AComision("Daniel", "Rodríguez", 40.00f, 80.00f);
        System.out.println(AComision);
    }
}
