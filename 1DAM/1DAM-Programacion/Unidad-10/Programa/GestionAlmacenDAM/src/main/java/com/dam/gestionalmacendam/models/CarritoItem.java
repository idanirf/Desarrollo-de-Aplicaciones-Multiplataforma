package com.dam.gestionalmacendam.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data
public class CarritoItem {
    private final SimpleStringProperty name;
    private final SimpleStringProperty photo;
    private final SimpleDoubleProperty price;
    private SimpleIntegerProperty amount;
    private double total;

    public CarritoItem(String nombre, String imagen, double precio, int cantidad) {
        this.name = new SimpleStringProperty(nombre);
        this.photo = new SimpleStringProperty(imagen);
        this.price = new SimpleDoubleProperty(precio);
        this.amount = new SimpleIntegerProperty(cantidad);
        this.total = precio * cantidad;
    }

    public String getName() {
        return name.get();
    }

    public String getPhoto() {
        return photo.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
        this.total = price.get() * amount;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "CarritoItem{" +
                "name=" + name +
                ", photo=" + photo +
                ", price=" + price +
                ", amount=" + amount +
                ", total=" + total +
                '}';
    }
}
