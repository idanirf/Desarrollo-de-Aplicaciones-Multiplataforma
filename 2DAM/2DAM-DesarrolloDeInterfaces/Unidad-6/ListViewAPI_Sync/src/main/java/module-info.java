module es.drodriguez.com.listviewapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.net.http;
    requires org.json;

    opens es.drodriguez.com.listviewapi to javafx.fxml;
    exports es.drodriguez.com.listviewapi;
    exports es.drodriguez.com.listviewapi.controller;
    opens es.drodriguez.com.listviewapi.controller to javafx.fxml;
}