package es.drodriguez.com.reproductormusicadanielrodriguez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Locale locale = new Locale("en", "UK");
        ResourceBundle bundle = ResourceBundle.getBundle("es.drodriguez.com.reproductormusicadanielrodriguez.media.strings_" + locale);
        Parent root = FXMLLoader.load(getClass().getResource("view/hello-view.fxml"), bundle);
        Scene scene = new Scene(root, 900, 500);
        //Internacionalizar
        stage.setTitle("@idanirf-Music");
        stage.setScene(scene);
        stage.show();
    }
}