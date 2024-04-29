package com.illiasalohub.movieapp.views;
import com.illiasalohub.movieapp.model.StatisticsResult;

import java.util.Map;
import java.util.TreeMap;

public class StatisticsView {
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
