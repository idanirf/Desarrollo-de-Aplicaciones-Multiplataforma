package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LineReceptionRepositoryTest {
    LineReceptionRepository repository = LineReceptionRepository.getInstance(DataBaseManager.getInstance());
    LineReception linereception = new LineReception("delete", "nuevo", 3, 30D, "pertenece a");


    @BeforeEach
    public void initDataTest() throws SQLException {
        repository.save(linereception);
    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM LineReception WHERE RLIC=?";
        db.open();
        db.delete(query, linereception.getRLIC());
        db.close();
    }

    @Test
    public void findAllTest() throws SQLException {


        ObservableList<LineReception> l = repository.findAll();

        assertAll(
                () -> assertNotNull(l),
                () -> assertFalse(l.size() < 1)
        );
    }

    @Test
    public void saveTest() throws SQLException {
        var res = repository.findByUUID(linereception.getRLIC());
        assertAll(
                () -> assertEquals(res.getRLIC(), linereception.getRLIC()),
                () -> assertEquals(res.getLoad().get(), linereception.getLoad().get()),
                () -> assertEquals(res.getUnitPrice().get(), linereception.getUnitPrice().get()),
                () -> assertEquals(res.getTotalPrice().get(), linereception.getTotalPrice().get())
        );

    }

    @Test
    public void updateTest() throws SQLException {
        linereception.setLoad(8);
        repository.update(linereception.getRLIC(), linereception);
        var li = repository.findByUUID(linereception.getRLIC());

        assertAll(
                () -> assertEquals(li.getRLIC(), linereception.getRLIC()),
                () -> assertEquals(li.getLoad().get(), linereception.getLoad().get()),
                () -> assertEquals(li.getUnitPrice().get(), linereception.getUnitPrice().get()),
                () -> assertEquals(li.getTotalPrice().get(), linereception.getTotalPrice().get())
        );

    }


    @Test
    public void searchByUuidReception() throws SQLException {
        ObservableList<LineReception> res = repository.SerachByReceptionsBelong(linereception.getBelongsRecepcion().get());

        assertAll(
                () -> assertEquals(res.get(0).getRLIC(), linereception.getRLIC()),
                () -> assertEquals(res.get(0).getLoad().get(), linereception.getLoad().get()),
                () -> assertEquals(res.get(0).getUnitPrice().get(), linereception.getUnitPrice().get()),
                () -> assertEquals(res.get(0).getTotalPrice().get(), linereception.getTotalPrice().get())
        );

    }
}
