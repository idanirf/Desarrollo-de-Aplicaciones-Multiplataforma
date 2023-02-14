package es.drodriguez.com.reproductormusicadanielrodriguez.controller;

import es.drodriguez.com.reproductormusicadanielrodriguez.models.Song;
import es.drodriguez.com.reproductormusicadanielrodriguez.rest.MusicFXApiService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListasViewController implements Initializable {
    public MusicFXApiService service;
    @FXML
    public ListView<Song> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}


