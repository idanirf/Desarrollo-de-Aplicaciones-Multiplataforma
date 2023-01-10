package com.dam.gestionalmacendam.controllers.menus;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;


public class MenuCustomerController {
    private final ArticleRepository articleRepository = ArticleRepository.getInstance(DataBaseManager.getInstance());
    private Stage stage;
    @FXML
    private TextField txtSearch;
    @FXML
    private GridPane grid;
    private Customer customer;

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void btnCarritoAction(ActionEvent actionEvent) {
        try {
            SceneManager.get().initCarrito(customer, stage);
            initArticles(null);
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void btnMyOrderClick(MouseEvent mouseEvent) {
        try {
            SceneManager.get().initViewOrderCustomer(customer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnMyUserClick(MouseEvent mouseEvent) {
        try {
            SceneManager.get().initViewDataCustomer(customer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initArticles(String name) throws SQLException {
        System.out.println("Iniciando los articulos.");
        List<Article> li;

        if (name == null) {
            li = articleRepository.findAll();
        } else {
            li = articleRepository.findAll().stream().filter(a -> a.getArticle().get().toLowerCase().contains(name.toLowerCase().trim())).toList();
        }
        grid.getChildren().clear();
        var k = 0;
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                if (k < li.size()) {
                    VBox vbox = getItemCell(li, k);
                    grid.add(vbox, j, i);
//                    grid.setGridLinesVisible(true);
                    k++;
                }
            }
        }

    }

    private VBox getItemCell(List<Article> list, int pos) throws SQLException {
        var article = list.get(pos);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setStyle("-fx-border-color: transparent gray gray transparent ");
        ImageView iv = new ImageView();
        iv.setPreserveRatio(false);
        iv.setFitHeight(130);
        iv.setFitWidth(130);

        if (!article.getPhoto().isBlank() && Files.exists(Paths.get(article.getPhoto()))) {
            Image image = new Image(new File(article.getPhoto()).toURI().toString());
            iv.setImage(image);
        } else {
            iv.setImage(new Image(Resources.get(HelloApplication.class, "images/article_default.png")));
            list.get(pos).setPhoto(new SimpleStringProperty(Resources.getPath(HelloApplication.class, "images/article_default.png").replaceFirst("/", "")));
            articleRepository.update(list.get(pos).getPIC(), list.get(pos));
        }

        HBox hbox = new HBox();
        hbox.setSpacing(15);
        hbox.setAlignment(Pos.TOP_CENTER);
        Label nombre = new Label(list.get(pos).getArticle().get());
        nombre.setStyle("-fx-font-weight: bold");
        nombre.setStyle("-fx-font-size: 16");
        Label precio = new Label(list.get(pos).getPrice().get() + " €");
        precio.setStyle("-fx-font-size: 16");
        hbox.getChildren().addAll(nombre, precio);
        vbox.getChildren().addAll(iv, hbox);

        vbox.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                SceneManager scene = SceneManager.get();
                try {
                    scene.initViewArticle(list.get(pos));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return vbox;
    }

    public void findByName(ActionEvent actionEvent) {
        try {
            initArticles(txtSearch.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        try {
            initArticles(null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
