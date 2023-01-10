package com.dam.gestionalmacendam.controllers.BackUp;

import com.dam.gestionalmacendam.dto.*;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Backup;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import com.dam.gestionalmacendam.servicies.Storage;

import java.sql.SQLException;
import java.util.stream.Collectors;


public class BackUpController {

     ArticleRepository articleRepository = ArticleRepository.getInstance(DataBaseManager.getInstance()) ;
     CustomerRepository customerRepository = CustomerRepository.getInstance(DataBaseManager.getInstance());
    EmployeeRepository employeeRepository = EmployeeRepository.getInstance(DataBaseManager.getInstance());
     LineOrderRepository lineOrderRepository = LineOrderRepository.getInstance(DataBaseManager.getInstance());
     LineReceptionRepository lineReceptionRepository = LineReceptionRepository.getInstance(DataBaseManager.getInstance());
     OrderRepository  orderRepository = OrderRepository.getInstance(DataBaseManager.getInstance());
     ReceptionRepository receptionRepository = ReceptionRepository.getInstance(DataBaseManager.getInstance());
     SupplierRepository supplierRepository = SupplierRepository.getInstance(DataBaseManager.getInstance());

    Storage backUpStorage = Storage.getInstance();

    public BackUpController() {

    }

    public void exportarDatos() throws SQLException {
        System.out.println("Exportando datos a fichero de Backup...");
        var res = backUpStorage.save(comebackBackup());
        if (res) {
            System.out.println("Datos exportados con éxito en: " + backUpStorage.getStoragePath());
        } else {
            System.out.println("Ha existido un problema al exportar los datos");
        }
    }

    public Backup comebackBackup() throws SQLException {
        var productos = articleRepository.findAll().stream().map(ArticleDTO::new).collect(Collectors.toList());
        var customers = customerRepository.findAll().stream().map(CustomerDTO::new).collect(Collectors.toList());
        var employees = employeeRepository.findAll().stream().map(EmployeeDTO::new).collect(Collectors.toList());
        var lineOrders = lineOrderRepository.findAll().stream().map(LineOrderDTO::new).collect(Collectors.toList());
        var lineReceptions = lineReceptionRepository.findAll().stream().map(LineReceptionDTO::new).collect(Collectors.toList());
        var orders = orderRepository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
        var receptions = receptionRepository.findAll().stream().map(ReceptionDTO::new).collect(Collectors.toList());
        var suplliers = supplierRepository.findAll().stream().map(SupplierDTO::new).collect(Collectors.toList());
        Backup backup = new Backup(productos,customers,employees, lineOrders,lineReceptions,orders,receptions,suplliers);
        return backup;
    }

}
