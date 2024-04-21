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

    private static class MovieData {
        int nextId;
        List<Movie> movies;
    }

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
