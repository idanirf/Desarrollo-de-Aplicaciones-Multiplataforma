package es.drodriguez.com;

import es.drodriguez.com.Exceptions.BibliotecaExceptions;
import es.drodriguez.com.Models.*;
import es.drodriguez.com.Repositories.BibliotecaRepository;

public class Main {
    public static void main(String[] args) throws BibliotecaExceptions {
        Menu m = new Menu();
        m.menu();
    }
}

