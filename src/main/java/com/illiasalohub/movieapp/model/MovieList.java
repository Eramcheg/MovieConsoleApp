package com.illiasalohub.movieapp.model;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> movies = new ArrayList<>();
    private DataProcessor dataManager = new DataProcessor();

    public MovieList() {
        List<Movie> loadedMovies = dataManager.loadMovies();
        if (loadedMovies != null) {
            this.movies = loadedMovies;
        } else {
            this.movies = new ArrayList<>();  // Initialize with an empty list if loadMovies returns null because of empty(for example) json
        }
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
