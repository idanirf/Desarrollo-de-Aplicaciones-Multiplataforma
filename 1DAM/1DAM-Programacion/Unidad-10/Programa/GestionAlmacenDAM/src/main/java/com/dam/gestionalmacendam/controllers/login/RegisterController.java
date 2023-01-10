package com.dam.gestionalmacendam.controllers.login;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class RegisterController {
    CustomerRepository repository = CustomerRepository.getInstance(DataBaseManager.getInstance());
    EmployeeRepository employeeRepo = EmployeeRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    TextField txtName;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtCIF;
    @FXML
    TextField txtNick;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtPasswordConfir;
    @FXML
    TextField txtDirection;
    @FXML
    TextField txtPhone;
    @FXML
    TextField txtEmail;
    @FXML
    ImageView photoView;

    @FXML
    CheckBox checkAcept;
    private Stage stage;

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    public void onPhotoClick(MouseEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Indica la foto que desea.");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.jpg", "*.png"));
        File file = filechooser.showOpenDialog(photoView.getScene().getWindow());

        if (file != null) {
            photoView.setImage(new Image(file.toURI().toString()));
        }
    }

    public void btnSaveAction(ActionEvent event) {
        if (checkAcept.isSelected()) {
            if (isValid()) {
                if (resumen()) {
                    System.out.println("Guardando nuevo usuario...");
                    saveCustomer();
                    stage.close();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("No han sido aceptado los términos.");
            alert.setContentText("Seleccione Aceptar los Términos.");
            alert.showAndWait();

        }

    }

    public boolean isValid() {
        String errorMessage = "";

        if (txtName.getText() == null || !Patterns.patternName(txtName.getText())) {
            errorMessage += "El nombre no puede estar vacio o es incorrecto. Ejemplo: Jaime\n";
            txtName.setText("");
        }
        if (txtSurname.getText() == null || !Patterns.patternSurnames(txtSurname.getText())) {
            errorMessage += "El apellido no puede estar vacio o es incorrecto. Ejemplo: Santoyo Salazar\n";
            txtSurname.setText("");
        }
        if (txtNick.getText() == null || txtNick.getText().length() == 0) {
            errorMessage += "El nick no puede estar vacio o es incorrecto. Ejemplo: Darkness07\n";
            txtNick.setText("");
        }
        if (txtCIF.getText() == null || !Patterns.patternCif(txtCIF.getText())) {
            errorMessage += "El CIF no puede estar vacio o es incorrecto. Ejemplo: 12345678A\n";
            txtCIF.setText("");
        }
        if (txtPassword.getText() == null || !Patterns.patternPassword(txtPassword.getText())) {
            errorMessage += "La contraseña debe contener letras,números y símbolos. Además que debe incluir una letra en mayúscula.\n";
            txtPassword.setText("");
        }
        if (!txtPasswordConfir.getText().equals(txtPassword.getText()) || txtPasswordConfir.getText() == null || txtPasswordConfir.getText().length() == 0) {
            errorMessage += "La contraseña no coinciden.\n";
            txtPasswordConfir.setText("");
        }
        if (txtDirection.getText() == null || txtDirection.getText().length() == 0) {
            errorMessage += "La dirección no puede estar vacia.\n";
            txtDirection.setText("");
        }
        if (txtPhone.getText() == null || !Patterns.patterPhone(txtPhone.getText())) {
            errorMessage += "El campo de teléfono esta vacio o es incorrecto: Ejemplo: 610245879\n";
            txtPhone.setText("");
        }
        if (txtEmail.getText() == null || !Patterns.patternEmail(txtEmail.getText())) {
            errorMessage += "El correo no puede estar vacio o es incorrecto. Ejemplo: ejemplo1@gmail.com\n";
            txtEmail.setText("");
        }
        if (isExist(txtNick.getText())) {
            errorMessage += "Ya existe un usuario con ese nick. Pruebe otro.\n";
            txtNick.setText("");
        }
        if (isExistCif(txtCIF.getText())) {
            errorMessage += "Ya existe un usuario con ese CIF.\n";
            txtCIF.setText("");
        }


        if (errorMessage.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los datos");
            alert.setHeaderText("Datos introducidos incorrectos");
            alert.setContentText("Hay datos incorrectos");
            Label label = new Label("Los errores son:");

            TextArea textArea = new TextArea(errorMessage);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
            return false;
        } else {
            return true;
        }

    }

    private void saveCustomer() {
        try {
            repository.save(
                    new Customer(
                            txtName.getText(),
                            txtSurname.getText(),
                            txtCIF.getText(),
                            txtDirection.getText(),
                            txtNick.getText(),
                            txtPassword.getText(),
                            txtPhone.getText(),
                            txtEmail.getText(),
                            photoView.getImage().getUrl().replaceFirst("file:/", ""),
                            LocalDateTime.now(),
                            true
                    )
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean resumen() {
        var message = "Nombre: " + txtName.getText() + "\n" +
                "Apellidos: " + txtSurname.getText() + "\n" +
                "CIF: " + txtCIF.getText() + "\n" +
                "Dirección: " + txtDirection.getText() + "\n" +
                "NickUser: " + txtNick.getText() + "\n" +
                "Teléfono: " + txtPhone.getText() + "\n" +
                "Email: " + txtEmail.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Datos de Usuario Nuevo.");
        alert.setHeaderText("Compruebe si todos los datos son correctos.");
        alert.setContentText("Si desea modificar algo, pulse cancelar.");
        Label label = new Label("Datos:");

        TextArea textArea = new TextArea(message);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        var res = alert.showAndWait();
        return res.get() == ButtonType.OK;
    }

    public boolean isExist(String text) {
        Optional<Customer> customer = null;
        Optional<Employee> employee = null;
        boolean ok = true;
        try {
            customer = repository.findAll().stream().filter(c -> c.getNickName().equals(text)).findFirst();
            employee = employeeRepo.findAll().stream().filter(c -> c.getNickName().equals(text)).findFirst();
            if (customer.isEmpty() && employee.isEmpty()) {
                ok = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ok;
    }
    public boolean isExistCif(String text) {
        Optional<Customer> customer = null;
        boolean ok = true;
        try {
            customer = repository.findAll().stream().filter(c -> c.getCif().equals(text)).findFirst();
            if (customer.isEmpty()) {
                ok = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ok;
    }
}
