package com.illiasalohub.movieapp.controller;

import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.model.StatisticsResult;
import com.illiasalohub.movieapp.model.Statuses;
import com.illiasalohub.movieapp.services.MoviesService;
import com.illiasalohub.movieapp.services.StatisticsService;
import com.illiasalohub.movieapp.views.MainView;
import com.illiasalohub.movieapp.views.ErrorView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {
    private MoviesService moviesService;
    private MainView mainView;
    private ErrorView errorView;
    public MainController(MainView mainView, ErrorView errorView, MoviesService moviesService) {
        this.mainView = mainView;
        this.errorView = errorView;
        this.moviesService = moviesService;
    }

    public void start() {
        mainView.displayWelcomeMessage();
        int initialChoice = mainView.getInitialUserInput();
        if (initialChoice == 2) {
            System.out.println("Exiting... Goodbye!");
            return; // Exit the program
        }
        int choice;
        do {
            mainView.displayStartMenu();
            choice = mainView.getUserInput();
            processStartChoice(choice);
        } while (choice != 4);
    }

    private void processStartChoice(int choice) {
        switch (choice) {
            case 1:
                addNewMovie();
                break;
            case 2:
                displayMovies();
                break;
            case 3:
                System.out.println("statistics");
                statistics();
                break;
            case 4:
                System.out.println("Exiting and saving... Goodbye!");
                break;
            default:
                errorView.displayError("Invalid choice, please try valid choice.");
        }
    }

    private void processMovieActionChoice(int choice) {
        switch (choice) {
            case 1:
                editMovie();
                break;
            case 2:
                filterMovies();
                break;
            case 3:
                orderMovies();
                break;
            case 4:
                mainView.setDisplayType();
                break;
            case 5:
                deleteMovie();
                break;
            case 6:
                // Return to the main menu
                break;
            default:
                errorView.displayError("Invalid choice, please select a valid option.");
        }
    }

    private void processStatisticsChoice(int choice) {
        switch (choice) {
            case 1:
                processTotalStatistics();
                break;
            case 2:
                processGenreStatistics();
                break;
            case 3:
                processRatingStatistics();
                break;
            case 4:
                processDirectorsStatistics();
                break;
            case 5:
                // Return to the main menu
                break;
            default:
                errorView.displayError("Invalid choice, please select a valid option.");

        }
    }



    public void addNewMovie() {
        Movie movie = mainView.promptNewMovie();
        if (movie == null) {
            errorView.displayError("Adding new movie canceled.");
            return;
        }
        moviesService.addMovie(movie);
        moviesService.saveMovies();  // Save after adding
        System.out.println("Movie added successfully!");
    }

    public void displayMovies() {
        List<Movie> movies = moviesService.getMovies();
        mainView.displayMovies(movies);
        if (!movies.isEmpty()) {
            int actionChoice;
            do {
                mainView.displayAllMoviesActionMenu();
                actionChoice = mainView.getUserInput();
                processMovieActionChoice(actionChoice);
            } while (actionChoice != 6);

        }
    }

    public void statistics(){
        List<Movie> movies = moviesService.getMovies();
        if (!movies.isEmpty()) {
            int actionChoice;
            do {
                mainView.displayStatisticsMenu();
                actionChoice = mainView.getUserInput();
                processStatisticsChoice(actionChoice);
            } while (actionChoice != 5);
        }
    }







    public void editMovie() {
        int movieId = mainView.promptForMovieId();
        Movie movie = moviesService.getMovieById(movieId);
        if (movie != null) {
            mainView.displayMovie(movie);
            int editChoice = mainView.getEditChoice(movie);
            processEditChoice(editChoice, movie);
        } else {
            errorView.displayError("Movie not found!");
        }
    }

    public void filterMovies() {
        int filterChoice = mainView.getFilterChoice();
        List<Movie> filteredMovies;

        switch (filterChoice) {
            case 1:  // Title
                String title = mainView.getFilterValue();
                filteredMovies = moviesService.filterByTitle(title);
                break;
            case 2:  // Director
                String director = mainView.getFilterValue();
                filteredMovies = moviesService.filterByDirector(director);
                break;
            case 3:  // Genre
                String genre = mainView.getFilterValue();
                filteredMovies = moviesService.filterByGenre(genre);
                break;
            case 4:  // Year Range
                int[] years = mainView.getYearRange();
                filteredMovies = moviesService.filterByYearRange(years[0], years[1]);
                break;
            case 5:  // Status
                Statuses status = Statuses.values()[mainView.readIntSafe() - 1];
                filteredMovies = moviesService.filterByStatus(status);
                break;
            case 6:  // Rating
                double[] ratings = mainView.getRatingRange();
                filteredMovies = moviesService.filterByRatingRange(ratings[0], ratings[1]);
                break;
            default:
                errorView.displayError("Invalid choice, please select a valid option.");
                return;
        }

        mainView.displayMovies(filteredMovies);
    }

    public void orderMovies() {
        int orderChoice = mainView.getOrderChoice();
        boolean isAscending = mainView.getSortOrder();
        List<Movie> orderedMovies = moviesService.getMovies();

        Comparator<Movie> comparator;
        switch (orderChoice) {
            case 1:
                comparator = Comparator.comparing(Movie::getTitle);
                break;
            case 2:
                comparator = Comparator.comparing(Movie::getDirector);
                break;
            case 3:
                comparator = Comparator.comparing(Movie::getGenre);
                break;
            case 4:
                comparator = Comparator.comparingInt(Movie::getYear);
                break;
            case 5:
                comparator = Comparator.comparing(Movie::getStatus);
                break;
            case 6:
                comparator = Comparator.comparingDouble(Movie::getRating);
                break;
            default:
                errorView.displayError("Invalid choice. Please select a valid option.");
                return;
        }

        if (!isAscending) {
            comparator = comparator.reversed();
        }

        orderedMovies.sort(comparator);
        mainView.displayMovies(orderedMovies);
    }

    public void deleteMovie() {
        int movieId = mainView.promptForMovieIdToDelete();
        boolean isDeleted = moviesService.deleteMovie(movieId);
        if (isDeleted) {
            System.out.println("Movie deleted successfully.");
            moviesService.saveMovies();
        } else {
            errorView.displayError("No movie found with ID: " + movieId + ". Deletion failed.");
        }
    }

    private void processEditChoice(int choice, Movie movie) {
        String field = "";
        switch (choice) {
            case 1:
                field = "title";
                movie.setTitle(mainView.getNewValue(field));
                break;
            case 2:
                field = "director";
                movie.setDirector(mainView.getNewValue(field));
                break;
            case 3:
                field = "genre";
                movie.setGenre(mainView.getNewValue(field));
                break;
            case 4: //Year
                int newYear = mainView.getMovieYear();
                movie.setYear(newYear);
                break;
            case 5: //Status
                processStatusEdit(movie);
                break;
            case 6: //Rating
                if (movie.getStatus() == Statuses.ALREADY_WATCHED) {
                    double newRating = mainView.processRating();
                    movie.setRating(newRating);
                } else {
                    System.out.println("Rating can only be set if the status is 'Already Watched'.");
                }
                break;
        }
        moviesService.saveMovies();  // Save changes after edit
        System.out.println("Movie updated successfully.\n");
    }

    private void processStatusEdit(Movie movie) {
        Statuses newStatus = mainView.getStatusFromChoice();
        movie.setStatus(newStatus);
        if (newStatus == Statuses.ALREADY_WATCHED && movie.getRating() == 0.0) {
            double newRating = mainView.processRating();
            movie.setRating(newRating);
        } else if (newStatus != Statuses.ALREADY_WATCHED && movie.getRating() > 0.0) {
            movie.setRating(0.0);
        }
    }

    private void processTotalStatistics(){
        List<Movie> movies = moviesService.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        StatisticsResult stats = StatisticsService.calculateTotalStatistics(movies);
        mainView.statisticDisplay().displayStatistics(stats);
    }

    public void processGenreStatistics() {
        List<Movie> movies = moviesService.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        Map<String, Integer> genreCounts = StatisticsService.calculateGenreCounts(movies);
        mainView.statisticDisplay().displayGenreStatistics(genreCounts);
    }

    public void processRatingStatistics() {
        List<Movie> movies = moviesService.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        Map<String, Integer> ratingStatistics  = StatisticsService.calculateRatingStatistics(movies);
        mainView.statisticDisplay().displayRatingStatistics(ratingStatistics);
    }

    public void processDirectorsStatistics() {
        List<Movie> movies = moviesService.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        Map<String, Integer> directorsStatistics  = StatisticsService.calculateDirectorStatistics(movies);
        mainView.statisticDisplay().displayDirectorStatistics(directorsStatistics);
    }
}
