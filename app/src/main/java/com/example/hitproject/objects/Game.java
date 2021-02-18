package com.example.hitproject.objects;

import java.io.Serializable;

public class Game implements Serializable {

    private String name;
    private String platform;
    private String developer;
    private String distributor;
    private String trailer;
    private String description;
    private String genre;
    private String releaseYear;

    public Game() {
        name = "";
        platform = "";
        developer = "";
        distributor = "";
        trailer = "";
        description = "";
        genre = "";
        releaseYear = "";

    }

    public Game(String name, String platform, String developer, String distributor, String trailer, String description, String genre, String releaseYear) {
        this.name = name;
        this.platform = platform;
        this.developer = developer;
        this.distributor = distributor;
        this.trailer = trailer;
        this.description = description;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}
