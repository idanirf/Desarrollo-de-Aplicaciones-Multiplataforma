package es.drodriguez.com.reproductormusicadanielrodriguez.controller;

import es.drodriguez.com.reproductormusicadanielrodriguez.rest.MusicFXApiClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ReproductorViewController implements Initializable {
    @FXML
    public ResourceBundle resources;
    @FXML
    public URL location;
    @FXML
    public Slider barraProgreso;
    @FXML
    public Label nombreCancionLabel;
    @FXML
    public Label albumLabel;
    @FXML
    public Label autorLabel;
    @FXML
    public ListasViewController listasViewController;
    @FXML
    public ReproductorViewController reproductorViewController;
    @FXML
    public ViewController viewController;
    @FXML
    public SalirViewController salirViewController;
    @FXML
    public ImageView imagenField;
    public ControlSong controlSong;
    public MusicFXApiClient musicFXApiClient;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}

