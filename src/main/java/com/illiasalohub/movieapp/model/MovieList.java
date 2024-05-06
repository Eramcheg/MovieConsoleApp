package com.illiasalohub.movieapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of movies. This class provides methods for managing the list of movies.
 */
public class MovieList {
    public static int displayingType = 1;
    private List<Movie> movies;

    /**
     * Constructs a new MovieList.
     *
     * @param movies the initial list of movies to be managed
     */
    public MovieList(List<Movie> movies) {
        this.movies = new ArrayList<>(movies);
    }

    /**
     * Adds a new movie to the list.
     *
     * @param movie the movie to be added
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * Retrieves all movies in the list.
     *
     * @return a list of all movies
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Retrieves a movie by its unique id.
     *
     * @param id the ID of the movie to retrieve
     * @return the movie if found, otherwise null
     */
    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Removes a movie from the list by its id.
     *
     * @param id the ID of the movie to be removed
     * @return true if a movie was removed, false otherwise
     */
    public boolean deleteMovieById(int id) {
        return movies.removeIf(movie -> movie.getId() == id);
    }

}
