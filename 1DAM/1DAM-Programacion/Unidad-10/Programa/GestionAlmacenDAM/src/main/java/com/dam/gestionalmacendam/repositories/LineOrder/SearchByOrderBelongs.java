package com.dam.gestionalmacendam.repositories.LineOrder;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SearchByOrderBelongs<LineOrder, String> {
    ObservableList<LineOrder> searchByUuidOrder(String identifier) throws SQLException;
}
