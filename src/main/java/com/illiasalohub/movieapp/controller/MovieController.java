package com.illiasalohub.movieapp.controller;

import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.model.Statuses;
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
            movieView.displayStartMenu();
            choice = movieView.getUserInput();
            processChoice(choice);
        } while (choice != 5);
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                addNewMovie();
                break;
            case 2:
                displayMovies();
                break;
            case 3:
                System.out.println("statistics");
                break;
            case 4:
                System.out.println("settings");
                break;
            case 5:
                System.out.println("Exiting and saving... Goodbye!");
                break;
            default:
                movieView.displayError("Invalid choice, please try valid choice.");
        }
    }

    private void processMovieActionChoice(int choice) {
        switch (choice) {
            case 1:
                editMovie();
                break;
            case 2:
                //filterMovies();
                break;
            case 3:
                //orderMovies();
                break;
            case 4:
                //setDisplayType();
                break;
            case 5:
                //deleteMovie();
                break;
            case 6:
                // Return to the main menu
                return;
            default:
                movieView.displayError("Invalid choice, please select a valid option.");
                movieView.displayAllMoviesActionMenu();
                int actionChoice = movieView.getUserInput();
                processMovieActionChoice(actionChoice);
        }
    }

    public void addNewMovie() {
        Movie movie = movieView.promptNewMovie();
        if (movie == null) {
            System.out.println("Adding new movie canceled.");
            return;
        }
        movieList.addMovie(movie);
        movieList.saveMovies();  // Save after adding
        System.out.println("Movie added successfully!");
    }

    public void displayMovies() {

        List<Movie> movies = movieList.getMovies();
        movieView.displayMovies(movies);
        if (!movies.isEmpty()) {
            int actionChoice;
            do {
                movieView.displayAllMoviesActionMenu();
                actionChoice = movieView.getUserInput();
                processMovieActionChoice(actionChoice);
            } while (actionChoice != 6);

        }
    }

    public void editMovie() {
        int movieId = movieView.promptForMovieId();
        Movie movie = movieList.getMovieById(movieId);
        if (movie != null) {
            movieView.displayMovie(movie);
            int editChoice = movieView.getEditChoice(movie);
            processEditChoice(editChoice, movie);
        } else {
            movieView.displayError("Movie not found!");
        }
    }

    private void processEditChoice(int choice, Movie movie) {
        String field = "";
        switch (choice) {
            case 1:
                field = "title";
                movie.setTitle(movieView.getNewValue(field));
                break;
            case 2:
                field = "director";
                movie.setDirector(movieView.getNewValue(field));
                break;
            case 3:
                field = "genre";
                movie.setGenre(movieView.getNewValue(field));
                break;
            case 4: //Year
                int newYear = movieView.getMovieYear();
                movie.setYear(newYear);
                break;
            case 5: //Status
                processStatusEdit(movie);
                break;
            case 6: //Rating
                if (movie.getStatus() == Statuses.ALREADY_WATCHED) {
                    double newRating = movieView.processRating();
                    movie.setRating(newRating);
                } else {
                    System.out.println("Rating can only be set if the status is 'Already Watched'.");
                }
                break;
        }
        movieList.saveMovies();  // Save changes after edit
        System.out.println("Movie updated successfully.\n");
    }

    private void processStatusEdit(Movie movie) {
        Statuses newStatus = movieView.getStatusFromChoice();
        movie.setStatus(newStatus);
        if (newStatus == Statuses.ALREADY_WATCHED && movie.getRating() == 0.0) {
            double newRating = movieView.processRating();
            movie.setRating(newRating);
        } else if (newStatus != Statuses.ALREADY_WATCHED && movie.getRating() > 0.0) {
            movie.setRating(0.0);
        }
    }
}
