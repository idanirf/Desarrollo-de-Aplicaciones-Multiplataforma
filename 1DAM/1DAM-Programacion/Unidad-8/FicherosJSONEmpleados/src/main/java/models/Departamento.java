package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;
//Usamos notaciones Lombock para disminuir la cantidad de código
@Data
@AllArgsConstructor
public class Departamento {
    private final String id = UUID.randomUUID().toString();
    private String Departamento;
    private List<Personas>personas;


}
