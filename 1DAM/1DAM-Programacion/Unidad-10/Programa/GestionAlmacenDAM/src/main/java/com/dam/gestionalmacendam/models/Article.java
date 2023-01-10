package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.Data;
import java.util.UUID;

@Data
public class Article {
    private String PIC=UUID.randomUUID().toString();
    private StringProperty article;
    private StringProperty description;
    private StringProperty location;
    private DoubleProperty price;
    private IntegerProperty stock;
    private BooleanProperty isActive;
    private StringProperty photo;
    public Article(){

    }

    public Article(String article, String description, String location,
                   Double price, Integer  stock, Boolean  isActive,String photo) {
        this.article =new SimpleStringProperty(article);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.price = new SimpleDoubleProperty(price) ;
        this.stock = new SimpleIntegerProperty(stock);
        this.isActive = new SimpleBooleanProperty(isActive);
        this.photo=new SimpleStringProperty(photo);
        isActive();
    }
    public Article(String PIC ,String article, String description, String location,
                   Double price, Integer  stock, Boolean  isActive, String photo) {
        this.PIC = PIC;
        this.article =new SimpleStringProperty(article);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.price = new SimpleDoubleProperty(price) ;
        this.stock = new SimpleIntegerProperty(stock);
        this.isActive = new SimpleBooleanProperty(isActive);
        this.photo=new SimpleStringProperty(photo);
        isActive();
    }
    public StringProperty imagenProperty() {
        return photo;
    }
    public String getPIC() {
        return PIC;
    }
    public StringProperty articleProperty() {
        return article;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public StringProperty photoProperty() {
        return photo;
    }

    public String getPhoto() {
        return photo.get();
    }
    public boolean isActive() {
        this.isActive = new SimpleBooleanProperty(stock.get()>0);
        return stock.get()>0;
    }
    @Override
    public String toString() {
        return "Products{" +
                "PIC=" + PIC +
                ", article='" + article + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", isActive=" + isActive +
                ", photo=" + photo +
                '}';
    }
    public String toString(String separator){
        return PIC + separator +article.get()+separator+description.get()+separator+location.get()
                +separator+price.get()+separator+stock.get()+separator+isActive.get()+separator+photo.get();
    }
}
