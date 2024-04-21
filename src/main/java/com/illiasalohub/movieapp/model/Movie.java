package com.illiasalohub.movieapp.model;

public class Movie {
    private String title;
    private String director;
    private String genre;
    private int year;
    private double rating;
    private Statuses status;

    public Movie(String title, String director, String genre, int year, Statuses status, double rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.status = status;
    }
    public Movie(String title, String director, String genre, int year, Statuses status) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.status = status;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
