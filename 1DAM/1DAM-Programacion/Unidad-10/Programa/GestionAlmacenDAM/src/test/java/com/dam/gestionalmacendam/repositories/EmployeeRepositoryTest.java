package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {
    private static final Employee employee = new Employee("pedro", "ramirez", "1", "ramirez@gmail.com", "x", "nick", "password", true, LocalDateTime.now(), true);
    EmployeeRepository repository = EmployeeRepository.getInstance(DataBaseManager.getInstance());

    @BeforeAll
    static void setUp() {

    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM Employee where EIC= ?";
        db.open();
        db.delete(query, employee.getEIC());
        db.close();
    }


    @Test
    void findAllTest() throws SQLException {
        repository.save(employee);
        var res = repository.findAll().stream().toList();
        assertAll(
                () -> assertTrue(res.size() > 0)
        );
    }

    @Test
    void saveTest() throws SQLException {
        var aux = repository.save(employee);
        assertAll(
                () -> assertEquals(aux.get().getName(), employee.getName()),
                () -> assertEquals(aux.get().getSurname(), employee.getSurname()),
                () -> assertEquals(aux.get().getNif(), employee.getNif()),
                () -> assertEquals(aux.get().getEmail(), employee.getEmail()),
                () -> assertEquals(aux.get().getPhoto(), employee.getPhoto()),
                () -> assertEquals(aux.get().getNickName(), employee.getNickName()),
                () -> assertEquals(aux.get().getPassword(), employee.getPassword()),
                () -> assertEquals(aux.get().isManager(), employee.isManager())
        );
    }

    @Test
    void updateTest() throws SQLException {
        repository.save(employee);
        employee.setSurname(new SimpleStringProperty("perez"));
        var aux = repository.update(UUID.fromString(employee.getEIC()), employee);

        assertAll(
                () -> assertEquals(aux.get().getName(), employee.getName()),
                () -> assertEquals(aux.get().getSurname(), employee.getSurname()),
                () -> assertEquals(aux.get().getNif(), employee.getNif()),
                () -> assertEquals(aux.get().getEmail(), employee.getEmail()),
                () -> assertEquals(aux.get().getPhoto(), employee.getPhoto()),
                () -> assertEquals(aux.get().getNickName(), employee.getNickName()),
                () -> assertEquals(aux.get().getPassword(), employee.getPassword()),
                () -> assertEquals(aux.get().isManager(), employee.isManager())
        );
    }

    @Test
    void findByUUIDTest() throws SQLException {
        repository.save(employee);
        var aux = repository.findByUUID(employee.getEIC());

        assertAll(
                () -> assertEquals(aux.getName(), employee.getName()),
                () -> assertEquals(aux.getSurname(), employee.getSurname()),
                () -> assertEquals(aux.getNif(), employee.getNif()),
                () -> assertEquals(aux.getEmail(), employee.getEmail()),
                () -> assertEquals(aux.getPhoto(), employee.getPhoto()),
                () -> assertEquals(aux.getNickName(), employee.getNickName()),
                () -> assertEquals(aux.getPassword(), employee.getPassword()),
                () -> assertEquals(aux.isManager(), employee.isManager())
        );


    }
}