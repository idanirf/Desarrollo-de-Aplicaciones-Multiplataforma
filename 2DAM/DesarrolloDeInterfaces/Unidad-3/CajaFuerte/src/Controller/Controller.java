package src.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Controller {
    private final JLabel numeroIntroducido;
    private final JLabel numeroResultado;
    private final JTextArea resultadosAnteriores;
    String contrasena = generarCodigoAleatorio();
    int intentos = 0;


    public Controller(JLabel numeroIntroducido, JLabel numeroResultado, JTextArea resultadosAnteriores) {
        this.numeroIntroducido = numeroIntroducido;
        this.numeroResultado = numeroResultado;
        this.resultadosAnteriores = resultadosAnteriores;
    }

    public void menejaBoton(ActionEvent e) {
        if (numeroIntroducido.getText().length() < 4) {
            numeroIntroducido.setText(numeroIntroducido.getText() + e.getActionCommand());
        } else {
            numeroIntroducido.setText("Te has pasado con la longitud del número");
        }
    }

    public void borrar(ActionEvent e) {
        numeroIntroducido.setText("");
    }

    public String generarCodigoAleatorio() {
        String codigo = "";
        for (int i = 0; i < 4; i++) {
            codigo += (int) (Math.random() * 10);
        }
        System.out.println(codigo);
        return codigo;
    }

    public void numeroIntentos(ActionEvent e) {
        if (numeroIntroducido.getText().equals(contrasena)) {
            JOptionPane.showMessageDialog(null, "La contraseña es correcta");

        } else {
            JOptionPane.showMessageDialog(null, "La contraseña es incorrecta");
            intentos++;
        }
        numeroResultado.setText(numeroIntroducido.getText());
        resultadosAnteriores.setText(resultadosAnteriores.getText() + numeroResultado.getText() + "\n");
        if (intentos == 10) {
            JOptionPane.showMessageDialog(null, "Has agotado los intentos");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Te quedan " + (10 - intentos) + " intentos");
        }
    }
}



