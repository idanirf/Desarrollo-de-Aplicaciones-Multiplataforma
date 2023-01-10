package es.drodriguez.com.Repositories;

import es.drodriguez.com.Models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class BibliotecaRepository extends TreeMap<String, Ficha>implements ICRUDRepository<Ficha, Integer> {

    /**
     * @return listado de elementos de la biblioteca
     */
    @Override
    public List<Ficha> findAll() {
        return new ArrayList<>(this.values());
    }

    public Ficha insert(Ficha ficha) {
        this.put(ficha.getTitulo(), ficha);
        return ficha;
    }

    @Override
    public Ficha findById(Integer id) {
        for (Ficha ficha : this.values()){
            if (id == ficha.getId()){
                return ficha;
            }
        }
        return null;
    }

    /**
     * @param ficha Ficha elemento a borrar de la biblioteca
     * @return el pais eliminado
     */
    @Override
    public Ficha delete(Ficha ficha) {
        var elementoEncontrado = this.get(ficha.getTitulo());
        if (elementoEncontrado != null){
            this.remove(ficha.getTitulo());
        }
        return null;
    }

    /**
     * @param titulo del elemento
     * @return elemento buscado
     */
    public Ficha findByNombre(String titulo){
        return this.get(titulo);
    }
}
