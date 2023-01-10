package com.dam.gestionalmacendam.controllers.viewCustomer;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

public class EditarCustomerController {
    private final CustomerRepository repository = CustomerRepository.getInstance(DataBaseManager.getInstance());
    private Stage stage;
    private Customer customer;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtCif;
    @FXML
    private TextField txtNick;
    @FXML
    private TextField txtDirection;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private CheckBox isActive;
    @FXML
    private ImageView imgView;

    @FXML
    public void onAceptarAction(ActionEvent actionEvent) {
        if (isDataValid()) {
            saveCustomer();
            stage.close();
        }
    }

    @FXML
    public void onClickImg(MouseEvent mouseEvent) {
        FileChooser buscadorImg = new FileChooser();
        buscadorImg.setTitle("Selecciona la imagen del employee: ");
        buscadorImg.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png"));
        File file = buscadorImg.showOpenDialog(imgView.getScene().getWindow());

        if (file != null) {
            System.out.println(("Seleccion del archivo: " + file.getAbsolutePath()));
            imgView.setImage(new Image(file.toURI().toString()));
            customer.setPhoto(new SimpleStringProperty(file.getAbsolutePath()));
        }
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        try {
            setDataInfo();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setDataInfo() throws SQLException {
        System.out.println("Esto es el SET DATA INFO");
        txtName.setText(customer.getName());
        txtSurname.setText(customer.getSurname());
        txtNick.setText(customer.getNickName());
        txtDirection.setText(customer.getDirection());
        txtPhone.setText(customer.getTelephoneNumber());
        txtEmail.setText(customer.getEmail());
        txtCif.setText(customer.getCif());
        isActive.setSelected(customer.isActive());

        if (!customer.getPhoto().isBlank() && Files.exists(Paths.get(customer.getPhoto().replace("file:/", "")))) {
            System.out.println(("Buscando la imagen: " + customer.getPhoto()));
            Image image = new Image(new File(customer.getPhoto()).toURI().toString());
            System.out.println(("Imagen Encontrada en: " + image.getUrl()));
            imgView.setImage(image);
        } else {
            System.out.println(("No existe la imagen. Usando imagen por defecto"));
            imgView.setImage(new Image(Resources.get(HelloApplication.class, "images/user_default.png")));
            System.out.println();
            customer.setPhoto(new SimpleStringProperty(Resources.getPath(HelloApplication.class, "images/user.png").replaceFirst("/", "")));
            repository.update(UUID.fromString(customer.getCIC()), customer);
        }
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
        if (txtPhone.getText() == null || txtPhone.getText().isBlank() || !Patterns.patterPhone(txtPhone.getText())) {
            errorMessage += "Debes introducir una teléfono válido.\n";
            txtPhone.setText("");
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
            customer.setName(new SimpleStringProperty(txtName.getText()));
            customer.setSurname(new SimpleStringProperty(txtSurname.getText()));
            customer.setNickName(new SimpleStringProperty(txtNick.getText()));
            customer.setCif(new SimpleStringProperty(txtCif.getText()));
            customer.setEmail(new SimpleStringProperty(txtEmail.getText()));
            customer.setDirection(new SimpleStringProperty(txtDirection.getText()));
            customer.setTelephoneNumber(new SimpleStringProperty(txtPhone.getText()));
            customer.setIsActive(new SimpleBooleanProperty(isActive.isSelected()));
            customer.setPhoto(new SimpleStringProperty(imgView.getImage().getUrl().replaceFirst("file:/", "")));
            repository.update(
                    UUID.fromString(customer.getCIC()), customer
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
