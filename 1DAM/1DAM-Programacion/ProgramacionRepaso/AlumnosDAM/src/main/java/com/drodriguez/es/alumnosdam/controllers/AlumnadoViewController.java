package com.drodriguez.es.alumnosdam.controllers;

import com.drodriguez.es.alumnosdam.managers.DataBaseManager;
import com.drodriguez.es.alumnosdam.models.Alumno;
import com.drodriguez.es.alumnosdam.models.PROMOCION;
import com.drodriguez.es.alumnosdam.repositories.AlumnosRepository;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlumnadoViewController {

    AlumnosRepository alumnosRepository = new AlumnosRepository(DataBaseManager.getInstance());
    @FXML
    TableView<Alumno> alumnoTable;
    @FXML
    TableColumn<Alumno, String> dniData;
    @FXML
    TableColumn<Alumno, String> nombreApellidosData;
    @FXML
    TableColumn<Alumno, Double> notaData;
    @FXML
    TableColumn<Alumno, PROMOCION> promocionData;
    @FXML
    TextField dniLabelData;
    @FXML
    TextField nombreApellidosLabelData;
    @FXML
    TextField notaLabelData;
    @FXML
    DatePicker fechaNacimientoLabelData;
    @FXML
    ComboBox<PROMOCION> promocionLabelData;
    private ObservableList<Alumno> alumnosList;
    @FXML
    private Label idData;

    @FXML
    private void initialize() {
        try {
            System.out.println("Cargando datos");
            loadComboBox();
            alumnoTable.setItems(alumnosRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dniData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDni()));
        nombreApellidosData.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreApellidos()));
        notaData.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getNota()).asObject());
        promocionData.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPromociona()));
        alumnoTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> alumnoSeleccionado(newValue));
        vaciarCampos();
    }

    private void loadComboBox() {
        promocionLabelData.setItems(FXCollections.observableArrayList(PROMOCION.PROMOCIONA, PROMOCION.NO_PROMOCIONA));
    }

    private void loadData() throws SQLException {
        System.out.println("Obteniendo datos de la base de datos");
        alumnoTable.setItems(alumnosRepository.findAll());
    }

    public void onCerrarAplicacion(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("¿Está seguro que desea cerrar AlumnosDAM?");
        alert.setContentText("¿Está seguro que desea cerrar AlumnosDAM?");
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public void onImportarAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importar alumnos");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        Path path = fileChooser.showOpenDialog(idData.getScene().getWindow()).toPath();
        try {
            alumnosRepository.loadFromCSV(path);
            alumnoTable.setItems(alumnosRepository.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void onExportarAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exportar alumnos");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON", "*.json"));
        Path path = fileChooser.showSaveDialog(idData.getScene().getWindow()).toPath();
        try {
            alumnosRepository.exportJSON(path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onPrintInformeAction(ActionEvent actionEvent) throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + File.separator + "data" + File.separator + "informe.txt");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DoubleSummaryStatistics results = null;
        String resultado1 = "";
        String resultado2 = "";
        String resultado3 = "";
        String resultado4 = "";
        String resultado5 = "";
        String resultado6 = "";
        try {
            results = alumnosRepository.findAll().stream().mapToDouble(Alumno::getNota).summaryStatistics();
            resultado1 = alumnosRepository.findAll().stream().collect(Collectors.groupingBy(Alumno::getPromociona)).get(PROMOCION.PROMOCIONA).toString();
            resultado2 = alumnosRepository.findAll().stream().collect(Collectors.groupingBy(Alumno::getPromociona)).get(PROMOCION.NO_PROMOCIONA).toString();
            resultado3 = alumnosRepository.findAll().stream().collect(Collectors.groupingBy(Alumno::getPromociona, Collectors.counting())).toString();
            resultado4 = String.valueOf((long) alumnosRepository.findAll().size());
            resultado5 = alumnosRepository.findAll().stream().max(Comparator.comparing(Alumno::getNota)).get().getNombreApellidos();
            resultado6 = alumnosRepository.findAll().stream().min(Comparator.comparing(Alumno::getNota)).get().getNombreApellidos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assert results != null;
        String informeAlumnado = "Informe de alumnos" + "\n" +
                "Nota Media: " + results.getAverage() + "\n" +
                "Nota Máximo: " + results.getMax() + "\n" +
                " Nota Mínimo: " + results.getMin() + "\n" +
                "Alumnos QUE promocionan: " + resultado1 + "\n" +
                "Alumnos QUE NO promocionan: " + resultado2 + "\n" +
                "Nº Alumnos PROMOCIONA/NO PROMOCIONA: " + resultado3 + "\n" +
                "Nº Total de alumnos: " + resultado4 + "\n" +
                "Alumno con mayor nota: " + resultado5 + "\n" +
                "Alumno con menor nota: " + resultado6 + "\n";
        try {
            assert objectOutputStream != null;
            objectOutputStream.writeBytes(informeAlumnado);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRegistrarAlumnoAction(ActionEvent actionEvent) throws SQLException {
        try {
            alumnosRepository.save(
                    new Alumno(
                            dniLabelData.getText(),
                            nombreApellidosLabelData.getText(),
                            LocalDate.parse(fechaNacimientoLabelData.getValue().toString()),
                            Double.parseDouble(notaLabelData.getText()),
                            promocionLabelData.getValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        alumnoTable.setItems(alumnosRepository.findAll());
    }

    public void onEliminarAlumnoAction(ActionEvent actionEvent) throws SQLException {
        Alumno alumno = alumnoTable.getSelectionModel().getSelectedItem();
        try {
            alumnosRepository.delete(alumno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        alumnoTable.setItems(alumnosRepository.findAll());
    }

    public void onActualizarAlumnoAction(ActionEvent actionEvent) throws SQLException {
        Alumno alumno = alumnoTable.getSelectionModel().getSelectedItem();
        alumno.setDni(dniLabelData.getText());
        alumno.setNombreApellidos(nombreApellidosLabelData.getText());
        alumno.setFechaNacimiento(LocalDate.parse(fechaNacimientoLabelData.getValue().toString()));
        alumno.setNota(Double.parseDouble(notaLabelData.getText()));
        alumno.setPromociona(promocionLabelData.getValue());
        try {
            alumnosRepository.update(alumno.getID(), alumno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        alumnoTable.setItems(alumnosRepository.findAll());
    }


    public void onLimpiarCamposAction(ActionEvent actionEvent) {
        vaciarCampos();
    }

    private void vaciarCampos() {
        dniLabelData.setText("");
        nombreApellidosLabelData.setText("");
        notaLabelData.setText("");
        fechaNacimientoLabelData.setValue(null);
        promocionLabelData.setValue(null);
    }

    private void setData(Alumno alumno) {
        dniLabelData.setText(alumno.getDni());
        nombreApellidosLabelData.setText(alumno.getNombreApellidos());
        notaLabelData.setText(String.valueOf(alumno.getNota()));
        fechaNacimientoLabelData.setValue(alumno.getFechaNacimiento());
        promocionLabelData.setValue(alumno.getPromociona());
    }

    private void alumnoSeleccionado(Alumno alumno) {
        if (alumno != null) {
            setData(alumno);
        } else {
            vaciarCampos();
        }
    }
}
