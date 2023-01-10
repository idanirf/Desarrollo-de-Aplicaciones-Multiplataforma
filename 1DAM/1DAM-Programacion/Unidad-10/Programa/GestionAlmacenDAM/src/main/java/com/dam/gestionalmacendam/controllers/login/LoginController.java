package com.dam.gestionalmacendam.controllers.login;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    CustomerRepository customerRepository = CustomerRepository.getInstance(DataBaseManager.getInstance());
    EmployeeRepository employeeRepository = EmployeeRepository.getInstance(DataBaseManager.getInstance());

    private Stage stage;

    @FXML
    private TextField txtNick;
    @FXML
    private TextField txtPassword;

    @FXML
    private void btnLoginClick(ActionEvent event) {
        System.out.println("Login");
        accionLogin();
    }

    @FXML
    private void btnNewUserClick(ActionEvent event) throws IOException {
        System.out.println("Nuevo Usuario");
        var scene = SceneManager.get();
        scene.initRegister();
    }

    public void setDialogStage(Stage stage) {
        this.stage = stage;
    }

    private void accionLogin() {
        String nick = txtNick.getText();
        String password = txtPassword.getText();

        System.out.println("Nick: " + nick);
        System.out.println("Password: " + password);

        if (nick.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Faltan datos...");
            alert.setContentText("Debe introducir el nick y la contraseña.");
            txtNick.requestFocus();
            alert.showAndWait();
        } else {
            loggear(nick, password);
        }

    }

    private void loggear(String nick, String password) {
        try {
            var listCustomer = customerRepository.findAll();
            var customer = listCustomer.stream().filter(c -> c.getNickName().equals(nick) && c.getPassword().equals(password)).findFirst();

            var listEmployee = employeeRepository.findAll();
            var employee = listEmployee.stream().filter(e -> e.getNickName().equals(nick) && e.getPassword().equals(password)).findFirst();


            if (customer.isEmpty() && employee.isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error de usuario");
                error.setContentText("El usuario no existe");
                error.showAndWait();
            } else {
                if (!customer.isEmpty()) {
                    System.out.println("Usuario cliente logueado.");
                    var scene = SceneManager.get();
                    scene.initMainCustomer(stage, customer.get());
                } else {
                    System.out.println("Usuario empleado logueado.");
                    var scene = SceneManager.get();
                    if (employee.get().isManager()) {
                        scene.initAPPManager(stage, employee.get());
                    } else {
                        scene.initAPPEmployee(stage, employee.get());
                    }

                }

            }


        } catch (SQLException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error no hay usuarios");
            error.setContentText("No se ha podido encontrar ningún usuario.");
            error.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
