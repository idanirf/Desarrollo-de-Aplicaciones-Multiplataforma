package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.Data;

import java.util.UUID;


@Data
public class Order {
    private String OIC = UUID.randomUUID().toString();
    private StringProperty customer;
    private DoubleProperty price;
    private ObjectProperty<Pay> methodPay;

    public Order(String customer, Pay methodPay) {
        this.customer = new SimpleStringProperty(customer);
        this.methodPay = new SimpleObjectProperty(methodPay);
    }

    public Order(String customer, double price, Pay methodPay) {

        this.customer = new SimpleStringProperty(customer);
        this.price = new SimpleDoubleProperty(price);
        this.methodPay = new SimpleObjectProperty(methodPay);
    }

    public Order(String OIC, String customer, double price, Pay methodPay) {
        this.OIC = OIC;
        this.customer = new SimpleStringProperty(customer);
        this.price = new SimpleDoubleProperty(price);
        this.methodPay = new SimpleObjectProperty(methodPay);
    }

    @Override
    public String toString() {
        return "Order{" +
                "OIC=" + OIC +
                ", customer=" + customer +
                ", price=" + price +
                ", methodPay=" + methodPay +
                '}';
    }
}
