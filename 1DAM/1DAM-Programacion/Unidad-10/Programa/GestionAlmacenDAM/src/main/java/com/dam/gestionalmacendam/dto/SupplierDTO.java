package com.dam.gestionalmacendam.dto;


import com.dam.gestionalmacendam.models.Supplier;

import java.io.Serializable;

public class SupplierDTO implements Serializable {

   private final String sic;

   private final String nameSupplier;

   private final String direction;

   private final String telephoneNumber;

   private final String email;


    public SupplierDTO(Supplier supplier) {
        this.sic = supplier.getSIC();
        this.nameSupplier = supplier.nameSupplierProperty().get();
        this.direction = supplier.directionProperty().get();
        this.telephoneNumber = supplier.telephoneNumberProperty().get();
        this.email = supplier.emailProperty().get();
    }

    public Supplier fromDTO(){return new Supplier(sic, nameSupplier, direction, telephoneNumber,email);}
}
