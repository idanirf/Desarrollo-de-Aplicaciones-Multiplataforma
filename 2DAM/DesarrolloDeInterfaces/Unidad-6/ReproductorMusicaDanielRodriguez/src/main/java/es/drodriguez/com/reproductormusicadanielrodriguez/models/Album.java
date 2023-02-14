package es.drodriguez.com.reproductormusicadanielrodriguez.models;

import java.io.Serializable;

public class Album implements Serializable {
    private String mbid;

    private Integer year;

    private Artist artist;

    private Integer id;

    private String title;

    private Integer artist_id;

    private String picture;

    public String getMbid() {
        return this.mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Integer getArtist_id() {
        return this.artist_id;
    }

    public void setArtist_id(Integer artist_id) {
        this.artist_id = artist_id;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}