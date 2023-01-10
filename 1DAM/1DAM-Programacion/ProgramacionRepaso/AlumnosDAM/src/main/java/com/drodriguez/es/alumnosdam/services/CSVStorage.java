package com.drodriguez.es.alumnosdam.services;

import com.drodriguez.es.alumnosdam.dto.AlumnoDTO;
import com.drodriguez.es.alumnosdam.managers.DataBaseManager;
import com.drodriguez.es.alumnosdam.models.Alumno;
import com.drodriguez.es.alumnosdam.models.PROMOCION;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVStorage implements ICSVStorage {
    DataBaseManager db;

    @Override
    public boolean save(Path path, List<Alumno> item) {
        var encabezado = "id, dni, nombreApellidos, nota, fechaNacimiento, promocion";
        var csv =new StringBuilder(encabezado);
        var csvLista = item.stream().map(this::toCsv).toList();
        for (String st : csvLista) {
            csv.append("\n");
            csv.append(st);
        }
        try (var fileWriter = new FileWriter(path.toFile())){
            fileWriter.write(csv.toString());
            return true;
        } catch (Exception e) {
            System.out.println("Error escribiendo en la base de datos");
        }
        return false;
    }

    @Override
    public List<Alumno> load(Path Path) throws IOException {

        return Collections.emptyList();
    }
    public List<AlumnoDTO> loadCSV(Path path) {
        File fichero = null;
        BufferedReader f = null;
        List<AlumnoDTO> alumnos = new ArrayList<>();

        try {
            fichero = new File(String.valueOf(path));
            f = new BufferedReader(new FileReader(fichero));

            String linea;
            while((linea = f.readLine()) != null) {
                alumnos.add(parse(linea));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return alumnos;
    }

    private AlumnoDTO parse(String linea){
        String[] spl = linea.split(",");
        var id = Integer.parseInt(spl[0]);
        var dni = spl[1];
        var nombreApellidos = spl[2];
        var nota = Double.parseDouble(spl[3]);
        var fechaNacimiento = LocalDate.parse(spl[4]);
        var promocion = PROMOCION.valueOf(spl[5]);
        return new AlumnoDTO(id, dni, nombreApellidos, nota, fechaNacimiento, promocion);
    }

    private String toCsv(Alumno dto){
        return dto.getID() + ";" +
                dto.getDni() + ";" +
                dto.getNombreApellidos() + ";" +
                dto.getNota() + ";" +
                dto.getFechaNacimiento()
                + ";" + dto.getPromociona();
    }
}
