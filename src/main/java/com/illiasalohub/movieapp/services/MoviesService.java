package com.illiasalohub.movieapp.services;

import com.illiasalohub.movieapp.model.DataProcessor;
import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.model.Statuses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MoviesService {
    private MovieList movieList;
    private DataProcessor dataManager;

    public MoviesService(DataProcessor dataManager) {
        this.dataManager = dataManager;
        List<Movie> loadedMovies = dataManager.loadMovies();
        this.movieList = new MovieList(loadedMovies != null ? loadedMovies : new ArrayList<>());
    }

    public void saveMovies() {
        dataManager.saveMovies(movieList.getMovies());
    }

    public void addMovie(Movie movie) {
        movieList.addMovie(movie);
        saveMovies();
    }

    public List<Movie> getMovies() {
        return movieList.getMovies();
    }

    public Movie getMovieById(int id){
        return movieList.getMovieById(id);
    }

    public boolean deleteMovie(int movieId) {
        boolean isDeleted = movieList.deleteMovieById(movieId);
        if (isDeleted) {
            saveMovies();
        }
        return isDeleted;
    }

    public List<Movie> filterByTitle(String title) {
        return movieList.getMovies().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByDirector(String director) {
        return movieList.getMovies().stream()
                .filter(m -> m.getDirector().toLowerCase().contains(director.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByGenre(String genre) {
        return movieList.getMovies().stream()
                .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByYearRange(int startYear, int endYear) {
        return movieList.getMovies().stream()
                .filter(m -> m.getYear() >= startYear && m.getYear() <= endYear)
                .collect(Collectors.toList());
    }

    public List<Movie> filterByStatus(Statuses status) {
        return movieList.getMovies().stream()
                .filter(m -> m.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Movie> filterByRatingRange(double minRating, double maxRating) {
        return movieList.getMovies().stream()
                .filter(m -> m.getRating() >= minRating && m.getRating() <= maxRating)
                .collect(Collectors.toList());
    }
}
