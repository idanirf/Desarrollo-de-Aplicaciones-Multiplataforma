package com.dam.gestionalmacendam.repositories.employee;

import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.repositories.CRUDRepository;

import java.sql.SQLException;
import java.util.UUID;

public interface IEmployeeRepository extends CRUDRepository<Employee, UUID> {
    Employee findByUUID(String uuid) throws SQLException;
}
