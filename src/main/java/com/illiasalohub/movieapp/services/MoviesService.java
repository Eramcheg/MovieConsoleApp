package com.illiasalohub.movieapp.services;

import com.illiasalohub.movieapp.model.DataProcessor;
import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.model.Statuses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing movies.
 * This class provides functionality to add, delete, retrieve, and filter movies,
 * as well as persist changes to storage.
 */
public class MoviesService {
    private MovieList movieList;
    private DataProcessor dataManager;

    /**
     * Constructs a MoviesService with a data processor for handling movie data.
     *
     * @param dataManager the data processor to be used for loading and saving movies.
     */
    public MoviesService(DataProcessor dataManager) {
        this.dataManager = dataManager;
        List<Movie> loadedMovies = dataManager.loadMovies();
        this.movieList = new MovieList(loadedMovies != null ? loadedMovies : new ArrayList<>());
    }

    /**
     * Saves the movies to the data storage.
     */
    public void saveMovies() {
        dataManager.saveMovies(movieList.getMovies());
    }

    /**
     * Adds a movie to the collection and saves the updated collection to storage.
     *
     * @param movie the movie to add.
     */
    public void addMovie(Movie movie) {
        movieList.addMovie(movie);
        saveMovies();
    }

    /**
     * Retrieves all movies from the collection.
     *
     * @return a list of all movies.
     */
    public List<Movie> getMovies() {
        return movieList.getMovies();
    }

    /**
     * Retrieves a movie by its id.
     *
     * @param id the ID of the movie to retrieve.
     * @return the movie if found, otherwise null.
     */
    public Movie getMovieById(int id){
        return movieList.getMovieById(id);
    }

    /**
     * Deletes a movie from the collection by its ID and saves the updated collection if the deletion was successful.
     *
     * @param movieId the ID of the movie to delete.
     * @return true if the movie was successfully deleted, false otherwise.
     */
    public boolean deleteMovie(int movieId) {
        boolean isDeleted = movieList.deleteMovieById(movieId);
        if (isDeleted) {
            saveMovies();
        }
        return isDeleted;
    }

    /**
     * Filters movies by title.
     *
     * @param title the title to filter by.
     * @return a list of movies that contain the specified title in their titles.
     */
    public List<Movie> filterByTitle(String title) {
        return movieList.getMovies().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filters movies by director.
     *
     * @param director the director to filter by.
     * @return a list of movies that have the specified director.
     */
    public List<Movie> filterByDirector(String director) {
        return movieList.getMovies().stream()
                .filter(m -> m.getDirector().toLowerCase().contains(director.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filters movies by genre.
     *
     * @param genre the genre to filter by.
     * @return a list of movies that belong to the specified genre.
     */
    public List<Movie> filterByGenre(String genre) {
        return movieList.getMovies().stream()
                .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Filters movies within a specified range of years.
     *
     * @param startYear the starting year of the range.
     * @param endYear the ending year of the range.
     * @return a list of movies that were released within the specified year range.
     */
    public List<Movie> filterByYearRange(int startYear, int endYear) {
        return movieList.getMovies().stream()
                .filter(m -> m.getYear() >= startYear && m.getYear() <= endYear)
                .collect(Collectors.toList());
    }

    /**
     * Filters movies by their status.
     *
     * @param status the status to filter by.
     * @return a list of movies that have the specified status.
     */
    public List<Movie> filterByStatus(Statuses status) {
        return movieList.getMovies().stream()
                .filter(m -> m.getStatus() == status)
                .collect(Collectors.toList());
    }

    /**
     * Filters movies by a range of ratings.
     *
     * @param minRating the minimum rating of the range.
     * @param maxRating the maximum rating of the range.
     * @return a list of movies that have ratings within the specified range.
     */
    public List<Movie> filterByRatingRange(double minRating, double maxRating) {
        return movieList.getMovies().stream()
                .filter(m -> m.getRating() >= minRating && m.getRating() <= maxRating)
                .collect(Collectors.toList());
    }
}
