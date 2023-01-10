package com.dam.gestionalmacendam.controllers.viewEmployee;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class NewEmployeeController {
    EmployeeRepository repository = EmployeeRepository.getInstance(DataBaseManager.getInstance());
    CustomerRepository repo = CustomerRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    TextField nombre;
    @FXML
    TextField surname;
    @FXML
    TextField nick;
    @FXML
    PasswordField password;
    @FXML
    TextField nif;
    @FXML
    TextField email;
    @FXML
    CheckBox isManager;
    @FXML
    ImageView imagePerfil;
    @FXML
    CheckBox isActive;
    private boolean aceptarClicked = false;
    private Employee employee;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isAceptarClicked() {
        return aceptarClicked;
    }


    public void onAceptarAction() {
        if (isDataValid()) {
            saveEmployee();
            this.aceptarClicked = true;
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Datos no validos.");
            alert.setContentText("Datos erroneos.");
            alert.showAndWait();
        }
    }

    private void saveEmployee() {
        try {
            repository.save(
                    new Employee(
                            nombre.getText(),
                            surname.getText(),
                            nif.getText(),
                            email.getText(),
                            imagePerfil.getImage().getUrl().replaceFirst("file:/", ""),
                            nick.getText(),
                            password.getText(),
                            isManager.isSelected(),
                            LocalDateTime.now(),
                            isActive.isSelected())
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isDataValid() {
        String errorMessage = "";
        if (nombre.getText() == null || nombre.getText().isBlank() || !Patterns.patternName(nombre.getText())) {
            errorMessage += "Debes introducir el nombre";
            nombre.setText("");
        }
        if (surname.getText() == null || surname.getText().isBlank() || !Patterns.patternSurnames(surname.getText())) {
            errorMessage += "Debes introducir apellidos";
            surname.setText("");
        }
        if (nick.getText() == null || nick.getText().isBlank()) {
            errorMessage += "Debes introducir nick de usuario";
            nick.setText("");
        }
        if (password.getText() == null || password.getText().isBlank() || !Patterns.patternPassword(password.getText())) {
            errorMessage += "Debes introducir una contraseña";
            password.setText("");
        }
        if (email.getText() == null || email.getText().isBlank() || !Patterns.patternEmail(email.getText())) {
            errorMessage += "Debes introducir un email con un formato correcto";
            email.setText("");
        }
        if (nif.getText() == null || nif.getText().isBlank() || !Patterns.patternCif(nif.getText())) {
            errorMessage += "Debes introducir NIF valido";
            nif.setText("");
        }
        if (isExist(nick.getText())) {
            errorMessage += "Ya existe un usuario con ese nick. Pruebe otro.\n";
            nick.setText("");
        }
        if (isExistCif(nif.getText())) {
            errorMessage += "Ya existe un usuario con ese NIF.\n";
            nif.setText("");
        }
        return errorMessage.length() == 0;
    }

    @FXML
    private void onClickImg() {
        FileChooser buscadorImg = new FileChooser();
        buscadorImg.setTitle("Selecciona la imagen del producto: ");
        buscadorImg.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png"));
        File file = buscadorImg.showOpenDialog(imagePerfil.getScene().getWindow());

        if (file != null) {
            System.out.println(("Seleccion del archivo: " + file.getAbsolutePath()));
            imagePerfil.setImage(new Image(file.toURI().toString()));
            employee.setPhoto(new SimpleStringProperty(file.getAbsolutePath()));
        }
    }
    public boolean isExist(String text) {
        Optional<Customer> customer = null;
        Optional<Employee> employee = null;
        boolean ok = true;
        try {
            customer = repo.findAll().stream().filter(c -> c.getNickName().equals(text)).findFirst();
            employee = repository.findAll().stream().filter(c -> c.getNickName().equals(text)).findFirst();
            if (customer.isEmpty() && employee.isEmpty()) {
                ok = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ok;
    }
    public boolean isExistCif(String text) {
        Optional<Employee> employee = null;
        boolean ok = true;
        try {
            employee = repository.findAll().stream().filter(c -> c.getNif().equals(text)).findFirst();
            if (employee.isEmpty()) {
                ok = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ok;
    }
}
