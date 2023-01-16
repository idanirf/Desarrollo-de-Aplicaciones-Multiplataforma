package es.drodriguez.com;

import java.io.DataOutputStream;
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
            int numeroUno = paquete.getNumeroUno();
            int numeroDos = paquete.getNumeroDos();
            String operacion = paquete.getOperacion();
            String resultado = "";

            if (operacion.equals("suma")) {
               resultado= "El resultado de la suma es: " +  (numeroUno + numeroDos);
            } else if (operacion.equals("resta")) {
                resultado= "El resultado de la resta es: " +  (numeroUno - numeroDos);
            } else if (operacion.equals("multiplicacion")) {
                resultado= "El resultado de la multiplicacion es: " +  (numeroUno * numeroDos);
            } else if (operacion.equals("division")) {
                resultado= "El resultado de la division es: " +  (numeroUno / numeroDos);
            } else {
                resultado= "Operacion no valida";
            }


            System.out.println("Enviado por el cliente " + paquete.toString() + " ");
            //Creamos un objeto para la salida del paquete y enviarlo al cliente
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            flujoSalida.writeUTF(resultado);

            cliente.close();


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
