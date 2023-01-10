package com.dam.gestionalmacendam.controllers.viewEmployee;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class EmpleadosVistaManagerController {
    @FXML
    public TextField buscar;
    EmployeeRepository employeeRepository = EmployeeRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> photo;
    @FXML
    private TableColumn<Employee, String> EIC;
    @FXML
    private TableColumn<Employee, String> nick;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, String> surname;
    @FXML
    private TableColumn<Employee, String> nif;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, Employee> manager;
    @FXML
    private TableColumn<Employee, Employee> createdAt;
    @FXML
    private TableColumn<Employee, Employee> active;

    public static String dateParser(LocalDateTime date, Locale locale) {
        // private String pattern = "dd/MM/yyyy";
        return date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL).withLocale(locale));
    }


    @FXML
    private void initialize() {
        try {
            loadData();
        } catch (SQLException e) {
        }
        photo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoto()));
        nick.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNickName()));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        EIC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEIC()));
        surname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
        nif.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNif()));
        email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        manager.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        setManegerValueFactory();
        createdAt.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        setCellCreateAt();
        active.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        setActiveValueFactory();
    }

    private void setActiveValueFactory() {
        active.setCellFactory(param -> new TableCell<>() {
            @Override
            public void updateItem(Employee item, boolean empty) {
                if (item != null) {
                    CheckBox check = new CheckBox();
                    check.setDisable(true);
                    check.setSelected(item.isActive());
                    setGraphic(check);
                }

            }
        });
    }

    private void setManegerValueFactory() {
        manager.setCellFactory(param -> new TableCell<>() {
            @Override
            public void updateItem(Employee item, boolean empty) {
                if (item != null) {
                    CheckBox check = new CheckBox();
                    check.setDisable(true);
                    check.setSelected(item.isManager());
                    setGraphic(check);
                }

            }
        });
    }

    private void setCellCreateAt() {
        createdAt.setCellFactory(param -> new TableCell<>() {
            @Override
            public void updateItem(Employee item, boolean empty) {
                if (item != null) {
                    Label label = new Label(dateParser(item.getCreatedAt(), new Locale("es", "ES")));
                    setGraphic(label);
                }

            }
        });
    }

    @FXML
    private void onModificarAction() {
        Employee employee = employeeTable.getFocusModel().getFocusedItem();
        System.out.println(employee);
        try {
            SceneManager.get().initModifyEmployee(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void loadData() throws SQLException {
        employeeTable.setItems(employeeRepository.findAll());
    }

    public void onNewEmployee() throws IOException {
        System.out.println("Insertando Empleado");
        Employee employee = new Employee();
        boolean aceptarClicked = SceneManager.get().initNewEmployee(false, employee);
        System.out.println("ESTAS??");
        if (aceptarClicked) {
            try {
                loadData();
            } catch (SQLException e) {
                System.err.println(("Error al cargar los empleados " + e.getMessage()));
            }
        }
    }

    public void findByUUID() throws SQLException {
        String uuid = buscar.getText();
        if (uuid.isEmpty()) {
            loadData();
        } else {
            employeeTable.setItems(employeeRepository.findAll().filtered(x -> x.getNickName()
                    .contains(uuid) || x.getEIC().contains(uuid)));
        }
        employeeTable.refresh();
    }
}
