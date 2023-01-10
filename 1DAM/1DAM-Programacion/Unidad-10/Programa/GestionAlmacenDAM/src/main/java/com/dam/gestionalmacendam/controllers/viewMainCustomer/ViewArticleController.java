package com.dam.gestionalmacendam.controllers.viewMainCustomer;

import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.models.CarritoItem;
import com.dam.gestionalmacendam.repositories.carrito.CarritoRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;


public class ViewArticleController {
    private final CarritoRepository carrito = CarritoRepository.getInstance();
    private Article article;
    private Stage stage;
    @FXML
    private ImageView viewArticle;
    @FXML
    private Label txtNameArticle;
    @FXML
    private Label txtStock;
    @FXML
    private Label txtPrice;
    @FXML
    private Label txtDescription;
    @FXML
    private Button btnAddArticle;

    @FXML
    public void btnAddArticleAction(ActionEvent actionEvent) {
        var aux = findItem();
        if (aux.isEmpty()) {
            carrito.addItem(setItem());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Añadiendo");
            alert.setHeaderText("Añadiendo el producto seleccionado a la cesta...");
            alert.show();

        } else {
            aux.get().setAmount(aux.get().getAmount() + 1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Añadiendo");
            alert.setHeaderText("Aumentando la cantidad del producto seleccionado en la cesta...");
            alert.show();
        }

    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
        initArticle();
    }

    private void initArticle() {
        Image image = new Image(new File(article.getPhoto()).toURI().toString());
        viewArticle.setFitHeight(250);
        viewArticle.setFitWidth(320);
        viewArticle.setPreserveRatio(false);
        viewArticle.setImage(image);

        txtNameArticle.setText(article.getArticle().get());
        txtNameArticle.setWrapText(true);
        txtStock.setText("Stock: " + article.getStock().get());
        txtPrice.setText("Precio: " + article.getPrice().get() + "€");
        txtDescription.setText(article.getDescription().get());
        txtDescription.setWrapText(true);
        if (!article.isActive()) {
            btnAddArticle.setDisable(true);
        }
    }

    private CarritoItem setItem() {
        System.out.println("Metiendo articulo a la cesta.");
        return new CarritoItem(
                article.getArticle().get(),
                article.imagenProperty().get(),
                article.getPrice().get(),
                1
        );
    }

    private Optional<CarritoItem> findItem() {
        return carrito.getItems().stream().filter(item -> item.getName().equals(article.getArticle().get())).findFirst();
    }
}
