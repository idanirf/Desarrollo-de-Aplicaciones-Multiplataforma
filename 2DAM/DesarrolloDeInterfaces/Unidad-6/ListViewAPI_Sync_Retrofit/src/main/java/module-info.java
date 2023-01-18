module es.drodriguez.com.listviewapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;
    requires retrofit2;
    requires okhttp3;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;

    opens es.drodriguez.com.listviewapi to javafx.fxml;
    exports es.drodriguez.com.listviewapi;
    exports es.drodriguez.com.listviewapi.controller;
    opens es.drodriguez.com.listviewapi.controller to javafx.fxml;
    exports es.drodriguez.com.listviewapi.models;
    opens es.drodriguez.com.listviewapi.models to javafx.fxml, gson;
}