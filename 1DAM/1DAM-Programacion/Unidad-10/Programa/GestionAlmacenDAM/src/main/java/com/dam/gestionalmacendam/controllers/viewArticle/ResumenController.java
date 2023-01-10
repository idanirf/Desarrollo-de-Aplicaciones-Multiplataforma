package com.dam.gestionalmacendam.controllers.viewArticle;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResumenController {
    @FXML
    ImageView imagen;
    @FXML
    CheckBox productActive;
    @FXML
    Label productStock;
    @FXML
    Label producPrice;
    @FXML
    Label productLocat;
    @FXML
    Label productDesc;
    @FXML
    Label productoTitle;
    @FXML
    Label productoName;


    private Article producto;

    public void setProducto(Article producto) {
        this.producto = producto;
        System.out.println(producto);
        setDataInfo();
        productActive.setDisable(true);

    }

    private void setDataInfo() {
        productoTitle.setText(producto.getArticle().get());
        productoName.setText(producto.getArticle().get());
        productLocat.setText(producto.getLocation().get());
        productDesc.setText(producto.getDescription().get());
        producPrice.setText(producto.getPrice().asObject().get().toString());
        productStock.setText(producto.getStock().asString().getValue());
        productActive.setSelected(producto.getIsActive().get());

        if (!producto.getPhoto().isBlank() && Files.exists(Paths.get(producto.getPhoto().replace("file:/", "")))) {
            System.out.println(("Buscando la imagen: " + producto.getPhoto()));
            Image image = new Image(new File(producto.getPhoto()).toURI().toString());
            System.out.println(("Imagen Encontrada en: " + image.getUrl()));
            imagen.setImage(image);
        } else {
            System.out.println(("No existe la imagen. Usando imagen por defecto"));
            imagen.setImage(new Image(Resources.get(HelloApplication.class, "images/cajita.png")));
            producto.setPhoto(new SimpleStringProperty(Resources.getPath(HelloApplication.class, "images/cajita.png")));
        }

    }


}
