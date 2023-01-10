package com.dam.gestionalmacendam.repositories.Order;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class OrderRepository implements ICRUDOrder {
    private static OrderRepository instance;
    private final ObservableList<Order> repository = FXCollections.observableArrayList();
    private final DataBaseManager bbdd;

    private OrderRepository(DataBaseManager bbdd) {
        this.bbdd = bbdd;
    }

    public static OrderRepository getInstance(DataBaseManager bbdd) {
        if (instance == null) {
            instance = new OrderRepository(bbdd);
        }
        return instance;
    }

    public DataBaseManager getDb() {
        return bbdd;
    }

    @Override
    public ObservableList<Order> findAll() throws SQLException {
        String sql = "SELECT * FROM \"Order\"";
        bbdd.open();
        ResultSet resultado = bbdd.select(sql).orElseThrow(() -> new SQLException("Se ha producido un error obteniendo los datos"));
        repository.clear();
        while (resultado.next()) {
            repository.add(
                    new Order(
                            resultado.getString("OIC"),
                            resultado.getString("Customer"),
                            resultado.getDouble("price"),
                            Pay.valueOf(resultado.getString("Pay"))

                    )
            );
        }
        bbdd.close();
        return repository;
    }

    @Override
    public Optional<Order> save(Order order) throws SQLException {
        String sql = "INSERT INTO \"Order\" values (?, ?, ?, ?);";
        bbdd.open();

        bbdd.insert(sql, order.getOIC(),
                order.getCustomer().get(),
                order.getPrice().get(),
                order.getMethodPay().get().toString());

        bbdd.close();
        return Optional.of(order);
    }

    @Override
    public Optional<Order> update(String uuid, Order order) throws SQLException {
        var o = findByUUID(uuid);
        int index = repository.indexOf(o);
        String sql = "UPDATE \"Order\" SET Customer = ?, Price = ?, Pay = ? WHERE OIC = ? ";
        bbdd.open();
        bbdd.update(sql,
                order.getCustomer().get(),
                order.getPrice().get(),
                order.getMethodPay().get(),
                uuid);
        repository.set(index, order);
        return Optional.of(order);
    }

    public Order findByUUID(String OIC) throws SQLException {
        var repo = findAll();
        return repo.stream().filter(order -> order.getOIC().equals(OIC)).findFirst().orElseThrow(() -> new SQLException("No existe"));
    }
}
