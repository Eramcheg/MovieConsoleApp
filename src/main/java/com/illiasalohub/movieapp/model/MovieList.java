package com.illiasalohub.movieapp.model;

import java.util.ArrayList;
import java.util.List;
public class MovieList {
    public static int displayingType = 1;
    private List<Movie> movies;

    public MovieList(List<Movie> movies) {
        this.movies = new ArrayList<>(movies);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public boolean deleteMovieById(int id) {
        return movies.removeIf(movie -> movie.getId() == id);
    }

}
