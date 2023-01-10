package com.dam.gestionalmacendam.repositories.LineReception;

import javafx.collections.ObservableList;

import java.sql.SQLException;

interface SerachByReceptionsBelong<T, ID> {
    ObservableList<T> SerachByReceptionsBelong(ID identifier) throws SQLException;
}
