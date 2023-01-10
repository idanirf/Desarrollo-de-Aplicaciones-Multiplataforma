package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierRepositoryTest {
    SupplierRepository supplierRepository = SupplierRepository.getInstance(DataBaseManager.getInstance());
    Supplier supplierTest = new Supplier(UUID.randomUUID().toString(), "MESATABLA S,L,",
            "Calle Valencia N12", "678908765", "mesatabala.valencia@mesatabla.com");

    @BeforeEach
    void setDown() throws SQLException {
        var db = supplierRepository.getBbdd();
        String query = "DELETE FROM SUPPLIER WHERE nameSupplier=?";
        db.open();
        db.delete(query, supplierTest.getNameSupplier());
        db.close();
    }


    @Test
    void findAll() throws SQLException {
        supplierRepository.save(supplierTest);
        var list1 = supplierRepository.findAll();
        assertAll(
                () -> assertEquals(list1.size(), 1),
                () -> assertEquals(list1.get(0).getSIC(), supplierTest.getSIC()),
                () -> assertEquals(list1.get(0).getNameSupplier(), supplierTest.getNameSupplier())
        );
    }

    @Test
    void findaByUUID() throws SQLException {
        supplierRepository.save(supplierTest);
        var res = supplierRepository.findByUUID(supplierTest.getSIC());
        assertAll(
                () -> assertEquals(res.getSIC(), supplierTest.getSIC()),
                () -> assertEquals(res.getSIC(), supplierTest.getSIC())
        );
    }

    @Test
    void findByUUIDNoExists() throws SQLException {
        Exception thrown = assertThrows(Exception.class, () -> supplierRepository.findByUUID("1"));
        assertTrue(thrown.getMessage().contains("No existe ningún proveedor con ese SIC"));
    }

    @Test
    void save() throws SQLException {
        var resultado = supplierRepository.save(supplierTest);
        assertAll(
                () -> assertEquals(resultado.get().getNameSupplier(), supplierTest.getNameSupplier()),
                () -> assertTrue(resultado.isPresent())
        );
    }

    @Test
    void update() throws SQLException {
        supplierRepository.save(supplierTest);
        supplierTest.setDirection("Calle Madrid N18 Madrid");
        supplierTest.setTelephoneNumber("605203547");
        supplierTest.setEmail("sillafactory.spain@sillafactory.com");
        var resultado = supplierRepository.update(supplierTest.getSIC(), supplierTest);

        assertAll(
                () -> assertEquals(resultado.get().getDirection(), supplierTest.getDirection()),
                () -> assertTrue(resultado.isPresent())
        );
    }
}
