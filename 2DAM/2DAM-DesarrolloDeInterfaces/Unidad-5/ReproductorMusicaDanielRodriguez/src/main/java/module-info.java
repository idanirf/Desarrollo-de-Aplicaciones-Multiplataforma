module es.drodriguez.com.reproductormusicadanielrodriguez {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens es.drodriguez.com.reproductormusicadanielrodriguez to javafx.fxml;
    exports es.drodriguez.com.reproductormusicadanielrodriguez;
    exports es.drodriguez.com.reproductormusicadanielrodriguez.controller;
    opens es.drodriguez.com.reproductormusicadanielrodriguez.controller to javafx.fxml;
}