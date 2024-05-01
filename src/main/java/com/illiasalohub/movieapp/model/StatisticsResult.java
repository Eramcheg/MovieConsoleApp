package com.illiasalohub.movieapp.model;

import java.util.Set;

/**
 * Encapsulates the results of various statistical analyses of the user movies.
 * This class is used for information about total user statistics such as the count of watched movies, the oldest and newest movies,
 * the most watched director, and ratings statistics.
 */
public class StatisticsResult {
    private int watchedCount;
    private Movie oldestMovie;
    private Movie newestMovie;
    private String mostWatchedDirector;
    private Movie highestRatedMovie;
    private Movie lowestRatedMovie;
    private Set<String> genres;

    /*
     * Attributes getters and setters
     */

    public int getWatchedCount() {
        return watchedCount;
    }

    public void setWatchedCount(int watchedCount) {
        this.watchedCount = watchedCount;
    }

    public Movie getOldestMovie() {
        return oldestMovie;
    }

    public void setOldestMovie(Movie oldestMovie) {
        this.oldestMovie = oldestMovie;
    }

    public Movie getNewestMovie() {
        return newestMovie;
    }

    public void setNewestMovie(Movie newestMovie) {
        this.newestMovie = newestMovie;
    }

    public Movie getHighestRatedMovie() {
        return highestRatedMovie;
    }

    public void setHighestRatedMovie(Movie highestRatedMovie) {
        this.highestRatedMovie = highestRatedMovie;
    }

    public String getMostWatchedDirector() {
        return mostWatchedDirector;
    }

    public void setMostWatchedDirector(String mostWatchedDirector) {
        this.mostWatchedDirector = mostWatchedDirector;
    }

    public Movie getLowestRatedMovie() {
        return lowestRatedMovie;
    }

    public void setLowestRatedMovie(Movie lowestRatedMovie) {
        this.lowestRatedMovie = lowestRatedMovie;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }
}
