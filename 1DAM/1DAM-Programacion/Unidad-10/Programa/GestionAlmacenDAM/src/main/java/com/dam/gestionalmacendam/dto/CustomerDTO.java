package com.dam.gestionalmacendam.dto;

import com.dam.gestionalmacendam.models.Customer;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CustomerDTO implements Serializable {

   private final String cic;

   private final String name;

   private final String surname;

   private final String cif;

   private final String direction;

   private final String nickname;

   private final String password;

   private final String telephoneNumber;

   private final String email;

   private final String photo;

   private final LocalDateTime createdAt;

   private final Boolean isActive;

    public CustomerDTO(Customer customer) {
        this.cic = customer.getCIC();
        this.name = customer.getName();
        this.surname =customer.getSurname();
        this.cif = customer.getCif();
        this.direction = customer.getDirection();
        this.nickname = customer.getNickName();
        this.password = customer.getPassword();
        this.telephoneNumber = customer.getTelephoneNumber();
        this.email = customer.getEmail();
        this.photo = customer.getPhoto();
        this.createdAt = customer.getCreatedAt();
        this.isActive = customer.isActive();
    }

    public Customer fromDTO(){return new Customer(cic,name,surname,cif,direction,nickname,password,telephoneNumber,email,photo,createdAt,isActive);}

}
