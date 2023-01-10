package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleRepositoryTest {
    ArticleRepository repository = ArticleRepository.getInstance(DataBaseManager.getInstance());
    Article a = new Article("Prueba1", "grande", "sala a", 35.50D, 5, false, "x");

    @BeforeEach
    void initDataTest() {
        try {
            repository.save(a);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("objeto ya en el repositorio");
        }
    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM Article WHERE PIC =?";
        db.open();
        db.delete(query, a.getPIC());
        db.close();
    }


    /**
     * @Test para mostrar todos los articulos del repositorio
     */
    @Test
    void findAllTest() throws SQLException {
        ObservableList<Article> res = repository.findAll();
        Article article = res.get(0);

        assertAll(
                () -> assertTrue(res.size() > 0),
                () -> assertFalse(res.size() == 0),
                () -> assertEquals(a.getArticle().get(), article.getArticle().get())
        );
    }

    /**
     * @Test buscar un Article del repositorio por el nombre del articulo repositorio
     */
    @Test
    void findByNameTest() throws SQLException {
        Article res = repository.findByName(a.getArticle().get());
        Article res1 = repository.findByUuid(a.getPIC());
        assertAll(
                () -> assertTrue(res.getArticle().get().equalsIgnoreCase(res1.getArticle().get()))
        );
    }

    /**
     * @Test buscar un Article del repositorio por el PIC del articulo repositorio
     */
    @Test
    void searchByUuidTest() throws SQLException {
        var aux = repository.findByUuid(a.getPIC());
        assertAll(

                () -> assertEquals(aux.getArticle().get(), a.getArticle().get()),
                () -> assertEquals(aux.getPIC(), a.getPIC()),
                () -> assertEquals(aux.getPrice().get(), a.getPrice().get()),
                () -> assertEquals(aux.getDescription().get(), a.getDescription().get())
        );

    }


    /**
     * @Test salbar Article en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {
        var a1 = repository.findByUuid(a.getPIC());
        assertAll(
                () -> assertEquals(a1.getArticle().get(), a.getArticle().get()),
                () -> assertEquals(a1.getPrice().get(), a.getPrice().get()),
                () -> assertEquals(a1.getDescription().get(), a.getDescription().get()),
                () -> assertEquals(a1.getStock().get(), a.getStock().get())
        );
    }

    /**
     * @Test cargar los datos nuevos del Article en el repositorio
     */
    @Test
    void updateTest() throws SQLException {
        a.setArticle(new SimpleStringProperty("mesita"));
        var aux = repository.update(a.getPIC(), a);
        assertAll(
                () -> assertEquals(aux.get().getArticle().get(), a.getArticle().get()),
                () -> assertEquals(aux.get().getPrice().get(), a.getPrice().get()),
                () -> assertEquals(aux.get().getDescription().get(), a.getDescription().get()),
                () -> assertEquals(aux.get().getStock().get(), a.getStock().get())
        );
    }
}
