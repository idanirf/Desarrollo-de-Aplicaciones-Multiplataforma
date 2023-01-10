module es.drodriguez.com.calculadorafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens es.drodriguez.com.calculadorafx to javafx.fxml;
    exports es.drodriguez.com.calculadorafx;
}