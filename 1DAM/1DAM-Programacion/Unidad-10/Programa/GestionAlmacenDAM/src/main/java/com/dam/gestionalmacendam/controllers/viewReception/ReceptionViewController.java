package com.dam.gestionalmacendam.controllers.viewReception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ReceptionViewController {
    ReceptionRepository repo = ReceptionRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    TableView<Reception> tableRecept;
    @FXML
    TableColumn<Reception, String> ric;
    @FXML
    TableColumn<Reception, String> supplier;
    @FXML
    TableColumn<Reception, String> carrier;
    @FXML
    TableColumn<Reception, Double> cost;
    @FXML
    TextField find;


    @FXML
    private void initialize() {
        try {
            loadProd();
            tableRecept.refresh();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        initColumns();

    }

    private void initColumns() {
        ric.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRIC()));
        supplier.setCellValueFactory(data -> data.getValue().getSupplierName());
        carrier.setCellValueFactory(data -> data.getValue().getCarrier());
        cost.setCellValueFactory(data -> data.getValue().getCost().asObject());
    }

    private void loadProd() throws SQLException {
        tableRecept.setItems(repo.findAll());
    }


    @FXML
    private void findByName() throws SQLException {
        String name = find.getText().trim().toLowerCase();
        if (name.isEmpty()) {
            loadProd();
        } else {
            tableRecept.setItems(repo.findAll()
                    .filtered(x -> x.getSupplierName().get().toLowerCase().contains(name) || x.getRIC().contains(name)));
        }
        tableRecept.refresh();
    }


    @FXML
    private void onCreateAction() throws IOException {
        System.out.println("Insertando la Recepcion...");
        Reception recepcion = new Reception();
        LineReception lineReception = new LineReception();
        boolean aceptarClicked = SceneManager.get().initNewReception(recepcion, lineReception);
        if (aceptarClicked) {
            try {
                repo.save(recepcion);
                loadProd();
            } catch (SQLException e) {
                System.err.println(("Error al crear la recepción: " + e.getMessage()));
            }
        }
    }

    @FXML
    private void onClickAction() throws IOException, SQLException {
        System.out.println("Mostrando Resumen.");
        Reception reception = tableRecept.getSelectionModel().getSelectedItem();
        SceneManager.get().initResumeReception(reception);

    }
}




