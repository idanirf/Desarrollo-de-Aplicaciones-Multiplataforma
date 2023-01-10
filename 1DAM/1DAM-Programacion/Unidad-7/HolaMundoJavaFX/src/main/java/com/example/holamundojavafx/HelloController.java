package com.example.holamundojavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    public TextField casNombre;
    @FXML
    private TextField casEdad;
    @FXML
    private Label welcomeText;
    @FXML
    private Button botonEnviar;
    @FXML
    protected void onBotonEntrar() {
        String name = casNombre.getText();
        String edad = casEdad.getText();
        welcomeText.setText("Hola " + name + " tu edad es de: " + edad) ;
    }
}