package com.dam.gestionalmacendam.repositories.employee;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class EmployeeRepository implements IEmployeeRepository {

    private static EmployeeRepository instance;
    private final ObservableList<Employee> repository = FXCollections.observableArrayList();
    private final DataBaseManager db;

    private EmployeeRepository(DataBaseManager db) {
        this.db = db;
    }

    public static EmployeeRepository getInstance(DataBaseManager db) {
        if (instance == null) {
            instance = new EmployeeRepository(db);
        }
        return instance;
    }

    public DataBaseManager getDb() {
        return db;
    }

    @Override
    public ObservableList<Employee> findAll() throws SQLException {
        String sql = "SELECT * FROM Employee";
        db.open();
        ResultSet resultado = db.select(sql).orElseThrow(() -> new SQLException("Error al obtener todos los empleados."));
        repository.clear();
        while (resultado.next()) {
            repository.add(
                    new Employee(
                            resultado.getString("EIC"),
                            resultado.getString("name"),
                            resultado.getString("surname"),
                            resultado.getString("nif"),
                            resultado.getString("email"),
                            resultado.getString("photo"),
                            resultado.getString("nickname"),
                            resultado.getString("password"),
                            resultado.getBoolean("isManager"),
                            LocalDateTime.parse(resultado.getString("createdAt")),
                            resultado.getBoolean("isActive")

                    )
            );
        }
        db.close();
        return repository;
    }

    @Override
    public Optional<Employee> save(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee (EIC,name,surname,nif,email,photo,nickname,password,isManager,createdAt,isActive) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        db.open();
        db.insert(sql, employee.getEIC(), employee.getName(), employee.getSurname(), employee.getNif(), employee.getEmail(), employee.getPhoto(), employee.getNickName(), employee.getPassword(), employee.isManager(), employee.getCreatedAt().toString(), employee.isActive());
        db.close();
        return Optional.of(employee);
    }

    @Override
    public Optional<Employee> update(UUID uuid, Employee employee) throws SQLException {
        var c = findByUUID(uuid.toString());
        var index = repository.indexOf(c);
        String sql = "UPDATE Employee SET name = ?, surname = ?, nif = ?, email = ?, photo = ?, nickname= ?, password= ?, isManager= ?, createdAt = ?, isActive= ? WHERE EIC = ?";
        db.open();
        db.update(sql, employee.getName(), employee.getSurname(), employee.getNif(), employee.getEmail(), employee.getPhoto(), employee.getNickName(), employee.getPassword(), employee.isManager(), employee.getCreatedAt().toString(), employee.isActive(), employee.getEIC());
        db.close();
        repository.set(index, employee);

        return Optional.of(employee);
    }

    @Override
    public Employee findByUUID(String uuid) throws SQLException {
        var repo = findAll();
        return repo.stream().filter(employee -> employee.getEIC().equals(uuid)).findFirst().orElseThrow(() -> new SQLException("No existe"));
    }
}
