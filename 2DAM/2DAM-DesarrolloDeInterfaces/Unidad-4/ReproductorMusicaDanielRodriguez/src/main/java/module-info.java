module es.drodriguez.com.reproductormusica {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens es.drodriguez.com.reproductormusica to javafx.fxml;
    exports es.drodriguez.com.reproductormusica;
    exports es.drodriguez.com.reproductormusica.controller;
    opens es.drodriguez.com.reproductormusica.controller to javafx.fxml;
}