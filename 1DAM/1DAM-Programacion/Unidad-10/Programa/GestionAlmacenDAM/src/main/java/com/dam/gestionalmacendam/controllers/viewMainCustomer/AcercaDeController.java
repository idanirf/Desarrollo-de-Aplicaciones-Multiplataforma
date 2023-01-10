package com.dam.gestionalmacendam.controllers.viewMainCustomer;

import com.dam.gestionalmacendam.utils.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AcercaDeController {
    private Stage dialogStage;
    @FXML
    private ImageView logoGadam;
    @FXML
    private Label titulo;
    @FXML
    private Label version;
    @FXML
    private Label licencia;
    @FXML
    private Label integrantes;
    @FXML
    private Label integrante1;
    @FXML
    private Label integrante2;
    @FXML
    private Label integrante3;
    @FXML
    private Label integrante4;
    @FXML
    private Label integrante5;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        titulo.setText(Properties.APLICACION_NAME);
        version.setText("Versión: " + Properties.APLICACION_VERSION);
        licencia.setText(Properties.APLICACION_LICENCIA);
    }

    @FXML
    private void salirAction() {
        dialogStage.close();
    }
}
