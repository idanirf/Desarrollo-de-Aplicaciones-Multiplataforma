package com.dam.gestionalmacendam.controllers.viewArticle;


import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;


public class ArticleViewController {


    ArticleRepository productosrepository = ArticleRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    TableView<Article> productosTable;

    @FXML
    TableColumn<Article, String> pic;

    @FXML
    TableColumn<Article, String> producto;

    @FXML
    TableColumn<Article, String> descripcion;
    @FXML
    TableColumn<Article, String> ubicacion;

    @FXML
    TableColumn<Article, Double> precio;

    @FXML
    TableColumn<Article, Integer> stock;

    @FXML
    TableColumn<Article, Boolean> activo;


    @FXML
    TextField busqueda;

    @FXML
    private void initialize() {
        try {
            loadProd();
            productosTable.refresh();
        } catch (SQLException e) {
            System.err.println("hey no he encontrado nada");
        }
        initColumns();

    }

    @FXML
    private void loadProd() throws SQLException {
        System.out.println("Cargando Productos");
        productosTable.setItems(productosrepository.findAll());
    }

    private void initColumns() {
        pic.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPIC()));
        producto.setCellValueFactory(data -> data.getValue().getArticle());
        descripcion.setCellValueFactory(data -> data.getValue().getDescription());
        ubicacion.setCellValueFactory(data -> data.getValue().getLocation());
        precio.setCellValueFactory(data -> data.getValue().getPrice().asObject());
        activo.setCellValueFactory(data -> data.getValue().getIsActive());
        stock.setCellValueFactory(data -> data.getValue().getStock().asObject());
    }

    @FXML
    private void onCreateAction() throws IOException {
        System.out.println("Integrando el Producto...");
        Article producto = new Article();
        boolean aceptarClicked = SceneManager.get().initProducto(false, producto);
        if (aceptarClicked) {
            try {
                productosrepository.save(producto);
                loadProd();
            } catch (SQLException e) {
                System.err.println(("Error al crear el producto: " + e.getMessage()));
            }
        }
    }

    @FXML
    private void onModAction() throws IOException {
        System.out.println(("Se ha pulsado accion Editar"));
        Article producto = productosTable.getSelectionModel().getSelectedItem();
        boolean aceptarClicked = SceneManager.get().initProducto(true, producto);
        if (aceptarClicked) {
            try {
                productosrepository.update(producto.getPIC(), producto);
            } catch (SQLException e) {
                System.err.println(("Error al actualizar dicha persona: " + e.getMessage()));
            }
        }
    }

    @FXML
    private void onVerDetalleAction() throws IOException {
        System.out.println(("Se ha pulsado ver Detalle"));
        Article producto = productosTable.getSelectionModel().getSelectedItem();
        SceneManager.get().initResumeArticle(producto);

    }

    @FXML
    private void findByName() throws SQLException {
        String name = busqueda.getText().toLowerCase();
        if (name.isEmpty()) {
            loadProd();
        } else {
            productosTable.setItems(productosrepository.findAll().filtered(x -> x.getArticle().get().toLowerCase().contains(name) || x.getPIC().contains(name)));
        }
        productosTable.refresh();
    }

}






