import com.diogonunes.jcolor.Attribute;
import models.Butaca;
import models.SalaCine;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class App {
    public static void main(String[] args) {
        System.out.println("CINE - SpiderDAM");
        SalaCine sala = new SalaCine();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int seleccion;

        while (!salir){
            System.out.println("1. Comprobar estado de sala.");
            System.out.println("2. Comprar una entrada.");
            System.out.println("3. Reserva de una butaca.");
            System.out.println("4. Confirmar reserva.");
            System.out.println("5. Anular reserva.");
            System.out.println("6. Anular compra.");
            System.out.println("7. Mostrar recaudación obtenida.");
            System.out.println("8. Mostrar las estadísticas del cine.");
            System.out.println(colorize("9. Tickets (OPCIÓN DESHABILITADA)", Attribute.RED_TEXT()));
            System.out.println("10. Salir y cerrar programa.");
            System.out.println();
            System.out.print("Introduce la opción deseada: ");
            boolean ok = false;
            do{
                seleccion = sc.nextInt();
                ok = true;

            }while (!ok);

            switch(seleccion){
                case 1:
                    System.out.println("Comprobar estado de la sala:");
                    sala.leerSalaCompleta();
                    break;

                case 2:
                    System.out.println("Compra de entradas:");
                    sala.comprarEntradas();
                    break;

                case 3:
                    System.out.println("Reserva de una butaca:");
                    sala.reservarEntradas();
                    break;

                case 4:
                    System.out.println("Confirmar reserva:");
                    sala.confirmarReserva();
                    break;

                case 5:
                    System.out.println("Anular reserva");
                    sala.anularReserva();
                    break;

                case 6:
                    System.out.println("Anular compra:");
                    sala.anularEntradas();
                    break;

                case 7:
                    System.out.println("Mostrar contabilidad obtenida:");
                    sala.estadisticasContabilidad();
                    break;

                case 8:
                    System.out.println("Mostrar las estadísticas del cine:");
                    sala.estadisticasOcupacion();
                    break;

                case 9:
                    System.out.println("Tickets");
                    break;

                case 10:
                    System.out.println("Salir y cerrar");
                    salir = true;
                    break;
            }
        }
    }
}
