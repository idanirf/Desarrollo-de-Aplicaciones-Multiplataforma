module es.drodriguez.com.vistasanidadasjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.drodriguez.com.vistasanidadasjavafx to javafx.fxml;
    exports es.drodriguez.com.vistasanidadasjavafx;
}