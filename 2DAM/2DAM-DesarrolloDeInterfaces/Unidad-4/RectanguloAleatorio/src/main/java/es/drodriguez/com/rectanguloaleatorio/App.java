package es.drodriguez.com.rectanguloaleatorio;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VistaRectangulo pane = new VistaRectangulo();
        Scene scene = new Scene(pane, 640, 480);
        stage.setTitle("Rectángulo Aleatorio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}