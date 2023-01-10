package com.dam.gestionalmacendam.repositories.Articles;


import java.sql.SQLException;

public interface SearchByName<T, ID> {
    T findByName(ID name) throws SQLException;
}