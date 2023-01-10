package com.dam.gestionalmacendam.repositories.Reception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Reception;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReceptionRepository implements ReceptionInterface<Reception, String> {
    private static ReceptionRepository instance;
    private final ObservableList<Reception> repository = FXCollections.observableArrayList();
    private final DataBaseManager dataBaseManager;


    private ReceptionRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static ReceptionRepository getInstance(DataBaseManager dataBaseManager) {
        if (instance == null) {
            instance = new ReceptionRepository(dataBaseManager);
        }
        return instance;
    }

    public DataBaseManager getDb() {
        return dataBaseManager;
    }

    @Override
    public ObservableList<Reception> findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from Reception";
        ResultSet result = dataBaseManager.select(query).orElseThrow(() -> new SQLException("Error al obtener las recepciones."));
        repository.clear();
        while (result.next()) {
            repository.add(
                    new Reception(
                            result.getString("RIC"),
                            result.getString("Supplier"),
                            result.getString("Carrier"),
                            result.getDouble("Cost")
                    ));

        }
        dataBaseManager.close();
        return repository;
    }


    @Override
    public Optional<Reception> save(Reception reception) throws SQLException {
        dataBaseManager.open();
        String query = "Insert into Reception(RIC, Supplier, Carrier, Cost) values (?, ?, ?, ?);";
        dataBaseManager.insert(query,
                reception.getRIC(),
                reception.getSupplierName().get(),
                reception.getCarrier().get(),
                reception.getCost().get()
        );
        dataBaseManager.close();
        return Optional.of(reception);
    }


}

