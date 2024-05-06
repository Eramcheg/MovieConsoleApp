package com.illiasalohub.movieapp.views;
import com.illiasalohub.movieapp.model.StatisticsResult;

import java.util.Map;

/**
 * Handles the display of statistical data related to movies.
 * This class provides methods to visualize different statistical information about users movies.
 */
public class StatisticsView {

    /**
     * Displays a summary of total statistics about the movie collection.
     *
     * @param result The statistics result containing data to be displayed.
     */
    public void displayStatistics(StatisticsResult result) {
        System.out.println("\nStatistics Summary:");
        System.out.println("Total movies watched: " + result.getWatchedCount());
        System.out.println("Oldest movie: " + result.getOldestMovie().getTitle() + " (" + result.getOldestMovie().getYear() + ")");
        System.out.println("Newest movie: " + result.getNewestMovie().getTitle() + " (" + result.getNewestMovie().getYear() + ")");
        System.out.println("Most watched director: " + result.getMostWatchedDirector());
        System.out.println("Highest rated movie: " + result.getHighestRatedMovie().getTitle() + " (" + result.getHighestRatedMovie().getRating() + ")");
        System.out.println("Lowest rated movie: " + result.getLowestRatedMovie().getTitle() + " (" + result.getLowestRatedMovie().getRating() + ")");
        System.out.println("Genres covered:");
        for (String genre : result.getGenres()) {
            System.out.println("--" + genre);
        }
    }

    /**
     * Displays statistics about the distribution of genres across movies.
     *
     * @param genreStatistics A map of genre names to their occurrence counts.
     */
    public void displayGenreStatistics(Map<String, Integer> genreStatistics) {
        int maxCount = genreStatistics.values().stream().max(Integer::compare).orElse(0);
        int scale = Math.max(maxCount / 10, 1);

        System.out.println("Statistics by Genres:");
        genreStatistics.forEach((genre, count) -> {
            int height = count / scale;
            String bar = new String(new char[height]).replace("\0", "|");
            System.out.printf("%-20s | %s %d%n", genre, bar, count);
        });
    }

    /**
     * Displays statistics about movie ratings across different rating intervals.
     *
     * @param ratingStatistics A map where keys are rating intervals and values are counts of movies in those intervals.
     */
    public void displayRatingStatistics(Map<String, Integer> ratingStatistics) {
        int maxCount = ratingStatistics.values().stream().max(Integer::compare).orElse(0);
        int scale = Math.max(maxCount / 10, 1);

        System.out.println("Movie Ratings Statistics:");
        ratingStatistics.forEach((ratingRange, count) -> {
            int height = count / scale;
            String bar = new String(new char[height]).replace("\0", "|");
            System.out.printf("%-10s | %s %d%n", ratingRange, bar, count);
        });
    }

    /**
     * Displays statistics related to movie directors and the count of movies they have directed.
     *
     * @param directorStatistics A map of director names to the count of movies they directed.
     */
    public void displayDirectorStatistics(Map<String, Integer> directorStatistics) {
        int maxCount = directorStatistics.values().stream().max(Integer::compare).orElse(0);
        int scale = Math.max(maxCount / 10, 1);

        System.out.println("Statistics by Director:");
        directorStatistics.forEach((director, count) -> {
            int height = count / scale;
            String bar = new String(new char[height]).replace("\0", "|");
            System.out.printf("%-30s | %s %d%n", director, bar, count);
        });
    }

}
