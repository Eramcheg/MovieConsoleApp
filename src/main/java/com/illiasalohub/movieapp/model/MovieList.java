package com.illiasalohub.movieapp.model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> movies = new ArrayList<>();
    private DataProcessor dataManager = new DataProcessor();

    public MovieList() {
        this.movies = dataManager.loadMovies();  // Load movies at construction
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void saveMovies() {
        dataManager.saveMovies(movies);
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
