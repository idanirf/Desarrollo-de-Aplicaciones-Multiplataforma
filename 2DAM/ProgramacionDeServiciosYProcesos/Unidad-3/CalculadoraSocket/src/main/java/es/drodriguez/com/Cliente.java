package es.drodriguez.com;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Cliente");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Socket server = new Socket("localhost", 7888);
            System.out.println("Conectado al servidor");
            System.out.println("Introduce número 1:");
            int numeroUno = scanner.nextInt();
            System.out.println("Introduce número 2:");
            int numeroDos = scanner.nextInt();
            System.out.println("Introduce la operación:");
            String operacion = scanner.next();
            //Salida del paquete
            Paquete paqueteSalida = new Paquete(numeroUno, numeroDos, operacion);
            ObjectOutputStream flujoSalida = new ObjectOutputStream(server.getOutputStream());
            flujoSalida.writeObject(paqueteSalida);
            System.out.println("Enviado al servidor " + paqueteSalida + " ");

            //Entrada del paquete
            DataInputStream flujoEntrada = new DataInputStream(server.getInputStream());
            String  resultadoLeido = flujoEntrada.readUTF();
            System.out.println("Resultado: " + resultadoLeido);

        }
    }
}
