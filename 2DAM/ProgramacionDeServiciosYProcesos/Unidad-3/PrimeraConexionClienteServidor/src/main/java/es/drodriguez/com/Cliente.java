package es.drodriguez.com;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("CLIENTE");
        InetAddress direccion;
        Socket socket;
        int numeroCliente = 0;
        int PUERTO = 5000;

        System.out.println("Conectando con el servidor...");

        try{
            direccion = InetAddress.getLocalHost();
            socket = new Socket(direccion, PUERTO);
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            System.out.println("El servidor dice: " + entrada.readUTF());
            entrada.close();
            System.out.println("Se ha desconectado del servidor");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
