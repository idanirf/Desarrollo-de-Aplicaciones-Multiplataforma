package com.dam.gestionalmacendam.controllers.viewEmployee;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

public class EditarEmployeeController {
    private static EditarEmployeeController instance;
    private static Employee employee;
    private final boolean aceptarClicked = false;
    private final boolean editMode = false;
    EmployeeRepository repository = EmployeeRepository.getInstance(DataBaseManager.getInstance());
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
    private Stage dialogStage;

    public static EditarEmployeeController get() {
        return instance;
    }

    public void setEmployee(Employee emplo) {
        employee = emplo;
        try {
            setDataInfo();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isAceptarClicked() {
        return aceptarClicked;
    }

    private void setDataInfo() throws SQLException {
        System.out.println("Esto es el SET DATA INFO");
        nombre.setText(employee.getName());
        surname.setText(employee.getSurname());
        nick.setText(employee.getNickName());
        password.setText(employee.getPassword());
        email.setText(employee.getEmail());
        nif.setText(employee.getNif());
        isManager.setSelected(employee.getIsActive().get());
        isActive.setSelected(employee.getIsActive().get());

        if (!employee.getPhoto().isBlank() && Files.exists(Paths.get(employee.getPhoto().replace("file:/", "")))) {
            System.out.println(("Buscando la imagen: " + employee.getPhoto()));
            Image image = new Image(new File(employee.getPhoto()).toURI().toString());
            System.out.println(("Imagen Encontrada en: " + image.getUrl()));
            imagePerfil.setImage(image);
        } else {
            System.out.println(("No existe la imagen. Usando imagen por defecto"));
            imagePerfil.setImage(new Image(Resources.get(HelloApplication.class, "images/user.png")));
            System.out.println();
            employee.setPhoto(new SimpleStringProperty(Resources.getPath(HelloApplication.class, "images/user.png").replaceFirst("/", "")));
            repository.update(UUID.fromString(employee.getEIC()), employee);
        }
    }

    public void onAceptarAction() {
        if (isDataValid()) {
            saveEmployee();
            dialogStage.close();
        } else {
            System.out.println("Error");
        }
    }

    private void saveEmployee() {
        try {
            employee.setName(new SimpleStringProperty(nombre.getText()));
            employee.setSurname(new SimpleStringProperty(surname.getText()));
            employee.setNickName(new SimpleStringProperty(nick.getText()));
            employee.setPassword(new SimpleStringProperty(password.getText()));
            employee.setNif(new SimpleStringProperty(nif.getText()));
            employee.setEmail(new SimpleStringProperty(email.getText()));
            employee.setIsManager(new SimpleBooleanProperty(isManager.isSelected()));
            employee.setIsActive(new SimpleBooleanProperty(isActive.isSelected()));
            employee.setPhoto(new SimpleStringProperty(imagePerfil.getImage().getUrl().replaceFirst("file:/", "")));
            repository.update(
                    UUID.fromString(employee.getEIC()), employee
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
        return errorMessage.length() == 0;
    }

    @FXML
    private void onClickImg() {
        FileChooser buscadorImg = new FileChooser();
        buscadorImg.setTitle("Selecciona la imagen del employee: ");
        buscadorImg.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png"));
        File file = buscadorImg.showOpenDialog(imagePerfil.getScene().getWindow());

        if (file != null) {
            System.out.println(("Seleccion del archivo: " + file.getAbsolutePath()));
            imagePerfil.setImage(new Image(file.toURI().toString()));
            employee.setPhoto(new SimpleStringProperty(file.getAbsolutePath()));
        }
    }
}
