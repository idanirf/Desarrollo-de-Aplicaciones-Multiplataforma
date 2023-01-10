package com.dam.gestionalmacendam.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Reception {
    private String RIC = UUID.randomUUID().toString();
    private StringProperty supplierName;
    private StringProperty carrier;
    private DoubleProperty cost;

    public Reception() {
    }

    public Reception(String ric, String supplierName, String carrier, Double cost) {
        this.RIC = ric;
        this.supplierName = new SimpleStringProperty(supplierName);
        this.carrier = new SimpleStringProperty(carrier);
        this.cost = new SimpleDoubleProperty(cost);
    }

    public Reception(String supplierName, String carrier, Double cost) {
        this.supplierName = new SimpleStringProperty(supplierName);
        this.carrier = new SimpleStringProperty(carrier);
        this.cost = new SimpleDoubleProperty(cost);
    }

    @Override
    public String toString() {
        return "Reception{" +
                "RIC=" + RIC +
                ", supplier=" + supplierName +
                ", carrier='" + carrier + '\'' +
                ", cost=" + cost +
                '}';
    }

}
