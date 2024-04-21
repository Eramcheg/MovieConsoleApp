package com.illiasalohub.movieapp.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    private String filePath = "movies.json";
    private Gson gson = new Gson();

    public List<Movie> loadMovies() {
        try (FileReader reader = new FileReader(filePath)) {
            Type movieListType = new TypeToken<List<Movie>>(){}.getType();
            return gson.fromJson(reader, movieListType);
        } catch (IOException e) {
            System.err.println("Error loading movies from JSON: " + e.getMessage());
            return new ArrayList<>();  // Return an empty list in case of failure
        }
    }

    public void saveMovies(List<Movie> movies) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(movies, writer);
        } catch (IOException e) {
            System.err.println("Error saving movies to JSON: " + e.getMessage());
        }
    }

}
