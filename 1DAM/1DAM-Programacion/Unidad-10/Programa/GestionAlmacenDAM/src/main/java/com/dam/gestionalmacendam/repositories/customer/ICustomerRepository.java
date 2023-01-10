package com.dam.gestionalmacendam.repositories.customer;

import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.repositories.CRUDRepository;

import java.sql.SQLException;
import java.util.UUID;

public interface ICustomerRepository extends CRUDRepository<Customer, UUID> {

    Customer findByUUID(String uuid) throws SQLException;
}
