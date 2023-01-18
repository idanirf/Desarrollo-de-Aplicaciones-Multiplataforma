module es.drodriguez.com.visorproductosdanielrodriguez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    requires retrofit2;
    requires okhttp3;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;


    opens es.drodriguez.com.visorproductosdanielrodriguez to javafx.fxml;
    exports es.drodriguez.com.visorproductosdanielrodriguez;
    exports es.drodriguez.com.visorproductosdanielrodriguez.controller;
    opens es.drodriguez.com.visorproductosdanielrodriguez.controller to javafx.fxml;
    exports es.drodriguez.com.visorproductosdanielrodriguez.models;
    opens es.drodriguez.com.visorproductosdanielrodriguez.models to javafx.fxml, gson;

}