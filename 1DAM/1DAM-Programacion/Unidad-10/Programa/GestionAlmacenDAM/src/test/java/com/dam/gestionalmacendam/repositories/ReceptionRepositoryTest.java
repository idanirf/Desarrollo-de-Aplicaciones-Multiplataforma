package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class ReceptionRepositoryTest {
    ReceptionRepository repository = ReceptionRepository.getInstance(DataBaseManager.getInstance());
    Reception o = new Reception("delete", "ReceptionTest", "ReceptionTest", 55D);


    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM Reception WHERE RIC=?";
        db.open();
        db.delete(query, o.getRIC());
        db.close();
    }

    /**
     * @Test para mostrar todos las Receptions del repositorio
     */
    @Test
    void findAllTest() throws SQLException {

        repository.save(o);
        ObservableList<Reception> res = repository.findAll();

        assertAll(
                () -> assertTrue(res.size() > 0)

        );
    }

    /**
     * @Test salvar Order en el repositorio
     */
    @Test
    void saveTest() throws SQLException {
        repository.save(o);
        var res1 = repository.findAll();

        assertAll(
                () -> assertEquals(res1.get(0).getRIC(), o.getRIC()),
                () -> assertEquals(res1.get(0).getCarrier().get(), o.getCarrier().get()),
                () -> assertEquals(res1.get(0).getCost().get(), o.getCost().get()),
                () -> assertEquals(res1.get(0).getSupplierName().get(), o.getSupplierName().get())

        );
    }


}