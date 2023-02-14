package es.drodriguez.com.reproductormusicadanielrodriguez.controller;

import es.drodriguez.com.reproductormusicadanielrodriguez.models.Song;
import es.drodriguez.com.reproductormusicadanielrodriguez.utils.Config;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ControlSong {
    public Media media;
    public MediaPlayer player;

    public void play(Song s) {
        if (media == null) {
            media = new Media(Config.API_URL + s.getFile());
            player = new MediaPlayer(media);
        }
        player.play();
    }

    public void resetSong() {
        if (media != null) {
            player.stop();
            media = null;
            player = null;
        }
    }

    public void stop() {
        if (player != null) {
            player.pause();
        }
    }

    public void adelantar() {
        if (player != null) {
            player.setRate(player.getRate() + 1);
        }
    }

    public void atrasar() {
        if (player != null) {
            player.setRate(player.getRate() - 1);
        }
    }

    public void siguiente() {
        if (player != null) {
            player.seek(player.getStopTime());
        }
    }

    public void anterior() {
        if (player != null) {
            player.seek(player.getStartTime());
        }
    }

    public void setSlider(Number newValue) {
        if (player != null) {
            player.seek(player.getStopTime().multiply(newValue.doubleValue() / 100));
            player.setRate(newValue.doubleValue());
            System.out.println(player.getRate());
            System.out.println(player.getStartTime());
            System.out.println(player.getStopTime());

        } else {
            System.out.println("No hay canción");
        }
    }
}
