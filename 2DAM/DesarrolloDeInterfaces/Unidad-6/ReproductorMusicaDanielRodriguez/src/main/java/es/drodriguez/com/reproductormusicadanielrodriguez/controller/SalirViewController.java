package es.drodriguez.com.reproductormusicadanielrodriguez.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class SalirViewController implements Initializable {
    @FXML
    private Button btnSalirCancelar;

    @FXML
    private Button btnSalirAceptar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSalirCancelar.setOnAction(event -> {
            System.out.println("Cancelar no se cierra la app");
        });

        btnSalirAceptar.setOnAction(event -> {
            //Salir de la aplicacion
            System.exit(0);
        });

    }
}
