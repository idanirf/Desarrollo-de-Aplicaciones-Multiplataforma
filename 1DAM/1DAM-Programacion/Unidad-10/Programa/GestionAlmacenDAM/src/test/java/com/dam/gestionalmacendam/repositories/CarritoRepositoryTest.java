package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.models.CarritoItem;
import com.dam.gestionalmacendam.repositories.carrito.CarritoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarritoRepositoryTest {
    private static final CarritoItem item = new CarritoItem("Producto", "x", 10, 2);

    CarritoRepository repository = CarritoRepository.getInstance();

    @AfterEach
    void setUp() {
        repository.clear();
    }

    @Test
    void getItemsTest() {
        var list = repository.getItems();
        assertEquals(0, list.size());

        repository.addItem(item);

        var lis1 = repository.getItems();
        assertAll(
                () -> assertTrue(lis1.size() > 0),
                () -> assertEquals(lis1.get(0).getName(), item.getName()),
                () -> assertEquals(lis1.get(0).getPhoto(), item.getPhoto()),
                () -> assertEquals(lis1.get(0).getAmount(), item.getAmount()),
                () -> assertEquals(lis1.get(0).getPrice(), item.getPrice()),
                () -> assertEquals(lis1.get(0).getTotal(), item.getTotal())
        );
    }

    @Test
    void addItemTest() {
        repository.addItem(item);
        var list = repository.getItems();

        assertTrue(list.contains(item));
    }

    @Test
    void removeItemTest() {
        repository.addItem(item);
        repository.removeItem(item);
        var list = repository.getItems();

        assertFalse(list.contains(item));

    }

    @Test
    void getTotalTest() {
        var itemNew = new CarritoItem("ProductoNuevo", "x", 12, 1);
        repository.addItem(item);
        repository.addItem(itemNew);

        var total = repository.getTotal();

        assertEquals(total, 32);
    }

}