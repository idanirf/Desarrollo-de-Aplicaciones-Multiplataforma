package es.drodriguez.com.Repositories;

import es.drodriguez.com.Models.Ficha;

import java.util.List;

public interface ICRUDRepository<T, ID> {
    List<T> findAll();

    T insert(T entity);

    T findById(ID id);

    Ficha delete(Ficha ficha);
}
