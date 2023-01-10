package com.dam.gestionalmacendam.repositories.Reception;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Optional;

public interface ReceptionInterface<Reception, String> {
    ObservableList<Reception> findAll() throws SQLException;

    Optional<Reception> save(Reception entity) throws SQLException;
}
