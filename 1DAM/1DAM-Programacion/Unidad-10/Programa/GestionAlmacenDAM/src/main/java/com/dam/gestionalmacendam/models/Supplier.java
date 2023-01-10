package com.dam.gestionalmacendam.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class Supplier {
    private String SIC = UUID.randomUUID().toString();
    private StringProperty nameSupplier;
    private StringProperty direction;
    private StringProperty telephoneNumber;
    private StringProperty email;


    public Supplier(String nameSupplier, String direction, String telephonNumber, String email) {
        this.SIC = SIC;
        this.nameSupplier = new SimpleStringProperty(nameSupplier);
        this.direction = new SimpleStringProperty(direction);
        this.telephoneNumber = new SimpleStringProperty(telephonNumber);
        this.email = new SimpleStringProperty(email);
    }

    //En este constructor se agrega el SIC
    public Supplier(String SIC, String nameSupplier, String direction, String telephonNumber, String email) {
        this.SIC = SIC;
        this.nameSupplier = new SimpleStringProperty(nameSupplier);
        this.direction = new SimpleStringProperty(direction);
        this.telephoneNumber = new SimpleStringProperty(telephonNumber);
        this.email = new SimpleStringProperty(email);
    }

    public String getSIC() {
        return SIC;
    }

    public String getNameSupplier() {
        return nameSupplier.get();
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier.set(nameSupplier);
    }

    public StringProperty nameSupplierProperty() {
        return nameSupplier;
    }

    public String getDirection() {
        return direction.get();
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    public StringProperty directionProperty() {
        return direction;
    }

    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber.set(telephoneNumber);
    }

    public StringProperty telephoneNumberProperty() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public Supplier nameSupplier(StringProperty nameSupplier) {
        this.nameSupplier = nameSupplier;
        return this;
    }

    public Supplier direction(StringProperty direction) {
        this.direction = direction;
        return this;
    }

    public Supplier telephoneNumber(StringProperty telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public Supplier email(StringProperty email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(SIC, supplier.SIC) && Objects.equals(nameSupplier, supplier.nameSupplier) && Objects.equals(direction, supplier.direction) && Objects.equals(telephoneNumber, supplier.telephoneNumber) && Objects.equals(email, supplier.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SIC, nameSupplier, direction, telephoneNumber, email);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "SIC=" + SIC +
                ", nameSupplier='" + nameSupplier + '\'' +
                ", direction='" + direction + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
