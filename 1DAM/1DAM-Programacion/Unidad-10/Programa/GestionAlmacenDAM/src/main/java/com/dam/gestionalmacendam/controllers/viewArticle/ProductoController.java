package com.dam.gestionalmacendam.controllers.viewArticle;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.utils.AlertInfo;
import com.dam.gestionalmacendam.utils.Patterns;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProductoController {

    @FXML
    TextField productName;
    @FXML
    TextField productLocat;
    @FXML
    TextField productDesc;
    @FXML
    TextField productPrice;
    @FXML
    TextField productStock;
    @FXML
    CheckBox isActive;
    @FXML
    ImageView imageProd;


    private Stage dialogStage;

    private Article producto;

    private boolean aceptarClicked = false;
    private boolean editMode = false;

    // GETTERS AND SETTERS
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProducto(Article producto) {
        this.producto = producto;
        System.out.println("Producto asociado: " + producto);
        if (editMode) {
            setDataInfo();
        }
        productName.requestFocus();
    }

    public void setEditarModo(boolean editarModo) {
        this.editMode = editarModo;
    }

    public boolean isAceptarClicked() {
        return aceptarClicked;
    }


    private void setDataInfo() {
        productName.setText(producto.getArticle().get());
        productLocat.setText(producto.getLocation().get());
        productDesc.setText(producto.getDescription().get());
        productPrice.setText(producto.getPrice().asObject().get().toString());
        productStock.setText(producto.getStock().asString().getValue());
        isActive.setSelected(producto.getIsActive().get());

        if (!producto.getPhoto().isBlank() && Files.exists(Paths.get(producto.getPhoto().replace("file:/", "")))) {
            System.out.println(("Buscando la imagen: " + producto.getPhoto()));
            Image image = new Image(new File(producto.getPhoto()).toURI().toString());
            System.out.println(("Imagen Encontrada en: " + image.getUrl()));
            imageProd.setImage(image);
        } else {
            System.out.println(("No existe la imagen. Usando imagen por defecto"));
            imageProd.setImage(new Image(Resources.get(HelloApplication.class, "img/cajita.png")));
            producto.setPhoto(new SimpleStringProperty(Resources.getPath(HelloApplication.class, "img/cajita.png")));
        }

    }

    private boolean isDataOk() {
        String errorMessage = "";

        if (productName.getText() == null || productName.getText().isBlank()) {
            errorMessage += "El nombre no puede estar en blanco\n";
        }
        if (productStock.getText() == null || productStock.getText().isBlank() || Patterns.isNumberInt(productStock.getText())) {
            errorMessage += "Cantidad Inválida\n";
        }
        if (productLocat.getText() == null || productLocat.getText().isBlank()) {
            errorMessage += "Ubicación no puede estar vacía\n";
        }

        if (productDesc.getText() == null || productDesc.getText().isBlank()) {
            errorMessage += "Descripción no puede estar vacía\n";
        }

        if (productPrice.getText() == null || productPrice.getText().isBlank() || Patterns.isNumerDouble(productPrice.getText())) {
            errorMessage += "Precio no puede estar vacío\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = AlertInfo.getAlertErrorDetails("Error en datos", "Datos del producto", "Existen problemas al intentar Aceptar.", errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void onAcept() {
        System.out.println(("Se ha pulsado en Aceptar"));
        if (isDataOk()) {
            producto.setArticle(new SimpleStringProperty(productName.getText()));
            producto.setLocation(new SimpleStringProperty(productLocat.getText()));
            producto.setDescription(new SimpleStringProperty(productDesc.getText()));
            producto.setStock(new SimpleIntegerProperty(Integer.parseInt(productStock.getText())));
            producto.setPrice(new SimpleDoubleProperty(Double.parseDouble(productPrice.getText())));
            producto.setPhoto(new SimpleStringProperty(imageProd.getImage().getUrl().replaceFirst("file:/", "")));
            producto.setIsActive(new SimpleBooleanProperty(isActive.isSelected()));
            aceptarClicked = true;
            dialogStage.close();
        } else {
            System.out.println(("Datos no validos"));
        }
    }


    @FXML
    private void onCancel() {
        System.out.println(("Has pulsado Cancelar"));
        dialogStage.close();
    }

    @FXML
    private void onClickImg() {
        FileChooser buscadorImg = new FileChooser();
        buscadorImg.setTitle("Selecciona la imagen del producto: ");
        buscadorImg.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagenes", "*.png"));
        File file = buscadorImg.showOpenDialog(imageProd.getScene().getWindow());

        if (file != null) {
            System.out.println(("Seleccion del archivo: " + file.getAbsolutePath()));
            imageProd.setImage(new Image(file.toURI().toString()));
            producto.setPhoto(new SimpleStringProperty(file.getAbsolutePath()));

        }
    }

}
