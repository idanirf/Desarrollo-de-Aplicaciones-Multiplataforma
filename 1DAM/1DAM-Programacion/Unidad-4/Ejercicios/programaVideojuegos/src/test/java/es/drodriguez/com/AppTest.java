package es.drodriguez.com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.drodriguez.com.models.Videojuegos;
import org.junit.jupiter.api.*;

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Test Videojuegos")
    class VideojuegosTest {
        private Videojuegos videojuego = new Videojuegos("hola", "pc", 20.00f);

        @BeforeEach
        void restVideojuego() {
            videojuego = new Videojuegos("hola", "pc", 20.00f);
        }

        @org.junit.jupiter.api.Test
        @Order(1)
        @DisplayName("Test caso videojuego constructor")
        void testConstructor() {
            Videojuegos videojuego = new Videojuegos("hola", "pc", 20.00f);
            Assertions.assertAll(
                    () -> assertEquals("hola", videojuego.getTitulo()),
                    () -> assertEquals("pc", videojuego.getPlataforma()),
                    () -> assertEquals(20.00f, videojuego.getPrecio())
            );
        }


        @org.junit.jupiter.api.Test
        void testGetTitulo() {
            assertEquals("hola", videojuego.getTitulo());
        }

        @org.junit.jupiter.api.Test
        void testSetTitulo() {
            videojuego.setTitulo("hola");
            assertEquals("hola", videojuego.getTitulo());
        }

        @Test
        void testSetTituloExceptions() {
            // Excepcion espacios en blanco
            IllegalArgumentException thrown;

            thrown = assertThrows(IllegalArgumentException.class, () -> {
                videojuego.setTitulo(" ");
            }, "IllegalArgumentException expected");

            assertEquals("El titulo no puede estar en blanco", thrown.getMessage());

            //Excepcion - vacio
            thrown = assertThrows(IllegalArgumentException.class, () -> {
                videojuego.setTitulo("");
            }, "IllegalArgumentException expected");

            assertEquals("El titulo no puede estar en blanco", thrown.getMessage());

            // Excepcion null
            thrown = assertThrows(IllegalArgumentException.class, () -> {
                videojuego.setTitulo(null);
            }, "IllegalArgumentException expected");

            assertEquals("El nombre no puede estar en blanco ", thrown.getMessage());

            // + 100 carcteres
            thrown = assertThrows(IllegalArgumentException.class, () -> {
                videojuego.setTitulo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.");
            }, "IllegalArgumentException expected");

            assertEquals("El nombre no puede estar en blanco", thrown.getMessage());

            thrown = assertThrows(IllegalArgumentException.class, () -> {
                videojuego.setTitulo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.");
            }, "IllegalArgumentException expected");

            Assertions.assertTrue(thrown.getMessage().contains("100"));
        }

        @org.junit.jupiter.api.Test
        void testGetPrecio() {
            assertEquals(20.00f, videojuego.getTitulo());
        }

        @org.junit.jupiter.api.Test
        void testSetPrecio() {
            videojuego.setPrecio(20.00f);
            assertEquals(20.00f, videojuego.getPrecio());
        }

        @org.junit.jupiter.api.Test
        void testSetPrecioExceptions() {
            // Excepcion 0
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                videojuego.setPrecio(0);
            }, "IllegalArgumentException expected");

            Assertions.assertTrue(thrown.getMessage().contains("0"));

            // Excepcion mayor de 200
            thrown = assertThrows(IllegalArgumentException.class, () -> {
                videojuego.setPrecio(200.01f);
            }, "IllegalArgumentException expected");

            Assertions.assertTrue(thrown.getMessage().contains("200.00"));
        }

}
