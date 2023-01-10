package es.drodriguez.com.Controller;

import es.drodriguez.com.Exceptions.BibliotecaExceptions;
import es.drodriguez.com.Models.DVD;
import es.drodriguez.com.Models.Ficha;
import es.drodriguez.com.Models.Libro;
import es.drodriguez.com.Models.Revistas;
import es.drodriguez.com.Repositories.BibliotecaRepository;

import java.util.List;

public class BibliotecaController {
    private static BibliotecaController instance;
    private final BibliotecaRepository bibliotecaRepository;

    private BibliotecaController(BibliotecaRepository bibliotecaRepository){
        this.bibliotecaRepository = bibliotecaRepository;
    }

    /**
     * Implementación de singleton
     *
     * @return instance BibliotecaController
     */
    public static BibliotecaController getInstance(){
        if (instance == null) {
            instance = new BibliotecaController(new BibliotecaRepository());
        }
        return instance;
    }

    /**
     * Alta dvd
     * @param dvd elemento a insertar
     * @return elemento de la biblioteca insertado
     */
    public DVD crearDVD(DVD dvd){
        bibliotecaRepository.insert(dvd);
        return dvd;
    }

    /**
     * Alta revistas
     * @param revistas elemento a insertar
     * @return elemento de la biblioteca insertado
     */
    public Revistas crearRevista(Revistas revistas){
        bibliotecaRepository.insert(revistas);
        return revistas;
    }

    /**
     * Alta libro
     * @param libro elemento a insertar
     * @return elemento de la biblioteca insertado
     */
    public Libro crearLibro(Libro libro) {
        bibliotecaRepository.insert(libro);
        return libro;
    }


    public List<Ficha>getAllBiblioteca(){
        return bibliotecaRepository.findAll();
    }

    /**
     * Elimina el pais con el nombre indicado
     * @param titulo del elemento
     * @return elemento de la biblioteca eliminado
     * @throws BibliotecaExceptions en el caso que no se encuentre un elemento en la biblioteca
     */
    public Ficha borrarElement (String titulo) throws BibliotecaExceptions{
        var ficha = bibliotecaRepository.findByNombre(titulo);
        if (ficha != null){
            bibliotecaRepository.delete(ficha);
            return ficha;
        }else{
            throw new BibliotecaExceptions("No hemos encontrado en la biblioteca ningún elemento con el nombre: " + titulo);
        }
    }

    /**
     * Elimina el pais con el nombre indicado
     *
     * @param titulo del elemento
     * @return elemento de la biblioteca buscado
     * @throws BibliotecaExceptions en el caso que no se encuentre un elemento en la biblioteca
     */
    public Ficha getFichaByNombre(String titulo)throws BibliotecaExceptions {
        var ficha = bibliotecaRepository.findByNombre(titulo);
        if (ficha != null){
            return ficha;
        }
        throw new BibliotecaExceptions("No hemos encontrado en la biblioteca");
    }



}