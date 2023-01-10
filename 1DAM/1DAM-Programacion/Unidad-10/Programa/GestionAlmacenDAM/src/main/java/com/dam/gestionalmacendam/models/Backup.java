package com.dam.gestionalmacendam.models;

import com.dam.gestionalmacendam.dto.*;

import java.util.List;

public class Backup {

    List<ArticleDTO>productos;
    List<CustomerDTO> customers;
    List<EmployeeDTO>employees;
    List<LineOrderDTO>lineOrders;
    List<LineReceptionDTO>lineReceptions;
    List<OrderDTO>orders;
    List<ReceptionDTO>receptions;
    List<SupplierDTO>suppliers;


    public Backup(List<ArticleDTO> productos, List<CustomerDTO> customers, List<EmployeeDTO> employees, List<LineOrderDTO> lineOrders, List<LineReceptionDTO> lineReceptions, List<OrderDTO> orders, List<ReceptionDTO> receptions, List<SupplierDTO> suppliers) {
        this.productos = productos;
        this.customers = customers;
        this.employees = employees;
        this.lineOrders = lineOrders;
        this.lineReceptions = lineReceptions;
        this.orders = orders;
        this.receptions = receptions;
        this.suppliers = suppliers;
    }
}
