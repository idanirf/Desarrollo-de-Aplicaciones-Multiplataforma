package es.drodriguez.com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Cliente");

        Socket server = new Socket("localhost", 7888);
        System.out.println("Conectado al servidor");

        //Salida del paquete
        Paquete paqueteSalida = new Paquete("La Dele", "Rodriguez", 21);
        ObjectOutputStream flujoSalida = new ObjectOutputStream(server.getOutputStream());
        flujoSalida.writeObject(paqueteSalida);
        System.out.println("Enviado al servidor " + paqueteSalida.toString() + " ");

        //Entrada del paquete
        ObjectInputStream flujoEntrada = new ObjectInputStream(server.getInputStream());
        Paquete paquete = (Paquete) flujoEntrada.readObject();
        paquete.visible();
        System.out.println("Recibido del servidor " + paquete.toString() + " ");
    }
}
