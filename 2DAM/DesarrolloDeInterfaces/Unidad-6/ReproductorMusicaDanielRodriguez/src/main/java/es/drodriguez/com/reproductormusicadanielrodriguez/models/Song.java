package es.drodriguez.com.reproductormusicadanielrodriguez.models;

import java.io.Serializable;

public class Song implements Serializable {
    private String file;

    private Integer year;

    private Album album;

    private Integer track_num;

    private String publisher;

    private Integer album_id;

    private Integer id;

    private String title;

    private Integer genre_id;

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getTrack_num() {
        return this.track_num;
    }

    public void setTrack_num(Integer track_num) {
        this.track_num = track_num;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getAlbum_id() {
        return this.album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGenre_id() {
        return this.genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    // To string
    @Override
    public String toString() {
        return title;
    }

}
