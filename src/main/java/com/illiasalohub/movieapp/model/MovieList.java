package com.illiasalohub.movieapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> filterByTitle(String title) {
        return movies.stream()
                .filter(m -> m.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByDirector(String director) {
        return movies.stream()
                .filter(m -> m.getDirector().toLowerCase().contains(director.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByGenre(String genre) {
        return movies.stream()
                .filter(m -> m.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> filterByYearRange(int startYear, int endYear) {
        return movies.stream()
                .filter(m -> m.getYear() >= startYear && m.getYear() <= endYear)
                .collect(Collectors.toList());
    }

    public List<Movie> filterByStatus(Statuses status) {
        return movies.stream()
                .filter(m -> m.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Movie> filterByRatingRange(double minRating, double maxRating) {
        return movies.stream()
                .filter(m -> m.getRating() >= minRating && m.getRating() <= maxRating)
                .collect(Collectors.toList());
    }
}
