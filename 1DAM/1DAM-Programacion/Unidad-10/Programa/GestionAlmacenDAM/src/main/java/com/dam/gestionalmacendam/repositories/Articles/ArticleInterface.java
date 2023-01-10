package com.dam.gestionalmacendam.repositories.Articles;

import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.CRUDRepository;
import com.dam.gestionalmacendam.repositories.SearchByUuid;


public interface ArticleInterface extends CRUDRepository<Article, String>, SearchByName<Article, String>, SearchByUuid<Article, String> {

}
