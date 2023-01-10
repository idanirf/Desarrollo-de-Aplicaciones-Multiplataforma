package com.dam.gestionalmacendam.controllers.viewSupplier;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Optional;

public class SupplierVistaController {
    SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());
    private Stage stage;

    @FXML
    private TableColumn<Supplier, String> colDireci;

    @FXML
    private TableColumn<Supplier, String> colEmail;

    @FXML
    private TableColumn<Supplier, String> colName;

    @FXML
    private TableColumn<Supplier, String> colSIC;

    @FXML
    private TableColumn<Supplier, String> colTelefo;

    @FXML
    private TableView<Supplier> suplierTable;

    @FXML
    private TextField textAreaSIC;

    public void setStage(Stage stage) {
        this.stage = stage;
        initView();
    }

    @FXML
    void findByUUID(ActionEvent event) throws SQLException {

        String name = textAreaSIC.getText();
        if (name.isEmpty()) {
            initView();
        } else {
            suplierTable.setItems(repository.findAll().filtered(x -> x.getNameSupplier()
                    .toLowerCase().contains(name) || x.getSIC().toUpperCase().contains(name)));
        }
        suplierTable.refresh();
    }

    @FXML
    void onModificarAction(ActionEvent event) {
        var supplier = suplierTable.getFocusModel().getFocusedItem();
        System.out.println(supplier);
        try {
            SceneManager.get().initModificarSuplier(supplier);
        } catch (Exception e) {
            System.out.println("No se ha seleccionado el empleado");
            e.printStackTrace();
        }

    }

    @FXML
    void onNewSuplier(ActionEvent event) {
        System.out.println("intentando cargar nuevo supplier ");
        try {
            SceneManager.get().initNewSuplier();
            suplierTable.refresh();
        } catch (Exception e) {
            System.out.println("no se ha podido cargar ");
        }

    }

    private void initView() {
        try {
            loadData();
        } catch (SQLException e) {
            System.out.println("error al cargar datos");
        }
        colDireci.setCellValueFactory(cellData -> cellData.getValue().directionProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameSupplierProperty());
        colSIC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSIC()));
        colTelefo.setCellValueFactory(cellData -> cellData.getValue().telephoneNumberProperty());

    }

    @FXML
    private void loadData() throws SQLException {
        suplierTable.setItems(repository.findAll());
    }

    @FXML
    void errorDeBusqueda() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Suplier selecionado no existe o incorrecto");
        alert.setContentText("No ha selecionado ningun suplier o suplier selecionado no existe o incorrecto");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        } else {
            alert.close();
        }
    }

    public void seleccionarAction(MouseEvent mouseEvent) {
        Supplier o = suplierTable.getSelectionModel().getSelectedItem();
        textAreaSIC.setText(o.getSIC());
    }

    public void findByUUID() throws SQLException {
        String name = textAreaSIC.getText();
        if (name.isEmpty()) {
            errorDeBusqueda();
        } else {
            suplierTable.setItems(repository.findAll().filtered(x -> x.getSIC().contains(name)));
        }
        suplierTable.refresh();
    }
}
