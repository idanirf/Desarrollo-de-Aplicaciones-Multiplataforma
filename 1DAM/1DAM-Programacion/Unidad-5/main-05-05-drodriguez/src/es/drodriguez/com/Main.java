package es.drodriguez.com;

import es.drodriguez.com.Models.Cuenta;
import es.drodriguez.com.Models.CuentaAhorro;
import es.drodriguez.com.Models.CuentaCorriente;
import es.drodriguez.com.Models.Transacciones;

public class Main {

    public static void main(String[] args) {
        System.out.println("Cuenta bancaria");
        Cuenta nuevo = new CuentaAhorro("48786454867867456", "drodriguez0",40000.00f, 12.00f,20.00f);
        System.out.println(nuevo);

        Cuenta corriente = new Transacciones("4564856447867", "drodriguez0",40000.00f, "Vacaciones", 20, 02, 2020,
                "Vacaciones verano", 1300.00f);
        System.out.println(corriente);

    }
}
