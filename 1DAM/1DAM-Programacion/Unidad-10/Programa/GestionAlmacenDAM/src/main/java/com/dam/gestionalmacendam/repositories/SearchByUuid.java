package com.dam.gestionalmacendam.repositories;

import java.sql.SQLException;

public interface SearchByUuid<T, ID> {
    T findByUuid(ID identifier) throws SQLException;
}
