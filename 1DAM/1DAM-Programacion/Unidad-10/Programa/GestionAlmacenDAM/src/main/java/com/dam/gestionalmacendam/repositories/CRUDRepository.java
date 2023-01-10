package com.dam.gestionalmacendam.repositories;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Optional;

public interface CRUDRepository<T, ID> {

    ObservableList<T> findAll() throws SQLException;

    Optional<T> save(T entity) throws SQLException;

    Optional<T> update(ID id, T entity) throws SQLException;
}
