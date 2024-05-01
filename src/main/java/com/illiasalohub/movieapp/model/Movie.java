package com.illiasalohub.movieapp.model;

public class Movie {
    public static final int FIRST_MOVIE_YEAR = 1888;
    private static int nextId = 1;

    private int id;
    private String title;
    private String director;
    private String genre;
    private int year;
    private double rating;
    private Statuses status;

    /**
     * Constructs a new Movie with all attributes specified.
     *
     * @param title    The title of the movie
     * @param director The director of the movie
     * @param genre    The genre of the movie
     * @param year     The year the movie was released
     * @param status   The viewing status of the movie
     * @param rating   The rating of the movie
     */
    public Movie(String title, String director, String genre, int year, Statuses status, double rating) {
        this.id = nextId++;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.status = status;
    }

    /**
     * Constructs a new Movie without a rating for situations when status is not "Already watched".
     *
     * @param title    The title of the movie
     * @param director The director of the movie
     * @param genre    The genre of the movie
     * @param year     The year the movie was released
     * @param status   The viewing status of the movie
     */
    public Movie(String title, String director, String genre, int year, Statuses status) {
        this.id = nextId++;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.status = status;
    }

    //Getters and setters

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

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }
    public static void setNextId(int nextId) {
        Movie.nextId = nextId;
    }
}
