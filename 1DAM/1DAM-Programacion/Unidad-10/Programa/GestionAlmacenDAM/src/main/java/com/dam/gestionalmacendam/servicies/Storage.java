package com.dam.gestionalmacendam.servicies;

import com.dam.gestionalmacendam.models.Backup;
import com.dam.gestionalmacendam.utils.Properties;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Storage implements IStorageBackup {
    private static Storage instance;

    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = Properties.BACKUP;
    private final String backupFile = Properties.BACKUP_FILE;


    private Storage() {
        makeDirectory();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private void makeDirectory() {
        if (!Files.exists(Paths.get(Properties.BACKUP))) {
            try {
                Files.createDirectory(Paths.get(dir));
                // Imagenes
            } catch (IOException e) {
            }
        }
    }
    class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    @Override
    public boolean save(Backup backup) {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDateTime.class,new LocalDateTimeSerializer()).create();
        boolean result = false;
        PrintWriter f = null;
        try {
            f = new PrintWriter(new FileWriter(backupFile));
            f.println(gson.toJson(backup));
            result = true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            result = false;
        } finally {
            if (f != null) {
                f.close();
            }
        }
        return result;
    }


    @Override
    public Backup load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Backup backup = null;
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(backupFile));
            backup = gson.fromJson(reader, new TypeToken<Backup>() {
            }.getType());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return backup;
    }

    @Override
    public String getStoragePath() {
        return backupFile;
    }
}
