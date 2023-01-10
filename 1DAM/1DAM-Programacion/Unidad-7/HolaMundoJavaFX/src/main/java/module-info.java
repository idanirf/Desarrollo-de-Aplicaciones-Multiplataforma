module com.example.holamundojavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.holamundojavafx to javafx.fxml;
    exports com.example.holamundojavafx;
}