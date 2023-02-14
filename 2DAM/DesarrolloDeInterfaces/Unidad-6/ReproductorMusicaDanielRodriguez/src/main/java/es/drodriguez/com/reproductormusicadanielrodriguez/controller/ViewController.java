package es.drodriguez.com.reproductormusicadanielrodriguez.controller;

import es.drodriguez.com.reproductormusicadanielrodriguez.models.Song;
import es.drodriguez.com.reproductormusicadanielrodriguez.rest.MusicFXApiClient;
import es.drodriguez.com.reproductormusicadanielrodriguez.utils.Config;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    private static Integer actualSong = 0;
    @FXML
    public ToggleButton btnAnterior;
    @FXML
    public ToggleButton btnHome;
    @FXML
    public ToggleButton btnListas;
    @FXML
    public ToggleButton btnPlay;
    @FXML
    public ToggleButton btnSalir;
    @FXML
    public ToggleButton btnSiguiente;
    @FXML
    public Pane reproductorView;
    @FXML
    public Pane listasView;
    @FXML
    public ListasViewController listasViewController;
    @FXML
    public ReproductorViewController reproductorViewController;
    @FXML
    public ControlSong controlSong;
    MusicFXApiClient apiStoreClient = new MusicFXApiClient();
    ArrayList<Song> listSongsApp = new ArrayList<>();
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Pane salirView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controlSong = new ControlSong();
        reproductorView.toFront();
        salirView.toBack();
        listasView.toBack();
        listSongsApp();

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

        listasViewController.listView.setOnMouseClicked(e -> {
            Object songSelect = listasViewController.listView.getFocusModel().getFocusedItem();
            Song song = (Song) songSelect;
            if (song != null) {
                reproductorView.toFront();
                printSong(song);
                Image image = new Image(Config.API_URL + song.getAlbum().getPicture());
                reproductorViewController.imagenField.setImage(image);
                controlSong.resetSong();
                controlSong.play(song);

                btnPlay.setOnAction(event -> {
                    if (btnPlay.isSelected()) {
                        controlSong.play(song);
                    } else {
                        controlSong.stop();
                    }
                });

            }
        });

        btnSiguiente.setOnAction(event -> {
            controlSong.resetSong();
            actualSong = actualSong + 1;
            if (actualSong == listSongsApp.size()) {
                actualSong = 0;
            } else {

            }
            printSong(listSongsApp.get(actualSong));
            controlSong.play(listSongsApp.get(actualSong));


        });
        btnAnterior.setOnAction(event -> {
            controlSong.resetSong();
            actualSong = actualSong - 1;
            if (actualSong == -1) {
                actualSong = listSongsApp.size() - 1;
            } else {
            }
            printSong(listSongsApp.get(actualSong));
            controlSong.play(listSongsApp.get(actualSong));
        });
    }

    private void listSongsApp() {
        Runnable task = () -> {
            try {
                List<Song> list = MusicFXApiClient.getDataSongs();
                for (int i = 0; i < list.size(); i++) {
                    listSongsApp.add(list.get(i));
                }
                if (listSongsApp.size() != 0) {
                    printSong(listSongsApp.get(0));
                    initzializePanes();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
    }

    private void initzializePanes() {
        for (int i = 0; i < listSongsApp.size(); i++) {
            listasViewController.listView.getItems().add(listSongsApp.get(i));
        }
    }

    private void printSong(Song s) {
        reproductorViewController.nombreCancionLabel.setText(s.getAlbum().getTitle());
        reproductorViewController.autorLabel.setText(s.getPublisher());
        reproductorViewController.albumLabel.setText(s.getAlbum().getTitle());
        Image image = new Image(Config.API_URL + s.getAlbum().getPicture());
        reproductorViewController.imagenField.setImage(image);
    }
}









