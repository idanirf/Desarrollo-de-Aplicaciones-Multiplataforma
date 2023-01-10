package com.dam.gestionalmacendam.controllers.viewOrder;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.servicies.printers.HtmlPrinterOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Optional;

public class LineaOrderController {
    private Order o;
    private final LineOrderRepository repo =  LineOrderRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    TableColumn<LineOrder, String> articleCol;
    @FXML
    TableColumn<LineOrder, Integer> cantCol;
    @FXML
    TableColumn<LineOrder, Double> unitCol;
    @FXML
    TableColumn<LineOrder, Double> totalCol;
    @FXML
    TableView<LineOrder> OrderView;
    @FXML
    private TextField order;
    @FXML
    private Button crearTiketButon;

    @FXML
    void onCrearTiket(ActionEvent event) {
        HtmlPrinterOrder printer = new HtmlPrinterOrder(o);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ticket Realizado");
        alert.setContentText("Puede encontrar su ticket en : \n GestionAlmacenDAM\\order\\ \nPedido."+o.getOIC()+"\\html");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        } else {
            alert.close();
        }
    }

    private void initColumns() {
        articleCol.setCellValueFactory(data -> data.getValue().getArticle());
        cantCol.setCellValueFactory(data -> data.getValue().getLoad().asObject());
        unitCol.setCellValueFactory(data -> data.getValue().getUnitPrice().asObject());
        totalCol.setCellValueFactory(data -> data.getValue().getTotalPrice().asObject());
    }

    private void loadLineOrder() throws SQLException {
        OrderView.setItems(repo.findAll().filtered(z -> z.getBelongsOrder().get().equals(o.getOIC())));
    }

    private void setDataInfo() {
        order.setText(o.getOIC());
    }

    public void setOrder(Order order) throws SQLException {
        System.out.println("entra en resumen");
        this.o = order;
        setDataInfo();
        loadLineOrder();
        initColumns();
    }

}
