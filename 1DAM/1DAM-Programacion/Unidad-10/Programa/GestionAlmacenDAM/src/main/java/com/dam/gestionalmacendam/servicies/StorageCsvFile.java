package com.dam.gestionalmacendam.servicies;

import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.models.Backup;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class StorageCsvFile implements IStorageCSV {
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "data";
    private final String articleFile = dir + File.separator + "articles.csv";


    @Override
    public boolean save(List<Article> articles) {
        PrintWriter f =null;
        File fichero= null;
        boolean ok=false;
        try {
            fichero = new File(articleFile);
            f = new PrintWriter(new FileWriter(fichero));

            for (Article article : articles) {
                f.println(article.toString(";"));
            }
            ok=true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }finally {
            if (f !=null){
                f.close();
            }
        }
        return ok;
    }

    @Override
    public List<Article> load() {
        List<Article> articles;

        try {
            articles= Files.lines(Path.of(articleFile)).map(this::getArticle).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    private Article getArticle(String line) {
        String[] campos= line.split(";");
        String uuid= campos[0];
        String article= campos[1];
        String description= campos[2];
        String location= campos[3];
        double price= parseDouble(campos[4]);
        int stock= parseInt(campos[5]);
        boolean isActive= parseBoolean(campos[6]);
        String photo= campos[7];
        return new Article(uuid,article,description,location,price,stock,isActive,photo);
    }

    @Override
    public String getStoragePath() {
        return null;
    }
}
