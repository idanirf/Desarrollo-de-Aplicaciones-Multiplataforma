package com.dam.gestionalmacendam.dto;

import com.dam.gestionalmacendam.models.Article;

import java.io.Serializable;

public class ArticleDTO implements Serializable {


   private final String pic;

   private final String article;

   private final String description;

   private final String location;

   private final Double price;

   private final Integer stock;

   private final Boolean isActive;

   private final String photo;

    public ArticleDTO(Article article) {
        this.pic = article.getPIC();
        this.article = article.articleProperty().get();
        this.description =article.descriptionProperty().get();
        this.location = article.locationProperty().get();
        this.price = article.priceProperty().get();
        this.stock = article.stockProperty().get();
        this.isActive = article.isActiveProperty().get();
        this.photo =article.photoProperty().get();
    }



    public Article fromDTO(){return new Article(pic, article, description, location, price, stock,isActive,photo);}
}
