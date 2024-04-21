package com.illiasalohub.movieapp.controller;

import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.view.MovieView;

import java.util.List;

public class MovieController {
    private MovieList movieList;
    private MovieView movieView;
    public MovieController(MovieList movieList, MovieView movieView) {
        this.movieList = movieList;
        this.movieView = movieView;
    }

    public void start() {
        movieView.displayWelcomeMessage();
        int initialChoice = movieView.getInitialUserInput();
        if (initialChoice == 2) {
            System.out.println("Exiting... Goodbye!");
            return; // Exit the program
        }
        int choice;
        do {
            movieView.displayMenu();
            choice = movieView.getUserInput();
            processChoice(choice);
        } while (choice != 4);
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                // Add a movie
                addNewMovie();
                break;
            case 2:
//                movieView.displayMovies(movieList.getMovies());
                displayMovies();
                break;
            case 3:
                // Display statistics
                System.out.println("statistics");
                break;
            case 4:
                // Display settings
                System.out.println("settings");
                break;
            case 5:
//                movieList.saveMovies();
                System.out.println("Exiting and saving... Goodbye!");
                break;
            default:
                movieView.displayError("Invalid choice, please try valid choice.");
        }
    }

    public void addNewMovie() {
        Movie movie = movieView.promptNewMovie();
        if (movie == null) {
            System.out.println("Adding new movie canceled.");
            return;
        }
        movieList.addMovie(movie);
        movieList.saveMovies();  // Save immediately after adding
        System.out.println("Movie added successfully!");
    }

    public void displayMovies() {

        List<Movie> movies = movieList.getMovies();
        movieView.displayMovies(movies);
    }
}
