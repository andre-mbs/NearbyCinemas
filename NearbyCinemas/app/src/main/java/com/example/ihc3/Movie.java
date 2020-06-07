package com.example.ihc3;

public class Movie {
    private String name;
    private String imgUrl;
    private String description;
    private String genre;
    private int duration;
    private int nSessions;
    private double rating;

    public Movie(String name, String imgUrl, String description, String genre, int duration, int nSessions, double rating) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.nSessions = nSessions;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public int getnSessions() {
        return nSessions;
    }

    public double getRating() {
        return rating;
    }
}
