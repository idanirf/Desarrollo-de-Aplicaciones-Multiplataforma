package com.dam.gestionalmacendam.controllers.viewOrder;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


public class OrderController {
    OrderRepository repository = OrderRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    private TextField textAreaBuscarPorOic;

    @FXML
    private TableView<Order> tablaPedidos;

    @FXML
    private TableColumn<Order, String> columnaCliente;

    @FXML
    private TableColumn<Order, Pay> columnaMetodoDePago;

    @FXML
    private TableColumn<Order, String> columnaOIC;

    @FXML
    private TableColumn<Order, Double> columnaPrecio;

    @FXML
    void onButonVerDetalle(MouseEvent event) throws SQLException {
    }

    @FXML
    private void initialize() {

        try {
            loadData();
            tablaPedidos.refresh();
        } catch (SQLException e) {
            System.out.println("No se ha podido cargar la lista de personas");
        }

        columnaOIC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOIC()));
        columnaCliente.setCellValueFactory(cellData -> cellData.getValue().getCustomer());
        columnaMetodoDePago.setCellValueFactory(cellData -> cellData.getValue().getMethodPay());
        columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());

    }

    @FXML
    private void loadData() throws SQLException {
       tablaPedidos.setItems(repository.findAll());
    }

    public void selecionarAcion() throws SQLException, IOException {
        Order o = tablaPedidos.getSelectionModel().getSelectedItem();
        SceneManager.get().initLineOrderView(o);
    }

    public void findByName(ActionEvent actionEvent) throws SQLException {
        String name = textAreaBuscarPorOic.getText().trim().toLowerCase();
        if (name.isEmpty()) {
            loadData();
        } else {
            tablaPedidos.setItems(repository.findAll()
                    .filtered(x -> x.getCustomer().get()
                    .toLowerCase().contains(name) || x.getOIC().contains(name)));
        }
        tablaPedidos.refresh();
    }

}
