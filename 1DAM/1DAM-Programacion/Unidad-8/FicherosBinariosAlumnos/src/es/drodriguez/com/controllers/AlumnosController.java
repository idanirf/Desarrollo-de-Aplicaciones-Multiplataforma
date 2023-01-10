package es.drodriguez.com.controllers;

import es.drodriguez.com.models.Alumno;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AlumnosController {
    //Necesitamos UUID para poder serializar


    //Inicializar variables
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "data";
    //Directorio de copias de seguridad
    private final String backup = ruta + File.separator + "backup";
    //Archivo de datos
    private final String alumnosFile = ruta + File.separator + "alumnosFile.txt";

    //Inicializar controlador
    public AlumnosController() {
        init();
    }

    public void init() {
        File directory = new File(this.dir);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public void getDir() {
        System.out.println(dir);
    }

    //Guardar alumnos en una lista de alumnos, almacenamos el alumno de uno en uno
    public void guardarAlumnos1a1(List<Alumno> alumnos, boolean append) {
        File fichero = null;
        ObjectOutputStream f = null;
        try {
            fichero = new File(alumnosFile);
            f = new ObjectOutputStream(new FileOutputStream(fichero, append));
            for (Alumno p : alumnos) {
                f.writeObject(p);
            }
        } catch (Exception e) {
            System.out.println("Error Write: " + e.getMessage());
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.out.println("Error Close Write: " + e.getMessage());
                }
            }
        }
    }

    //Leer Alumnos de una lista de uno en uno
    public List<Alumno> leerAlumno1a1() {
        File fichero = null;
        ObjectInputStream f = null;
        List<Alumno> alumnos = new ArrayList<>();
        try {
            fichero = new File(alumnosFile);
            f = new ObjectInputStream(new FileInputStream(fichero));
            Alumno alumno;
            while ((alumno = (Alumno) f.readObject()) != null) {
                alumnos.add(alumno);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error Read: " + e.getMessage());
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.out.println("Error Read: " + e.getMessage());
                }
            }
        }
        return alumnos;
    }

    //Realizar copia de seguridad
    public void backup() {
        Path pathOrigen = Paths.get(alumnosFile);
        Path pathFinal = Paths.get(backup + File.separator + "alumnos.back");
        if (!Files.exists(Paths.get(backup))) {
            try {
                Files.createDirectories(Paths.get(backup));
                Files.copy(pathOrigen, pathFinal);
            } catch (IOException e) {
                System.out.println("Error backup:" + e.getMessage());
            }
        }
    }

    //Guardar listado completo de alumnos
    public void guardarListaAlumnos(List<Alumno> alumnos, boolean append) {
        File fichero = null;
        ObjectOutputStream f = null;
        try {
            fichero = new File(alumnosFile);
            f = new ObjectOutputStream(new FileOutputStream(fichero, append));
            f.writeObject(alumnos);
        } catch (Exception e) {
            System.out.println("Error Write: " + e.getMessage());
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.out.println("Error Write: " + e.getMessage());
                }
            }
        }
    }

    //Leer listado completo de alumnos
    public List<Alumno> leerListaAlumnos() {
        File fichero = null;
        ObjectInputStream f = null;
        List<Alumno> alumnos = new ArrayList<>();
        try {
            fichero = new File(alumnosFile);
            f = new ObjectInputStream(new FileInputStream(fichero));
            alumnos = (List<Alumno>) f.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error read: " + e.getMessage());
        } finally {
            if (f != null) {
                try {
                    f.close();
                } catch (IOException e) {
                    System.err.println("Error read: " + e.getMessage());
                }
            }
        }
        return alumnos;
    }
}
