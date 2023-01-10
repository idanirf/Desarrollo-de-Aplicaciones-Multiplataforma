import com.diogonunes.jcolor.Attribute;
import models.Parking;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class App {
    public App(){

    }
    public static void main(String[] args) {
        System.out.println(colorize("Iniciando Parking...", Attribute.BLUE_BACK(), BLACK_TEXT()));
        Scanner sc = new Scanner(System.in);
        Parking p = new Parking();
        boolean exit = false;
        int seleccion = 0;

        while (!exit){
            boolean ok = false;
            System.out.println(colorize("MENÚ DE OPCIONES PARKING-DAM:",GREEN_BACK(),BLACK_TEXT()));
            System.out.println("1. Estacionar Vehículo");
            System.out.println("2. Retirada de Vehiculo");
            System.out.println("3. Mostrar estado del Parking");
            System.out.println("4. Listado de Vehículos, con mayor tiempo de estacionamiento a menor");
            System.out.println("5. Exit");



            do {
                try {
                    System.out.println("Introduce la opción:");
                    seleccion = sc.nextInt();
                    ok = true;
                } catch (Exception e) {
                    System.out.println(colorize("El dato introducido no es válido, intentalo de nuevo",Attribute.RED_TEXT()));
                    sc.next();
                }
            } while (!ok);

            switch (seleccion){
                case 1:
                    p.crearCoche();
                    p.imprimirParking();
                    break;
                case 2:
                    p.borrarCoche();
                    p.imprimirParking();
                    break;
                case 3:
                    p.imprimirParking();
                    break;
                case 4:
                    p.OrdenarFecha();
                    break;
                case 5:
                    exit = true;
                    break;
            }
        }
    }
}
