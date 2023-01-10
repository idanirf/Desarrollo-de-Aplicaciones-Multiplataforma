package com.dam.gestionalmacendam.repositories.supplier;

import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.CRUDRepository;

import java.sql.SQLException;

public interface ICRUDSupplier extends CRUDRepository<Supplier, String> {
    Supplier findByUUID(String uuid) throws SQLException;
}
