module es.drodriguez.com.reproductormusicadanielrodriguez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    requires retrofit2;
    requires okhttp3;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    requires javafx.media;

    requires org.kordamp.ikonli.javafx;

    opens es.drodriguez.com.reproductormusicadanielrodriguez to javafx.fxml;
    exports es.drodriguez.com.reproductormusicadanielrodriguez;
    exports es.drodriguez.com.reproductormusicadanielrodriguez.controller;
    opens es.drodriguez.com.reproductormusicadanielrodriguez.controller to javafx.fxml;
    exports es.drodriguez.com.reproductormusicadanielrodriguez.models;
    opens es.drodriguez.com.reproductormusicadanielrodriguez.models to javafx.fxml, gson;
    exports es.drodriguez.com.reproductormusicadanielrodriguez.rest;
    opens es.drodriguez.com.reproductormusicadanielrodriguez.rest to javafx.fxml, gson;
}