package com.dam.gestionalmacendam.controllers.menus;

import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Employee;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Optional;

public class MenuEmployeeController {
    private Stage dialogStage;
    private Employee employee;
    @FXML
    private Label nickname;

    public void setEmployee(Employee employee) {
        this.employee = employee;
        nickname.setText(employee.getNickName());
    }

    @FXML
    public void onSalirAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Está seguro que desea salir?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            alert.close();
        }
    }

    @FXML
    public void onAcercaDe() {
        try {
            SceneManager.get().initAcercaDe();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void openOperationPedidos() {
        try {
            SceneManager.get().initOrderView(dialogStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void openOperationArticles() {
        try {
            SceneManager.get().initArticleView(dialogStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void openOperationClientes() {
        try {
            SceneManager.get().initViewCustomer(dialogStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void openOperationRecepciones() {
        try {
            System.out.println("Entra");
            SceneManager.get().initReception(dialogStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onImportarAction() {
        try {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onExportarAction() {
        try {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void setStage(Stage stage) {
        this.dialogStage = stage;
    }
}
