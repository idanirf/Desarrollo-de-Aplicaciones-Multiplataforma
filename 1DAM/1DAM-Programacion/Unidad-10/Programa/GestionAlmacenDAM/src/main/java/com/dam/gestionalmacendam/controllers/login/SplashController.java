package com.dam.gestionalmacendam.controllers.login;

import com.dam.gestionalmacendam.managers.SceneManager;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {
    private Stage stage;
    @FXML
    private ImageView fondoSplash;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition transition = new FadeTransition(Duration.millis(3000), fondoSplash);
        transition.setFromValue(1.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(event -> {
            SceneManager sceneManager = SceneManager.get();
            try {
                sceneManager.initLogin(stage);

            } catch (IOException e) {
                System.out.println(e);
            }
        });

    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }
}