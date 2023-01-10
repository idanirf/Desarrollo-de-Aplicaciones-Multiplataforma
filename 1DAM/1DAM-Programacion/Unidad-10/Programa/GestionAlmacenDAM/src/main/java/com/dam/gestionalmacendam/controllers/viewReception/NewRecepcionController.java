package com.dam.gestionalmacendam.controllers.viewReception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;
import com.dam.gestionalmacendam.utils.AlertInfo;
import com.dam.gestionalmacendam.utils.Patterns;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class NewRecepcionController {

    LineReceptionRepository repo = LineReceptionRepository.getInstance(DataBaseManager.getInstance());
    ArticleRepository repository = ArticleRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    TextField unitPrice;
    @FXML
    TextField load;
    @FXML
    TextField article;
    @FXML
    TextField carrier;
    @FXML
    TextField suplierName;

    private Stage dialogStage;
    private boolean aceptarClicked = false;
    private Reception reception;

    private LineReception lineReception;

    public void setReception(Reception recepcion, LineReception lineReception) {
        this.reception = recepcion;
        this.lineReception = lineReception;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public boolean isAceptarClicked() {
        return aceptarClicked;
    }

    public void onAcept() {
        if (isDataOk()) {
            var cost= Integer.parseInt(load.getText()) *Double.parseDouble(unitPrice.getText());


            reception.setCost(new SimpleDoubleProperty(cost));
            reception.setSupplierName(new SimpleStringProperty(suplierName.getText()));
            reception.setCarrier(new SimpleStringProperty(carrier.getText()));
            lineReception = new LineReception(article.getText(), Integer.parseInt(load.getText()), Double.parseDouble(unitPrice.getText()), reception.getRIC());
            try {
                var aux= repository.findByName(article.getText());
                aux.setStock(new SimpleIntegerProperty(aux.getStock().get() + Integer.parseInt(load.getText())));
                repository.update(aux.getPIC(),aux);
                repo.save(lineReception);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            aceptarClicked = true;
            dialogStage.close();
        }
    }


    private boolean isDataOk() {
        String errorMessage = "";

        if (suplierName.getText() == null || suplierName.getText().isBlank()) {
            errorMessage += "El campo suplier no puede estar en blanco\n";
        }
        if (carrier.getText() == null || carrier.getText().isBlank()) {
            errorMessage += "El campo carrier no puede estar en blanco\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = AlertInfo.getAlertErrorDetails("Error en datos", "Datos del producto", "Existen problemas al intentar Aceptar.", errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}
