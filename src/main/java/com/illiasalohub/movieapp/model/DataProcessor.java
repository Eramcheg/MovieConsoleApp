package com.illiasalohub.movieapp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of movie data to and from a JSON file.
 * This class encapsulates the functionality required to serialize and deserialize
 * movie data using Gson library.
 */
public class DataProcessor {
    private String filePath = "movies.json";
    private Gson gson = new Gson();

    /**
     * Nested static class to represent the structure of the movie data stored in JSON.
     */
    private static class MovieData {
        int nextId;
        List<Movie> movies;
    }

    /**
     * Loads movies from the JSON file.
     * This method deserializes JSON content into movie objects and updates the static movie ID counter.
     *
     * @return A list of movie objects retrieved from the JSON file, or an empty list if an error occurs.
     */
    public List<Movie> loadMovies() {
        try (FileReader reader = new FileReader(filePath)) {
            Type dataType = new TypeToken<MovieData>(){}.getType();
            MovieData data = gson.fromJson(reader, dataType);
            Movie.setNextId(data.nextId);  // Set the static nextId in Movie with last Id saved to json
            return data.movies;
        } catch (IOException e) {
            System.err.println("Error loading movies from JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Saves a list of movies to the JSON file.
     * This method serializes the list of movies and their next ID into JSON format and writes it to the file.
     *
     * @param movies The list of movies to be saved.
     */
    public void saveMovies(List<Movie> movies) {
        try (FileWriter writer = new FileWriter(filePath)) {
            MovieData data = new MovieData();
            data.nextId = Movie.getNextId();
            data.movies = movies;
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error saving movies to JSON: " + e.getMessage());
        }
    }

}
