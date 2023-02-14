package es.drodriguez.com.reproductormusicadanielrodriguez.models;

import java.io.Serializable;

public class Artist implements Serializable {
    private String artist_thumbnail;

    private String artist_background;

    private String mbid;

    private String artist_logo;

    private String name;

    private String artist_banner;

    private Integer id;

    public String getArtist_thumbnail() {
        return this.artist_thumbnail;
    }

    public void setArtist_thumbnail(String artist_thumbnail) {
        this.artist_thumbnail = artist_thumbnail;
    }

    public String getArtist_background() {
        return this.artist_background;
    }

    public void setArtist_background(String artist_background) {
        this.artist_background = artist_background;
    }

    public String getMbid() {
        return this.mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getArtist_logo() {
        return this.artist_logo;
    }

    public void setArtist_logo(String artist_logo) {
        this.artist_logo = artist_logo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist_banner() {
        return this.artist_banner;
    }

    public void setArtist_banner(String artist_banner) {
        this.artist_banner = artist_banner;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}