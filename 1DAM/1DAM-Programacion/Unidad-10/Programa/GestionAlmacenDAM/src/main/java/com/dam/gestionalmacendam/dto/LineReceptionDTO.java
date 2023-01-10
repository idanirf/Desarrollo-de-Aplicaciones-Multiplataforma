package com.dam.gestionalmacendam.dto;


import com.dam.gestionalmacendam.models.LineReception;

import java.io.Serializable;

public class LineReceptionDTO implements Serializable {

   private final String rlic;

   private final String articlePIC;

    private final Integer load;

    private final Double unitPrice;

    private final Double totalPrice ;

    private final String belongsRecepcion;

    public LineReceptionDTO(LineReception lineR) {
        this.rlic = lineR.getRLIC();
        this.articlePIC = lineR.getArticlePIC().get();
        this.load = lineR.getLoad().get();
        this.unitPrice = lineR.getUnitPrice().get();
        this.totalPrice = lineR.getTotalPrice().get();
        this.belongsRecepcion = lineR.getBelongsRecepcion().get();

    }

    public LineReception fromDTO(){return new LineReception(rlic, articlePIC, load, unitPrice, belongsRecepcion);}
}
