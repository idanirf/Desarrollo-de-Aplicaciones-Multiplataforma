package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LineOrderRepositoryText {
    LineOrderRepository repository = LineOrderRepository.getInstance(DataBaseManager.getInstance());
    LineOrder lineOrder = new LineOrder("delete", "lineaOrder prueva1", 1, 1.00, "no pertenece");

    @BeforeEach
    public void initDataTest() throws SQLException {
        repository.save(lineOrder);
    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM LineOrder WHERE OLIC=?";
        db.open();
        db.delete(query, lineOrder.getOLIC());
        db.close();
    }

    @Test
    public void findAllTest() throws SQLException {


        ObservableList<LineOrder> l = repository.findAll();

        assertAll(
                () -> assertNotNull(l),
                () -> assertFalse(l.size() < 1)
        );
    }

    @Test
    public void saveTest() throws SQLException {
        var res = repository.findByUuid(lineOrder.getOLIC());
        assertAll(
                () -> assertEquals(res.getOLIC(), lineOrder.getOLIC()),
                () -> assertEquals(res.getLoad().get(), lineOrder.getLoad().get()),
                () -> assertEquals(res.getUnitPrice().get(), lineOrder.getUnitPrice().get()),
                () -> assertEquals(res.getTotalPrice().get(), lineOrder.getTotalPrice().get())
        );


    }

    @Test
    public void updateTest() throws SQLException {
        lineOrder.setLoad(8);
        repository.update(lineOrder.getOLIC(), lineOrder);
        var li = repository.findByUuid(lineOrder.getOLIC());

        assertAll(
                () -> assertTrue(li.getOLIC().equalsIgnoreCase(lineOrder.getOLIC())),
                () -> assertEquals(li.getLoad().get(), lineOrder.getLoad().get())
        );
    }

    @Test
    public void findByUuidTest() throws SQLException {
        var res = repository.findByUuid(lineOrder.getOLIC());

        assertAll(
                () -> assertEquals(res.getOLIC(), lineOrder.getOLIC()),
                () -> assertEquals(res.getLoad().get(), lineOrder.getLoad().get()),
                () -> assertEquals(res.getUnitPrice().get(), lineOrder.getUnitPrice().get()),
                () -> assertEquals(res.getTotalPrice().get(), lineOrder.getTotalPrice().get())
        );
    }

    @Test
    public void searchByUuidOrder() throws SQLException {
        var res = repository.searchByUuidOrder(lineOrder.getBelongsOrder().get());

        assertAll(
                () -> assertEquals(res.get(0).getOLIC(), lineOrder.getOLIC()),
                () -> assertEquals(res.get(0).getLoad().get(), lineOrder.getLoad().get()),
                () -> assertEquals(res.get(0).getUnitPrice().get(), lineOrder.getUnitPrice().get()),
                () -> assertEquals(res.get(0).getTotalPrice().get(), lineOrder.getTotalPrice().get())
        );

    }


}
