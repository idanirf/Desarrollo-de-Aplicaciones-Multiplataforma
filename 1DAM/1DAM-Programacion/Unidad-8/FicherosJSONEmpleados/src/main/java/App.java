import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Departamento;
import models.Personas;
import netscape.javascript.JSObject;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args){

        /*
        Tenemos dos opciones escribir en un archivo JSON o leer un archivo JSON:
        Para probar cada una de las dos opciones se comenta la línea que corresponda.
         */

        //ESCRIBIR ARCHIVO JSON:
        escribirJSON();
        //LEER ARCHIVO JSON:
        //leerJSON();
    }

    private static void leerJSON() {
        System.out.println("ESTAMOS LEYENDO UN OBJETO JSON: ");
        //Usmos GSON libreria de Google JSON para leer los datos
        var gson = new GsonBuilder().setPrettyPrinting().create();
        //Creamos una nueva persona
        Personas persona1 = new Personas("Daniel", "Rodríguez Fernández");
        //Convertimos la persona1 en JSON
        String json = gson.toJson(persona1);
        Personas resultado = gson.fromJson(json, Personas.class);
        System.out.println(resultado);
        if (resultado.equals(persona1)){
            System.out.println("¡SON IGUALES!");
        }

        //LEER UNA LISTA DE PERSONAS
        System.out.println("LEER UNA LISTA DE OBJETOS JSON, EN ESTE CASO DE PERSONAS");
        //Creamos una nueva persona
        Personas persona2 = new Personas("Azahara", "Blanco Rodríguez");
        //Creamos una lista que esté formado por la persona1 y persona2
        List<Personas> personas = List.of(persona1, persona2);
        //Convertimos a JSON la lista de personas
        json = gson.toJson(personas);
        System.out.println(json);
        //Resultado es una lista de GSON a JSON
        List<Personas>resultadoLista = gson.fromJson(json, List.class);
        //Pasa a ser una array, formado por personas
        Personas[] resultadoArray = gson.fromJson(json, Personas[].class);
        System.out.println(resultadoArray);
        System.out.println(Arrays.toString(resultadoArray));


        //LEER OBJETOS COMPUESTOS, COMO PUEDE SER DE UNA LISTA.
        //Creamos los departamentos
        Departamento dept1  = new Departamento("RRHH", personas);
        Departamento dept2 = new Departamento("Desarrollo", personas);
        List<Departamento>departamentos = List.of(dept1, dept2);

        System.out.println("LEER UN OBJETO COMPUESTO DE OTROS CON JSON");
        json = gson.toJson(dept1);
        System.out.println(json);
        Departamento resultadoDepartamento = gson.fromJson(json, Departamento.class);
        System.out.println(resultadoDepartamento);
        json = gson.toJson(departamentos);
        System.out.println(json);
        List<Personas> resultadoDepartamentos = gson.fromJson(json, List.class);
        System.out.println(resultadoDepartamentos);
        Departamento[] resultadoDepartamentosArray = gson.fromJson(json, Departamento[].class);
        System.out.println(Arrays.toString(resultadoDepartamentosArray));
    }

    private static void escribirJSON() {
        Personas persona1 = new Personas("José Luis", "Gonzalez");
        Personas persona2 = new Personas("Jeremy", "Ramos");
        List<Personas>personas = List.of(persona1, persona2);
        System.out.println("JSON DE PERSONAS");
        System.out.println(persona1);

        Gson gson =  new Gson();
        String json = gson.toJson(persona1);
        System.out.println(json);

        System.out.println("LISTADO DE PERSONAS: ");
        json = gson.toJson(personas);
        System.out.println(json);

        System.out.println("JSON DE UNA PERSONA PRETTY PRINTING");
        gson = new GsonBuilder().setPrettyPrinting().create();
        json = gson.toJson(persona1);
        System.out.println(json);

        System.out.println("JSON DE UNA LISTA DE PERSONAS PRETTY PRINTING");
        json = gson.toJson(personas);
        System.out.println(json);

        System.out.println("JSON DE UN DEPARTAMENTO PRETTY PRINTING");
        Departamento dept1  = new Departamento("RRHH", personas);
        Departamento dept2 = new Departamento("Desarrollo", personas);
        List<Departamento>departamentos = List.of(dept1, dept2);

        json = gson.toJson(departamentos);
        System.out.println(json);

        System.out.println("JSON LISTA DE DEPARTAMENTOS PRETTY PRINTING");
        json = gson.toJson(departamentos);
        System.out.println(departamentos);
    }
}
