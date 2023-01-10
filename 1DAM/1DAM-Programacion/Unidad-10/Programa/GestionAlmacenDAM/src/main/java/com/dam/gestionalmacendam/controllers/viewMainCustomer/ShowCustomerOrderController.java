package com.dam.gestionalmacendam.controllers.viewMainCustomer;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.servicies.printers.HtmlPrinterOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ShowCustomerOrderController {
    private final OrderRepository repository = OrderRepository.getInstance(DataBaseManager.getInstance());
    private final LineOrderRepository line = LineOrderRepository.getInstance(DataBaseManager.getInstance());
    private final ArticleRepository article = ArticleRepository.getInstance(DataBaseManager.getInstance());
    private Customer customer;
    @FXML
    private ListView<LineOrder> listOrder;
    @FXML
    private Label labelNotOrder;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        try {
            initData();
            initRows();
            labelNotOrder.setVisible(listOrder.getItems().size() == 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void initRows() {
        listOrder.setCellFactory(param -> new ListCell<>() {
            @Override
            public void updateItem(LineOrder item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    try {
                        var product = article.findByName(item.getArticle().get());
                        HBox hbox = new HBox();
                        hbox.setSpacing(20);
                        ImageView view = new ImageView(product.getPhoto());
                        view.setPreserveRatio(false);
                        view.setFitHeight(100);
                        view.setFitWidth(130);
                        Label pedido = new Label(item.getArticle().get());
                        pedido.setWrapText(true);
                        pedido.setPadding(new Insets(25, 0, 0, 0));
                        pedido.setStyle("-fx-font-size: 20px");
                        pedido.setTextFill(Color.valueOf("#4b9bf0"));
                        pedido.setPrefWidth(170);


                        GridPane grid = new GridPane();
                        grid.setHgap(15);
                        grid.setVgap(5);
                        grid.setAlignment(Pos.CENTER);
                        grid.setPadding(new Insets(0, 40, 0, 0));

                        Label txtCant = new Label("Cantidad: ");
                        txtCant.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
                        grid.add(txtCant, 0, 0);

                        Label cantidad = new Label(String.valueOf(item.getLoad().get()));
                        grid.add(cantidad, 1, 0);
                        cantidad.setStyle("-fx-font-size: 16px");

                        Label txtPrice = new Label("Precio del producto: ");
                        grid.add(txtPrice, 0, 1);
                        txtPrice.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");

                        Label unitPrice = new Label(item.getUnitPrice().get() + " €");
                        grid.add(unitPrice, 1, 1);
                        unitPrice.setStyle("-fx-font-size: 16px");

                        Label txtTotal = new Label("Total :");
                        grid.add(txtTotal, 0, 2);
                        txtTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");

                        Label totalPrice = new Label(item.getTotalPrice().get() + " €");
                        grid.add(totalPrice, 1, 2);
                        totalPrice.setStyle("-fx-font-size: 16px");


                        hbox.getChildren().addAll(view, pedido, grid);
                        setGraphic(hbox);


                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
        });
    }

    private void initData() throws SQLException {
        var order = repository.findAll().stream().filter(o -> o.getCustomer().get().equals(customer.getName())).toList();
        var lineOrder = line.findAll();
        List<LineOrder> lines = new ArrayList<>();
        for (int i = 0; i < order.size(); i++) {
            for (int j = 0; j < lineOrder.size(); j++) {
                if (lineOrder.get(j).getBelongsOrder().get().equals(order.get(i).getOIC())) {
                    lines.add(lineOrder.get(j));
                }
            }
        }
        ObservableList<LineOrder> items = FXCollections.observableArrayList();
        items.addAll(lines);
        listOrder.setItems(items);
    }

    public void onTicketAction(ActionEvent actionEvent) {
        var lineOrder=listOrder.getSelectionModel().getSelectedItem();
        Order order=null;
        try {
            order= repository.findByUUID(lineOrder.getBelongsOrder().get());
            HtmlPrinterOrder printer = new HtmlPrinterOrder(order);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ticket Realizado");
        alert.setContentText("Puede encontrar su ticket en : GestionAlmacenDAM\\order\\Pedido."+order.getOIC()+"\\html");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        } else {
            alert.close();
        }
    }


}
