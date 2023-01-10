package com.drodriguez.es.alumnosdam.services;

import com.drodriguez.es.alumnosdam.dto.AlumnoDTO;
import com.drodriguez.es.alumnosdam.models.Alumno;
import com.drodriguez.es.alumnosdam.utils.Adapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class JSONStorage implements IJSONStorage {

    @Override
    public boolean save(Path path, List<Alumno> item) {
        return false;
    }

    @Override
    public List<Alumno> load(Path Path) throws IOException {
        return null;
    }

    public void exportarJSON(List<AlumnoDTO> alumnos, Path path) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new Adapter())
                .setPrettyPrinting()
                .create();
        PrintWriter f = null;
        try {
            f = new PrintWriter(String.valueOf(path));
            f.println(gson.toJson(alumnos));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (f != null) {
                f.close();
            }
        }
    }
}
