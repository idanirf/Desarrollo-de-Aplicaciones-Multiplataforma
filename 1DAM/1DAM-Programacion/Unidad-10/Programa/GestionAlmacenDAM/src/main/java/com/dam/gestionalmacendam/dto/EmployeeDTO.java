package com.dam.gestionalmacendam.dto;

import com.dam.gestionalmacendam.models.Employee;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EmployeeDTO implements Serializable {

   private final String eic;

   private final String name;

   private final String surname;

   private final String nif;

   private final String email;

   private final String photo;

    private final String nickName;
    private final String password;

    private final Boolean isManager;
   private final LocalDateTime createdAt;

   private final Boolean isActive;

    public EmployeeDTO(Employee employee) {
        this.eic = employee.getEIC();
        this.name = employee.getName();
        this.surname =employee.getSurname();
        this.nif = employee.getNif();
        this.nickName = employee.getNickName();
        this.password = employee.getPassword();
        this.email = employee.getEmail();
        this.photo = employee.getPhoto();
        this.createdAt = employee.getCreatedAt();
        this.isActive = employee.isActive();
        this.isManager = employee.isManager();
    }

    public Employee fromDTO(){return new Employee(eic,name,surname,nif,email,photo,nickName,password,isManager,createdAt,isActive);}

}
