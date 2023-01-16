package es.drodriguez.com;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        System.out.println("SERVIDOR");
        ServerSocket servidor;
        Socket socket;
        int contadorCliente = 0;
        int PUERTO = 5000;
        System.out.println("Esperando clientes...");

        try {
            servidor = new ServerSocket(PUERTO);
            do {
                contadorCliente++;
                socket = servidor.accept();
                System.out.println("Cliente conectado: " + contadorCliente);
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeUTF("Hola cliente " + contadorCliente);
                salida.close();
                System.out.println("Se ha desconectado el cliente " + contadorCliente);

            }while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}