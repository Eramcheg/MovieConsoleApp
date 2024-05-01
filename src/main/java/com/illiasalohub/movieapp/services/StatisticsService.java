package com.illiasalohub.movieapp.services;

import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.model.StatisticsResult;

import java.util.*;

public class StatisticsService {
    public static StatisticsResult calculateTotalStatistics(List<Movie> movies) {
        if (movies.isEmpty()) {
            return null;
        }

        StatisticsResult result = new StatisticsResult();
        Map<String, Integer> directorCount = new HashMap<>();
        Movie oldestMovie = null;
        Movie newestMovie = null;
        Movie highestRatedMovie = null;
        Movie lowestRatedMovie = null;
        Set<String> genres = new HashSet<>();

        for (Movie movie : movies) {

            genres.add(movie.getGenre());

            directorCount.put(movie.getDirector(), directorCount.getOrDefault(movie.getDirector(), 0) + 1);

            if (oldestMovie == null || movie.getYear() < oldestMovie.getYear()) {
                oldestMovie = movie;
            }

            if (newestMovie == null || movie.getYear() > newestMovie.getYear()) {
                newestMovie = movie;
            }

            if (highestRatedMovie == null || movie.getRating() > highestRatedMovie.getRating()) {
                highestRatedMovie = movie;
            }

            if (lowestRatedMovie == null || movie.getRating() < lowestRatedMovie.getRating() && movie.getRating()>0.0) {
                lowestRatedMovie = movie;
            }
        }

        String mostWatchedDirector = Collections.max(directorCount.entrySet(), Map.Entry.comparingByValue()).getKey();

        result.setWatchedCount(movies.size());
        result.setOldestMovie(oldestMovie);
        result.setNewestMovie(newestMovie);
        result.setHighestRatedMovie(highestRatedMovie);
        result.setLowestRatedMovie(lowestRatedMovie);
        result.setMostWatchedDirector(mostWatchedDirector);
        result.setGenres(genres);

        return result;
    }

    public static Map<String, Integer> calculateGenreCounts(List<Movie> movies) {
        Map<String, Integer> genreCount = new HashMap<>();
        for (Movie movie : movies) {
            String genre = movie.getGenre();
            genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
        }
        return genreCount;
    }

    public static Map<String, Integer> calculateRatingStatistics(List<Movie> movies) {
        Map<String, Integer> ratingCounts = new TreeMap<>();
        for (Movie movie : movies) {
            double rating = movie.getRating();
            String key = ((int) rating) + " - " + ((int) rating + 1);
            ratingCounts.put(key, ratingCounts.getOrDefault(key, 0) + 1);
        }
        return ratingCounts;
    }

    public static Map<String, Integer> calculateDirectorStatistics(List<Movie> movies) {
        Map<String, Integer> directorCounts = new HashMap<>();
        for (Movie movie : movies) {
            String director = movie.getDirector();
            directorCounts.put(director, directorCounts.getOrDefault(director, 0) + 1);
        }
        return directorCounts;
    }
}
