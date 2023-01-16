package es.drodriguez.com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Hilo extends Thread {
    private int numeroCliente;
    private Socket cliente;

    //Constructor
    public Hilo(int numeroCliente, Socket cliente) {
        this.numeroCliente = numeroCliente;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        ObjectInputStream flujoEntrada = null;
        try {
            System.out.println("Cliente " + numeroCliente + " conectado");
            flujoEntrada = new ObjectInputStream(cliente.getInputStream());
            Paquete paquete = (Paquete) flujoEntrada.readObject();
            paquete.visible();
            System.out.println("Enviado por el cliente " + paquete.toString() + " ");
            //Creamos un objeto para la salida del paquete y enviarlo al cliente
            Paquete paqueteSalida = new Paquete();
            ObjectOutputStream flujoSalida = new ObjectOutputStream(cliente.getOutputStream());
            flujoSalida.writeObject(paqueteSalida);
            System.out.println("Enviado al cliente " + paqueteSalida.toString() + " ");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                flujoEntrada.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
