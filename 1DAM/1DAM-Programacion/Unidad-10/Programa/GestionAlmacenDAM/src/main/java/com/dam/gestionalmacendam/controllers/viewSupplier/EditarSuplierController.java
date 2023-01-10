package com.dam.gestionalmacendam.controllers.viewSupplier;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Optional;


public class EditarSuplierController {
    SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());
    private Supplier supplier;
    @FXML
    private TextField areaD;


    @FXML
    private TextField areaTelefono;

    @FXML
    private TextField areaemail;

    @FXML
    private TextField areanombre;

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        initData();
    }

    void initData() {
        areaD.setText(supplier.getDirection());
        areaTelefono.setText(supplier.getTelephoneNumber());
        areanombre.setText(supplier.getNameSupplier());
        areaemail.setText(supplier.getEmail());
    }

    @FXML
    void onAceptarAction(ActionEvent event) {
        boolean datosOk = comprobarDatos();
        if (datosOk) {
            try {
                guardarSuplier();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmacion");
                alert.setContentText("El suplier se ha modificado");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    alert.close();
                } else {
                    alert.close();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmacion");
                alert.setContentText("El suplier NO se ha modificado");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    alert.close();
                } else {
                    alert.close();
                }


                System.out.println("no se ha podido aptualizar");
            }

        }
    }

    private void guardarSuplier() throws SQLException {
        supplier.setTelephoneNumber(areaTelefono.getText());
        supplier.setEmail(areaemail.getText());
        supplier.setNameSupplier(areanombre.getText());
        supplier.setDirection(areaD.getText());
        repository.update(supplier.getSIC(), supplier);
    }

    private boolean comprobarDatos() {
        String errorMessage = "";

        if (areanombre.getText() == null || areanombre.getText().isBlank()) {
            errorMessage += "Debes introducir nombre correcto. ";
        }
        if (areaD.getText() == null || areaD.getText().isBlank()) {
            errorMessage += "Debes introducir la dirección correcta. ";
        }
        if (areaTelefono.getText() == null || areaTelefono.getText().isBlank() || !Patterns.patterPhone(areaTelefono.getText())) {
            errorMessage += "Debes introducir un teléfono correcto. ";

        }
        if (areaemail.getText() == null || areaemail.getText().isBlank() || !Patterns.patternEmail(areaemail.getText())) {
            errorMessage += "Debes introducir email correcto. ";
        }

        if (errorMessage.length() != 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los datos");
            alert.setHeaderText("Datos introducidos incorrectos");
            alert.setContentText("Hay datos incorrectos");
            alert.setContentText(errorMessage);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }

        }
        return errorMessage.length() == 0;
    }
}
