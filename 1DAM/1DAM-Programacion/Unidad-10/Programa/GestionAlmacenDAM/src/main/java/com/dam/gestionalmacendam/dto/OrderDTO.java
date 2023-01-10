package com.dam.gestionalmacendam.dto;


import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;

import java.io.Serializable;

public class OrderDTO implements Serializable {

   private final String oic;

   private final String customer;

   private final Double price;

   private final Pay methodPay;

    public OrderDTO(Order order) {
        this.oic = order.getOIC();
        this.customer = order.getCustomer().get();
        this.price =order.getPrice().get();
        this.methodPay = order.getMethodPay().get();
    }

    public Order fromDTO(){return new Order(oic, customer, price, methodPay);}
}
