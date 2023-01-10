package com.drodriguez.es.alumnosdam.repositories;

import com.drodriguez.es.alumnosdam.dto.AlumnoDTO;
import com.drodriguez.es.alumnosdam.managers.DataBaseManager;
import com.drodriguez.es.alumnosdam.models.Alumno;
import com.drodriguez.es.alumnosdam.models.PROMOCION;
import com.drodriguez.es.alumnosdam.services.CSVStorage;
import com.drodriguez.es.alumnosdam.services.JSONStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AlumnosRepository implements IRepositoryAlumnos {
    public static AlumnosRepository instance;
    public final ObservableList<Alumno> repository = FXCollections.observableArrayList();
    CSVStorage csvStorage = new CSVStorage();
    JSONStorage jsonStorage = new JSONStorage();
    DataBaseManager db;

    public AlumnosRepository(DataBaseManager db) {
        this.db = db;
    }

    public static AlumnosRepository getInstance(DataBaseManager db) {
        if (instance == null) {
            instance = new AlumnosRepository(db);
        }
        return instance;
    }

    @Override
    public ObservableList<Alumno> findAll() throws SQLException {
        String sql = "SELECT * FROM ALUMNOS";
        db.open();
        ResultSet resultado = db.select(sql).orElseThrow(() -> new SQLException("Error mostrando todos los alumnos"));
        repository.clear();
        while (resultado.next()) {
            repository.add(
                    new Alumno(
                            resultado.getInt("id"),
                            resultado.getString("dni"),
                            resultado.getString("nombreApellidos"),
                            resultado.getDouble("nota"),
                            LocalDate.parse(resultado.getString("fechaNacimiento")),
                            PROMOCION.valueOf(resultado.getString("promocion"))
                    ));
        }
        return repository;
    }

    @Override
    public Optional<Alumno> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM ALUMNOS WHERE id = ?";
        db.open();
        ResultSet resultado = db.select(sql, id).orElseThrow(SQLException::new);
        if (resultado.first()) {
            Alumno alumno = new Alumno(
                    resultado.getInt("id"),
                    resultado.getString("dni"),
                    resultado.getString("nombreApellidos"),
                    resultado.getDouble("nota"),
                    LocalDate.parse(resultado.getString("fecha")),
                    PROMOCION.valueOf(resultado.getString("promocion"))
            );
            db.close();
            return Optional.of(alumno);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Alumno> save(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO ALUMNOS (id, dni, nombreApellidos, nota, fechaNacimiento, promocion) VALUES (null, ?, ?, ?, ?, ?)";
        db.open();
        db.insert(sql, alumno.getDni(), alumno.getNombreApellidos(), alumno.getNota(), alumno.getFechaNacimiento(), alumno.getPromociona());
        db.close();
        return Optional.of(alumno);
    }

    @Override
    public Optional<Alumno> update(Integer id, Alumno alumno) throws SQLException {
        String sql = "UPDATE ALUMNOS SET dni = ?, nombreApellidos = ?, nota = ?, fechaNacimiento = ?, promocion = ? WHERE id = ?";
        db.open();
        db.update(sql, alumno.getDni(), alumno.getNombreApellidos(), alumno.getNota(), alumno.getFechaNacimiento(), alumno.getPromociona(), id);
        db.close();
        repository.set(repository.indexOf(alumno), alumno);
        return Optional.empty();
    }

    @Override
    public void delete(Alumno alumno) throws SQLException {
        String sql = "DELETE FROM ALUMNOS WHERE id=?";
        db.open();
        db.delete(sql, alumno.getID());
        db.close();
        repository.remove(alumno);
    }

    public void loadFromCSV(Path path) throws SQLException {
        List<AlumnoDTO> alumnos = csvStorage.loadCSV(path);
        repository.clear();
        String sql = "DELETE FROM ALUMNOS";
        db.open();
        db.update(sql);
        db.close();
        alumnos.forEach(m -> {
                    try {
                        save(m.fromDTO());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void exportJSON(Path path) throws SQLException {
        List<AlumnoDTO> alumnos = repository.stream().map(AlumnoDTO::new).toList();
        jsonStorage.exportarJSON(alumnos, path);
    }

}
