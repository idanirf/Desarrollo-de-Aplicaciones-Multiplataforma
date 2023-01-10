package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    private static final Customer customer = new Customer("pedro", "ramos", "1", "avaux", "nick", "password", "3332211",
            "ramos@gmail.com", "x", LocalDateTime.now(), true);
    CustomerRepository repository = CustomerRepository.getInstance(DataBaseManager.getInstance());

    @BeforeAll
    static void setUp() {

    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM customer where CIC= ?";
        db.open();
        db.delete(query, customer.getCIC());
        db.close();
    }


    @Test
    void findAllTest() throws SQLException {
        repository.save(customer);
        var res = repository.findAll().stream().toList();
        assertAll(
                () -> assertTrue(res.size() > 0)
        );
    }

    @Test
    void saveTest() throws SQLException {
        var aux = repository.save(customer);
        assertAll(
                () -> assertEquals(aux.get().getName(), customer.getName()),
                () -> assertEquals(aux.get().getSurname(), customer.getSurname()),
                () -> assertEquals(aux.get().getDirection(), customer.getDirection()),
                () -> assertEquals(aux.get().getNickName(), customer.getNickName()),
                () -> assertEquals(aux.get().getPassword(), customer.getPassword()),
                () -> assertEquals(aux.get().getCIC(), customer.getCIC()),
                () -> assertEquals(aux.get().getTelephoneNumber(), customer.getTelephoneNumber())
        );
    }

    @Test
    void updateTest() throws SQLException {
        repository.save(customer);
        customer.setSurname(new SimpleStringProperty("perez"));
        var aux = repository.update(UUID.fromString(customer.getCIC()), customer);

        assertAll(
                () -> assertEquals(aux.get().getName(), customer.getName()),
                () -> assertEquals(aux.get().getSurname(), customer.getSurname()),
                () -> assertEquals(aux.get().getDirection(), customer.getDirection()),
                () -> assertEquals(aux.get().getNickName(), customer.getNickName()),
                () -> assertEquals(aux.get().getPassword(), customer.getPassword()),
                () -> assertEquals(aux.get().getCIC(), customer.getCIC()),
                () -> assertEquals(aux.get().getTelephoneNumber(), customer.getTelephoneNumber())
        );
    }

    @Test
    void findByUUIDTest() throws SQLException {
        repository.save(customer);
        var aux = repository.findByUUID(customer.getCIC());

        assertAll(
                () -> assertEquals(aux.getName(), customer.getName()),
                () -> assertEquals(aux.getSurname(), customer.getSurname()),
                () -> assertEquals(aux.getDirection(), customer.getDirection()),
                () -> assertEquals(aux.getCIC(), customer.getCIC()),
                () -> assertEquals(aux.getTelephoneNumber(), customer.getTelephoneNumber())
        );

    }

}