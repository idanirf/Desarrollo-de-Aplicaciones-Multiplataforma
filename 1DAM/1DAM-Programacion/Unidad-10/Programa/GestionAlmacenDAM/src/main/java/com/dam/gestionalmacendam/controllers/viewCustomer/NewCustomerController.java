package com.dam.gestionalmacendam.controllers.viewCustomer;

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

public class NewCustomerController {
    private final CustomerRepository repo = CustomerRepository.getInstance(DataBaseManager.getInstance());
    EmployeeRepository employeeRepo = EmployeeRepository.getInstance(DataBaseManager.getInstance());
    private Stage stage;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtCif;
    @FXML
    private TextField txtNick;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtDirection;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private ImageView imgView;
    @FXML
    private CheckBox isActive;

    public void onAceptarAction(ActionEvent actionEvent) {
        if (isDataValid()) {
            saveCustomer();
            stage.close();
        }
    }

    public void onClickImg(MouseEvent mouseEvent) {
        FileChooser buscadorImg = new FileChooser();
        buscadorImg.setTitle("Selecciona la imagen del employee: ");
        buscadorImg.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png"));
        File file = buscadorImg.showOpenDialog(imgView.getScene().getWindow());

        if (file != null) {
            System.out.println(("Seleccion del archivo: " + file.getAbsolutePath()));
            imgView.setImage(new Image(file.toURI().toString()));
        }
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    private boolean isDataValid() {
        String errorMessage = "";
        if (txtName.getText() == null || txtName.getText().isBlank() || !Patterns.patternName(txtName.getText())) {
            errorMessage += "Debes introducir el nombre\n";
            txtName.setText("");
        }
        if (txtSurname.getText() == null || txtSurname.getText().isBlank() || !Patterns.patternSurnames(txtSurname.getText())) {
            errorMessage += "Debes introducir apellidos\n";
            txtSurname.setText("");
        }
        if (txtNick.getText() == null || txtNick.getText().isBlank()) {
            errorMessage += "Debes introducir nick de usuario\n";
            txtNick.setText("");
        }
        if (txtEmail.getText() == null || txtEmail.getText().isBlank() || !Patterns.patternEmail(txtEmail.getText())) {
            errorMessage += "Debes introducir un email con un formato correcto\n";
            txtEmail.setText("");
        }
        if (txtCif.getText() == null || txtCif.getText().isBlank() || !Patterns.patternCif(txtCif.getText())) {
            errorMessage += "Debes introducir CIF valido\n";
            txtCif.setText("");
        }
        if (txtDirection.getText() == null || txtDirection.getText().isBlank()) {
            errorMessage += "Debes introducir una dirección.\n";
            txtDirection.setText("");
        }
        if (txtPassword.getText() == null || txtPassword.getText().isBlank() || !Patterns.patternPassword(txtPassword.getText())) {
            errorMessage += "Debes introducir una contraseña correcta.\n";
            txtPassword.setText("");
        }
        if (txtPhone.getText() == null || txtPhone.getText().isBlank() || !Patterns.patterPhone(txtPhone.getText())) {
            errorMessage += "Debes introducir una teléfono válido.\n";
            txtPhone.setText("");
        }
        if (isExist(txtNick.getText())) {
            errorMessage += "Ya existe un usuario con ese nick. Pruebe otro.\n";
            txtNick.setText("");
        }
        if (isExistCif(txtCif.getText())) {
            errorMessage += "Ya existe un usuario con ese CIF.\n";
            txtCif.setText("");
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
            repo.save(
                    new Customer(
                            txtName.getText(),
                            txtSurname.getText(),
                            txtCif.getText(),
                            txtDirection.getText(),
                            txtNick.getText(),
                            txtPassword.getText(),
                            txtPhone.getText(),
                            txtEmail.getText(),
                            imgView.getImage().getUrl().replaceFirst("file:/", ""),
                            LocalDateTime.now(),
                            isActive.isSelected()
                    )
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean isExist(String text) {
        Optional<Customer> customer = null;
        Optional<Employee> employee = null;
        boolean ok = true;
        try {
            customer = repo.findAll().stream().filter(c -> c.getNickName().equals(text)).findFirst();
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
            customer = repo.findAll().stream().filter(c -> c.getCif().equals(text)).findFirst();
            if (customer.isEmpty()) {
                ok = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ok;
    }
}
