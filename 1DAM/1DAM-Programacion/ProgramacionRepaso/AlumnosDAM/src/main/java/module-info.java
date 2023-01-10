module com.drodriguez.es.alumnosdam {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires com.google.gson;


    opens com.drodriguez.es.alumnosdam to javafx.fxml;
    exports com.drodriguez.es.alumnosdam;

    opens com.drodriguez.es.alumnosdam.controllers to javafx.fxml;
    exports com.drodriguez.es.alumnosdam.controllers;

    opens com.drodriguez.es.alumnosdam.models to com.google.gson;

    opens com.drodriguez.es.alumnosdam.dto to com.google.gson;
}
