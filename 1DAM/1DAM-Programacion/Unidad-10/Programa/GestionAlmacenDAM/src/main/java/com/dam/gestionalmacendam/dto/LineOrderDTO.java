package com.dam.gestionalmacendam.dto;

import com.dam.gestionalmacendam.models.LineOrder;

import java.io.Serializable;

public class LineOrderDTO implements Serializable {

   private final String olic;

   private final String article;

   private final Integer load;

   private final Double unitPrice;

   private final Double totalPrice ;

   private final String belongsOrder;

    public LineOrderDTO(LineOrder lineO) {
        this.olic = lineO.getOLIC();
        this.article = lineO.getArticle().get();
        this.load =lineO.getLoad().get();
        this.unitPrice = lineO.getUnitPrice().get();
        this.totalPrice = lineO.totalPriceProperty().get();
        this.belongsOrder = lineO.getBelongsOrder().get();
    }


    public LineOrder fromDTO(){return new LineOrder(olic,article,load,unitPrice,belongsOrder);}



}
