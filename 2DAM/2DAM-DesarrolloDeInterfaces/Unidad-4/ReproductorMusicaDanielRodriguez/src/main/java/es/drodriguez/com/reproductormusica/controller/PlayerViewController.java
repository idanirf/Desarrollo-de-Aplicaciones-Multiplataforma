package es.drodriguez.com.reproductormusica.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PlayerViewController implements Initializable {

    @FXML
    private Slider barraProgreso;

    @FXML
    private Button anteriorBoton;

    @FXML
    private Button siguienteBoton;

    @FXML
    private Button playBoton;

    @FXML
    private Label nombreCancion;

    @FXML
    private Label albumLabel;

    @FXML
    private Label grupoLabel;

    @FXML
    private Label cancionLabel;

    @FXML
    private ImageView imageView;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // Creamos el media player
        Media media = new Media (getClass().getResource(("/media/cancion.mp3")).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        media.getMetadata().addListener((MapChangeListener.Change<? extends String, ?> change)  -> {
            switch (change.getKey().toString()) {
                case "album" -> albumLabel.setText(change.getValueAdded().toString());
                case "artist"-> grupoLabel.setText(change.getValueAdded().toString());
                case "title"-> cancionLabel.setText(change.getValueAdded().toString());
                case "image"-> imageView.setImage((Image) change.getValueAdded());
            }
        });


        // Inicializar el botón de play
        playBoton.setOnAction(event -> {
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
                return;
            }
            if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED) {
                mediaPlayer.play();
            } else {
                mediaPlayer.pause();
            }
        });

        // Inicializar el botón de siguiente
        siguienteBoton.setOnAction(event -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().add(mediaPlayer.getTotalDuration().divide(10)));
        });

        // Inicializar el botón de anterior
        anteriorBoton.setOnAction(event -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(mediaPlayer.getTotalDuration().divide(10)));
        });

        // Inicializar la barra de progreso
        barraProgreso.setOnMousePressed(event -> {
            mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(barraProgreso.getValue() / 100));
        });

        barraProgreso.setOnMouseDragged(event -> {
            mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(barraProgreso.getValue() / 100));
        });

        // Inicializar el listener de la barra de progreso
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            barraProgreso.setValue(newValue.toMillis() / mediaPlayer.getTotalDuration().toMillis() * 100);
        });
    }
}
