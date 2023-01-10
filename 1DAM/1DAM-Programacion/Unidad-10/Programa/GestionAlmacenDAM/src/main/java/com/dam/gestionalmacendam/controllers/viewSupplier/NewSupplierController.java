package com.dam.gestionalmacendam.controllers.viewSupplier;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Employee;
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

public class NewSupplierController {
    SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    private TextField areaDirection;
    @FXML
    private TextField areaPhone;

    @FXML
    private TextField areaEmail;

    @FXML
    private TextField areaNombre;


    @FXML
    public void onAceptarAction(ActionEvent event) {
        boolean datosValidos = coprobarDatos();
        if (datosValidos) {
            saveSuplier();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmación");
            alert.setContentText("El supplier se ha creado");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Datos no validos.");
            alert.showAndWait();
        }
    }


    @FXML
    private boolean coprobarDatos() {
        String errorMessage = "";

        if (areaNombre.getText() == null || areaNombre.getText().isBlank()) {
            errorMessage += "Debes introducir nombre correcto. ";
        }
        if (areaDirection.getText() == null || areaDirection.getText().isBlank()) {
            errorMessage += "Debes introducir la dirección correcta. ";
        }
        if (areaPhone.getText() == null || areaPhone.getText().isBlank() || !Patterns.patterPhone(areaPhone.getText())) {
            errorMessage += "Debes introducir un teléfono correcto. ";

        }
        if (areaEmail.getText() == null || areaEmail.getText().isBlank() || !Patterns.patternEmail(areaEmail.getText())) {
            errorMessage += "Debes introducir email correcto. ";
        }
        if (isExist(areaNombre.getText())) {
            errorMessage += "Ya existe un proveedor con ese nombre. Pruebe otro.\n";
            areaNombre.setText("");
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

    @FXML
    private void saveSuplier() {
        try {
            repository.save(
                    new Supplier(
                            areaNombre.getText(),
                            areaDirection.getText(),
                            areaPhone.getText(),
                            areaEmail.getText()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean isExist(String text) {
        Optional<Supplier> supplier = null;
        boolean ok = true;
        try {
            supplier = repository.findAll().stream().filter(c -> c.getNameSupplier().equals(text)).findFirst();
            if (supplier.isEmpty()) {
                ok = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ok;
    }


}
