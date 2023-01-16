package es.drodriguez.com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("SERVER");

        ServerSocket server;
        Socket cliente;
        int numeroCliente = 0;
        int PUERTO = 7888;
        boolean exit = false;

        System.out.println("Esperando cliente...");
        try {
            server = new ServerSocket(PUERTO);
            while (!exit) {
                cliente = server.accept();
                numeroCliente++;
                Hilo hilo = new Hilo(numeroCliente, cliente);
                hilo.start();
            }
            System.out.println("Servidor cerrado");
            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}