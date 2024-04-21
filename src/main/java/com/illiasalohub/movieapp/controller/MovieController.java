package com.illiasalohub.movieapp.controller;

import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.view.MovieView;

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
                // Logic to add a movie
                System.out.println("add movie");
                break;
            case 2:
//                movieView.displayMovies(movieList.getMovies());
                System.out.println("display movies");
                break;
            case 3:
                // Display statistics
                System.out.println("statistics");
                break;
            case 4:
//                movieList.saveMovies();  // Assuming there's a method to save movies
                System.out.println("Exiting and saving... Goodbye!");
                break;
            default:
                movieView.displayError("Invalid choice, please try valid choice.");
        }
    }
}
