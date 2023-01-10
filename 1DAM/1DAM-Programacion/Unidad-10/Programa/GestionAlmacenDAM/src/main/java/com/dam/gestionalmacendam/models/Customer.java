package com.dam.gestionalmacendam.models;

import javafx.beans.property.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Customer {
    private String CIC = UUID.randomUUID().toString();
    private StringProperty name;
    private StringProperty surname;
    private StringProperty cif;
    private StringProperty direction;
    private StringProperty nickName;
    private StringProperty password;
    private StringProperty telephoneNumber;
    private StringProperty email;
    private StringProperty photo;
    private ObjectProperty<LocalDateTime> createdAt;
    private BooleanProperty isActive;

    public Customer(String name, String surname, String cif, String direction, String nickName, String password, String telephoneNumber, String email, String photo, LocalDateTime createdAt, Boolean isActive) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.cif = new SimpleStringProperty(cif);
        this.direction = new SimpleStringProperty(direction);
        this.nickName = new SimpleStringProperty(nickName);
        this.password = new SimpleStringProperty(password);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
        this.email = new SimpleStringProperty(email);
        this.photo = new SimpleStringProperty(photo);
        this.createdAt = new SimpleObjectProperty<LocalDateTime>(createdAt);
        this.isActive= new SimpleBooleanProperty(isActive);


    }

    public Customer(String cic, String name, String surname, String cif, String direction, String nickName, String password, String telephoneNumber, String email, String photo, LocalDateTime createdAt, Boolean isActive) {
        this.CIC = cic;
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.cif = new SimpleStringProperty(cif);
        this.direction = new SimpleStringProperty(direction);
        this.nickName = new SimpleStringProperty(nickName);
        this.password = new SimpleStringProperty(password);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
        this.email = new SimpleStringProperty(email);
        this.photo = new SimpleStringProperty(photo);
        this.createdAt = new SimpleObjectProperty<LocalDateTime>(createdAt);
        this.isActive= new SimpleBooleanProperty(isActive);
    }

    public String getCIC() {
        return CIC;
    }

    public String getName() {
        return name.get();
    }


    public String getSurname() {
        return surname.get();
    }


    public String getCif() {
        return cif.get();
    }


    public String getDirection() {
        return direction.get();
    }

    public String getNickName() {
        return nickName.get();
    }

    public String getPassword() {
        return password.get();
    }


    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }


    public String getEmail() {
        return email.get();
    }


    public String getPhoto() {
        return photo.get();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    public Boolean isActive(){return isActive.get();}

    @Override
    public String toString() {
        return "Customer{" +
                "CIC='" + CIC + '\'' +
                ", name=" + name +
                ", surname=" + surname +
                ", cif=" + cif +
                ", direction=" + direction +
                ", nickName=" + nickName +
                ", password=" + password +
                ", telephoneNumber=" + telephoneNumber +
                ", email=" + email +
                ", photo=" + photo +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }
}
