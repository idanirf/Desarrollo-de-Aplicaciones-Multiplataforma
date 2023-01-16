module es.drodriguez.com.rectanguloaleatorio {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.drodriguez.com.rectanguloaleatorio to javafx.fxml;
    exports es.drodriguez.com.rectanguloaleatorio;
}