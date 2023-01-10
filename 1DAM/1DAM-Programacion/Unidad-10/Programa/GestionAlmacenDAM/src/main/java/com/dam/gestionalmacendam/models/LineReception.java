package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data

@AllArgsConstructor
public class LineReception {
    private String RLIC = UUID.randomUUID().toString();
    private StringProperty articlePIC;
    private IntegerProperty load;
    private DoubleProperty unitPrice;
    private DoubleProperty totalPrice;
    private StringProperty belongsRecepcion;

    public LineReception() {
    }

    public LineReception(String articlePIC, Integer load, Double unitPrice, String belongsRecepcion) {
        this.articlePIC = new SimpleStringProperty(articlePIC);
        this.load = new SimpleIntegerProperty(load);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.totalPrice = new SimpleDoubleProperty(unitPrice * load);
        this.belongsRecepcion = new SimpleStringProperty(belongsRecepcion);
    }

    public LineReception(String rlic, String articlePIC, Integer load, Double unitPrice, String belongsRecepcion) {
        this.RLIC = rlic;
        this.articlePIC = new SimpleStringProperty(articlePIC);
        this.load = new SimpleIntegerProperty(load);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.totalPrice = new SimpleDoubleProperty(unitPrice * load);
        this.belongsRecepcion = new SimpleStringProperty(belongsRecepcion);
    }

    public void setLoad(int load) {
        this.load.set(load);
        this.totalPrice = new SimpleDoubleProperty(this.load.intValue() * this.unitPrice.doubleValue());
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice.set(unitPrice);
        this.totalPrice = new SimpleDoubleProperty(this.load.intValue() * this.unitPrice.doubleValue());
    }

    @Override
    public String toString() {
        return "LineReception{" +
                "RLIC=" + RLIC +
                ", articlePIC=" + articlePIC +
                ", load=" + load +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", belongsRecepcion=" + belongsRecepcion +
                '}';
    }
}
