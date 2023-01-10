package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

//Podemos usar notaciones de Lombock para ahorrar trabajo
@Data
@AllArgsConstructor
public class Personas {
    private final String id = UUID.randomUUID().toString();
    private String nombre;
    private String apellidos;

}
