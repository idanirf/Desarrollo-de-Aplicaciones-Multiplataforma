package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class OrderRepositoryTest {

    OrderRepository repository = OrderRepository.getInstance(DataBaseManager.getInstance());
    Order o = new Order("delete", "pagador", 34.54D, Pay.PAYPAL);

    @BeforeEach
    void initDataTest() {

        try {
            repository.save(o);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("objeto ya en el repositorio");
        }
    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM \"Order\" WHERE OIC=?";
        db.open();
        db.delete(query, o.getOIC());
        db.close();
    }


    /**
     * @Test para mostrar todos las Orders del repositorio
     */
    @Test
    void findAllTest() throws SQLException {
        var res = repository.findAll();

        assertAll(
                () -> assertTrue(res.size() > 0),
                () -> assertFalse(res.size() == 0)

        );
    }


    /**
     * @Test buscar una order del repositorio por el UUid de la order del repositorio
     */
    @Test
    void searchByUuidTest() throws SQLException {

        var res = repository.findByUUID(o.getOIC());

        assertAll(
                () -> assertEquals(res.getCustomer().get(), o.getCustomer().get()),
                () -> assertEquals(res.getOIC(), o.getOIC()),
                () -> assertEquals(res.getPrice().get(), o.getPrice().get()),
                () -> assertEquals(res.getMethodPay().get(), o.getMethodPay().get())
        );

    }


    /**
     * @Test salbar Order en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {
        var o1 = repository.findByUUID(o.getOIC());
        assertAll(
                () -> assertEquals(o1.getCustomer().get(), o.getCustomer().get()),
                () -> assertEquals(o1.getOIC(), o.getOIC()),
                () -> assertEquals(o1.getMethodPay().get(), o.getMethodPay().get()),
                () -> assertEquals(o1.getPrice().get(), o.getPrice().get())
        );

    }

    /**
     * @Test cargar los datos nuevos del order en el repositorio
     */
    @Test
    void updateTest() throws SQLException {
        o.setMethodPay(new SimpleObjectProperty<>(Pay.CARD));
        var aux = repository.update(o.getOIC(), o);
        assertAll(
                () -> assertEquals(aux.get().getCustomer().get(), o.getCustomer().get()),
                () -> assertEquals(aux.get().getOIC(), o.getOIC()),
                () -> assertEquals(aux.get().getMethodPay().get(), o.getMethodPay().get()),
                () -> assertEquals(aux.get().getPrice().get(), o.getPrice().get())
        );

    }
}
