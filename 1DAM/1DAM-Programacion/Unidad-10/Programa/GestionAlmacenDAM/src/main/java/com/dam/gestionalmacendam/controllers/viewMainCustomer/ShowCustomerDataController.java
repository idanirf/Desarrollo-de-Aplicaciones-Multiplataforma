package com.dam.gestionalmacendam.controllers.viewMainCustomer;

import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ShowCustomerDataController {
    @FXML
    private ImageView img;
    @FXML
    private Label txtName;
    @FXML
    private Label txtSurname;
    @FXML
    private Label txtCif;
    @FXML
    private Label txtDirection;
    @FXML
    private Label txtNick;
    @FXML
    private Label txtPhone;
    @FXML
    private Label txtEmail;
    private Customer customer;
    private Stage stage;

    public void btnModAction(ActionEvent actionEvent) {
        try {
            SceneManager.get().initViewModifyDataCustomer(customer, stage);
            initData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        initData();
    }

    public void initData() {
        Image image = new Image(new File(customer.getPhoto()).toURI().toString());
        img.setImage(image);
        txtName.setText(customer.getName());
        txtSurname.setText(customer.getSurname());
        txtCif.setText(customer.getCif());
        txtDirection.setText(customer.getDirection());
        txtNick.setText(customer.getNickName());
        txtPhone.setText(customer.getTelephoneNumber());
        txtEmail.setText(customer.getEmail());
    }
}
