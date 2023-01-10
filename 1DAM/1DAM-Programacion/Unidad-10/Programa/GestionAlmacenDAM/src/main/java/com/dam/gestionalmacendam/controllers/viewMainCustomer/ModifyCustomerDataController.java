package com.dam.gestionalmacendam.controllers.viewMainCustomer;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class ModifyCustomerDataController {
    private final CustomerRepository repository = CustomerRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    private ImageView img;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtCif;
    @FXML
    private TextField txtDirection;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    private Customer customer;
    private Stage stage;

    public void btnSaveAction(ActionEvent actionEvent) {
        if (isValid()) {
            System.out.println("Guardando nuevo usuario...");
            saveCustomer();
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        initData();
    }

    private void initData() {
        Image image = new Image(new File(customer.getPhoto()).toURI().toString());
        img.setImage(image);
        txtName.setText(customer.getName());
        txtSurname.setText(customer.getSurname());
        txtCif.setText(customer.getCif());
        txtDirection.setText(customer.getDirection());
        txtPhone.setText(customer.getTelephoneNumber());
        txtEmail.setText(customer.getEmail());
    }

    public void onPhotoClick(MouseEvent event) {
        System.out.println("Seleccionando la foto...");
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Indica la foto que desea.");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.jpg", "*.png"));
        File file = filechooser.showOpenDialog(img.getScene().getWindow());

        if (file != null) {
            img.setImage(new Image(file.toURI().toString()));
        }
    }

    public boolean isValid() {
        String errorMessage = "";

        if (txtName.getText() == null || !Patterns.patternName(txtName.getText())) {
            errorMessage += "El nombre no puede estar vacio o es incorrecto. Ejemplo: Jaime\n";
        }
        if (txtSurname.getText() == null || !Patterns.patternSurnames(txtSurname.getText())) {
            errorMessage += "El apellido no puede estar vacio o es incorrecto. Ejemplo: Santoyo Salazar\n";
        }
        if (txtCif.getText() == null || !Patterns.patternCif(txtCif.getText())) {
            errorMessage += "El CIF no puede estar vacio o es incorrecto. Ejemplo: 12345678A\n";
        }
        if (txtDirection.getText() == null || txtDirection.getText().length() == 0) {
            errorMessage += "La dirección no puede estar vacia.\n";
        }
        if (txtPhone.getText() == null || !Patterns.patterPhone(txtPhone.getText())) {
            errorMessage += "El campo de teléfono esta vacio o es incorrecto: Ejemplo: 610245879\n";
        }
        if (txtEmail.getText() == null || !Patterns.patternEmail(txtEmail.getText())) {
            errorMessage += "El correo no puede estar vacio o es incorrecto. Ejemplo: ejemplo1@gmail.com\n";
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
        System.out.println(customer);
        try {
            System.out.println("custo");
            customer.setName(new SimpleStringProperty(txtName.getText()));
            customer.setSurname(new SimpleStringProperty(txtSurname.getText()));
            customer.setCif(new SimpleStringProperty(txtCif.getText()));
            customer.setDirection(new SimpleStringProperty(txtDirection.getText()));
            customer.setTelephoneNumber(new SimpleStringProperty(txtPhone.getText()));
            customer.setEmail(new SimpleStringProperty(txtEmail.getText()));
            customer.setPhoto(new SimpleStringProperty(img.getImage().getUrl().replaceFirst("file:/", "")));
            repository.update(
                    UUID.fromString(customer.getCIC()),
                    customer
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
