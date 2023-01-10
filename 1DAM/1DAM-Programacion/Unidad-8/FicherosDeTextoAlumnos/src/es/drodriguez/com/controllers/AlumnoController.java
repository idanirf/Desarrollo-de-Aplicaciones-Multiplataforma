package es.drodriguez.com.controllers;

import es.drodriguez.com.models.Alumno;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AlumnoController {
    //Inicializar rutas con ubicación de directorios
    private Path currentRelativePath = Paths.get("");
    private String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "datos";

    //Copia seguridad
    private final String backup = ruta + File.separator + "backup";

    //Archivo donde se almacenan los alumnos
    private final String alumnosFile = dir + File.separator + "alumnos.txt";

    //Inicilizar el controlador
    public AlumnoController(){
        init();
    }

    private void init() {
        File directory = new File(this.dir);
        if (!directory.exists()){
            directory.mkdir();
        }
    }

    //Obtener dir con get
    public void getDir(){
        System.out.println(dir);
    }

    //Escribir los alumnos en un fichero de texto
    public void escribirFicheroTexto(List<Alumno> alumnos, boolean append){
        File fichero = null;
        PrintWriter f = null;
        try {
            fichero = new File(alumnosFile);
            f = new PrintWriter(new FileWriter(alumnosFile, false));
            //Acción que va a realizar de escritura
            for (Alumno a : alumnos){
                f.println(a.toFile());
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (f != null){
                f.close();
            }
        }
    }

    //Leer datos de los alumnos que se encuentran en un fichero
    public List<Alumno> leerAlumno() {
        File fichero = null;
        BufferedReader f = null;
        List<Alumno> alumnos = new ArrayList<>();
        try {
            fichero = new File(alumnosFile);
            f = new BufferedReader(new FileReader(fichero));
            String line;
            // ++Acción++
            while ((line = f.readLine()) != null){
                alumnos.add(getAlumnos(line));
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (f != null){
                    f.close();
                }
            }catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        return alumnos;
    }

    //Función para obtener los datos de los alumnos, con los campos separados por el valor ";"
    private Alumno getAlumnos(String line) {
        String [] campos = line.split(";");
        var id = Integer.parseInt(campos[0]);
        var nombre = campos[1];
        var curso = campos[2];
        var a = new Alumno(id, nombre, curso);
        return a;
    }

    //Copia de seguridad del fichero donde tenemos los alumnos
    public void backup(){
        Path pathOrigen = Paths.get(alumnosFile);
        Path pathFinal = Paths.get(backup + File.separator + "alumnosFile.bak");
        if (!Files.exists(Paths.get(backup))){
            try {
                Files.createDirectories(Paths.get(backup));
                Files.copy(pathOrigen, pathFinal);
            }catch (IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}
