package com.dam.gestionalmacendam.dto;

import com.dam.gestionalmacendam.models.Reception;

import java.io.Serializable;

public class ReceptionDTO implements Serializable {

   private final String ric;

   private final String supplierName;

   private final String carrier;

   private final Double cost;


    public ReceptionDTO(Reception reception) {
        this.ric = reception.getRIC();
        this.supplierName = reception.getSupplierName().get();
        this.carrier = reception.getCarrier().get();
        this.cost = reception.getCost().get();

    }


    public Reception fromDTO(){return new Reception(ric,supplierName,carrier,cost);}
}
