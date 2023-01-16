package es.drodriguez.com.reproductormusicadanielrodriguez.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleButton btnAnterior;

    @FXML
    private ToggleButton btnHome;

    @FXML
    private ToggleButton btnListas;

    @FXML
    private ToggleButton btnPlay;

    @FXML
    private ToggleButton btnSalir;

    @FXML
    private ToggleButton btnSiguiente;

    @FXML
    private Pane reproductorView;

    @FXML
    private Pane listasView;

    @FXML
    private Pane salirView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reproductorView.toFront();
        salirView.toBack();
        listasView.toBack();

        // Cambiar entre paneles
        btnHome.setOnAction(event -> {
            reproductorView.toFront();
            salirView.toBack();
            listasView.toBack();
        });
        btnListas.setOnAction(event -> {
            listasView.toFront();
            salirView.toBack();
            reproductorView.toBack();
        });
        btnSalir.setOnAction(event -> {
            salirView.toFront();
            reproductorView.toBack();
            listasView.toBack();
        });

        // Cambiar idioma


    }
}