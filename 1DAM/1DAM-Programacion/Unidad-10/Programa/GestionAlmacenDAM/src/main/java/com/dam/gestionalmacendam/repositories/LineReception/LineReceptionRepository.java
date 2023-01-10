package com.dam.gestionalmacendam.repositories.LineReception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LineReceptionRepository implements LineReceptionInterface {

    private static LineReceptionRepository instance;
    private final DataBaseManager dataBaseManager;
    private final ObservableList<LineReception> repository = FXCollections.observableArrayList();

    private LineReceptionRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static LineReceptionRepository getInstance(DataBaseManager dataBaseManager) {
        if (instance == null) {
            instance = new LineReceptionRepository(dataBaseManager);
        }
        return instance;
    }

    public DataBaseManager getDb() {
        return dataBaseManager;
    }

    @Override
    public ObservableList<LineReception> findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineReception";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);
        repository.clear();
        while (result.next()) {
            String RLIC = (result.getString("RLIC"));
            StringProperty articlePIC = new SimpleStringProperty(result.getString("article"));
            IntegerProperty load = new SimpleIntegerProperty(result.getInt("load"));
            DoubleProperty unitPrice = new SimpleDoubleProperty(result.getDouble("unitPrice"));
            DoubleProperty totalPrice = new SimpleDoubleProperty(result.getDouble("totalPrice"));
            StringProperty belongsRecepcion = new SimpleStringProperty(result.getString("belongsReception"));

            LineReception lineReception = new LineReception(RLIC, articlePIC, load, unitPrice, totalPrice, belongsRecepcion);
            repository.add(lineReception);
        }
        dataBaseManager.close();
        return repository;
    }

    @Override
    public Optional<LineReception> save(LineReception lineReception) throws SQLException {

        dataBaseManager.open();
        String query = "Insert into LineReception values (?, ?, ?, ?, ?, ?);";
        ResultSet resultado = dataBaseManager.insert(query,
                lineReception.getRLIC(),
                lineReception.getArticlePIC().get(),
                lineReception.getLoad().get(),
                lineReception.getUnitPrice().get(),
                lineReception.getTotalPrice().get(),
                lineReception.getBelongsRecepcion().get()
        ).orElseThrow(SQLException::new);
        dataBaseManager.close();
        return Optional.of(lineReception);
    }

    @Override
    public Optional<LineReception> update(String rlic, LineReception lineReception) throws SQLException {
        var o = findByUUID(rlic);
        int index = repository.indexOf(o);
        dataBaseManager.open();
        String query = "Update LineReception set article = ? , load = ? " +
                ", unitPrice = ?, totalPrice = ?, BelongsReception = ? where  RLIC = ? ;";
        dataBaseManager.update(query,
                lineReception.getArticlePIC().get(),
                lineReception.getLoad().get(),
                lineReception.getUnitPrice().get(),
                lineReception.getTotalPrice().get(),
                lineReception.getBelongsRecepcion().get(),
                rlic);
        dataBaseManager.close();
        repository.set(index, lineReception);

        return Optional.of(lineReception);
    }

    @Override
    public ObservableList<LineReception> SerachByReceptionsBelong(String identifier) throws SQLException {
        repository.clear();
        dataBaseManager.open();
        String query = "select * from LineReception where belongsReception = ?";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
        while (result.next()) {
            String RLIC = result.getString("RLIC");
            StringProperty articlePIC = new SimpleStringProperty(result.getString("article"));
            IntegerProperty load = new SimpleIntegerProperty(result.getInt("load"));
            DoubleProperty unitPrice = new SimpleDoubleProperty(result.getDouble("unitPrice"));
            DoubleProperty totalPrice = new SimpleDoubleProperty(result.getDouble("totalPrice"));
            StringProperty belongsRecepcion = new SimpleStringProperty(result.getString("belongsReception"));

            LineReception lineReception = new LineReception(RLIC, articlePIC, load, unitPrice, totalPrice, belongsRecepcion);
            repository.add(lineReception);
        }
        dataBaseManager.close();
        return repository;
    }

    public LineReception findByUUID(String rlic) throws SQLException {
        var repo = findAll();
        return repo.stream().filter(lineReception -> lineReception.getRLIC().equals(rlic)).findFirst().orElseThrow(() -> new SQLException("No existe"));
    }
}
