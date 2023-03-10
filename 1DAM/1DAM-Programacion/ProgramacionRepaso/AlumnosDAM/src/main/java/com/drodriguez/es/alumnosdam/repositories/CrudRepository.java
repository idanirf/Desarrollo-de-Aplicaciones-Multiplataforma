package com.drodriguez.es.alumnosdam.repositories;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    ObservableList<T> findAll() throws SQLException;


    Optional<T> findById(ID id) throws SQLException;


    Optional<T> save(T entity) throws SQLException;


    Optional<T> update(ID id,T entity) throws SQLException;


    void delete(T entity) throws SQLException;
}
