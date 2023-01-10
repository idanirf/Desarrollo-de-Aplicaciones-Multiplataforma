package com.dam.gestionalmacendam.repositories.customer;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class CustomerRepository implements ICustomerRepository {

    private static CustomerRepository instance;
    private final ObservableList<Customer> repository = FXCollections.observableArrayList();
    private final DataBaseManager db;

    private CustomerRepository(DataBaseManager db) {
        this.db = db;
    }

    public static CustomerRepository getInstance(DataBaseManager db) {
        if (instance == null) {
            instance = new CustomerRepository(db);
        }
        return instance;
    }

    public DataBaseManager getDb() {
        return db;
    }

    @Override
    public ObservableList<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM Customer";
        db.open();
        ResultSet resultado = db.select(sql).orElseThrow(() -> new SQLException("Error al obtener todos los clientes."));
        repository.clear();
        while (resultado.next()) {
            repository.add(
                    new Customer(
                            resultado.getString("CIC"),
                            resultado.getString("name"),
                            resultado.getString("surname"),
                            resultado.getString("cif"),
                            resultado.getString("direction"),
                            resultado.getString("nickname"),
                            resultado.getString("password"),
                            resultado.getString("telephoneNumber"),
                            resultado.getString("email"),
                            resultado.getString("photo"),
                            LocalDateTime.parse(resultado.getString("createdAt")),
                            resultado.getBoolean("isActive")

                    )
            );
        }
        db.close();
        return repository;
    }

    @Override
    public Optional<Customer> save(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (CIC,name,surname,cif,direction,nickname,password,telephoneNumber,email,photo,createdAt,isActive) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        db.open();
        db.insert(sql, customer.getCIC(), customer.getName(), customer.getSurname(), customer.getCif(), customer.getDirection(), customer.getNickName(), customer.getPassword(), customer.getTelephoneNumber(), customer.getEmail(), customer.getPhoto(), customer.getCreatedAt().toString(), customer.isActive());
        db.close();
        return Optional.of(customer);
    }

    @Override
    public Optional<Customer> update(UUID uuid, Customer customer) throws SQLException {
        var c = findByUUID(uuid.toString());

        var index = repository.indexOf(c);

        String sql = "UPDATE Customer SET name = ?, surname = ?, cif = ?, direction = ?, nickname= ?, password= ?, telephoneNumber= ?, email = ?, photo = ?, createdAt = ?, isActive= ? WHERE CIC = ?";
        db.open();
        db.update(sql, customer.getName(), customer.getSurname(), customer.getCif(), customer.getDirection(), customer.getNickName(), customer.getPassword(), customer.getTelephoneNumber(), customer.getEmail(), customer.getPhoto(), customer.getCreatedAt(), customer.isActive(), customer.getCIC());
        db.close();
        repository.set(index, customer);

        return Optional.of(customer);
    }


    @Override
    public Customer findByUUID(String uuid) throws SQLException {
        var repo = findAll();
        return repo.stream().filter(customer -> customer.getCIC().equals(uuid)).findFirst().orElseThrow(() -> new SQLException("No existe"));
    }
}
