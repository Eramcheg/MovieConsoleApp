package com.illiasalohub.movieapp.controller;

import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.model.StatisticsResult;
import com.illiasalohub.movieapp.model.StatisticsCalculator;
import com.illiasalohub.movieapp.model.Statuses;
import com.illiasalohub.movieapp.views.MovieView;
import com.illiasalohub.movieapp.views.ErrorView;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MovieController {
    private MovieList movieList;
    private MovieView movieView;
    private ErrorView errorView;
    public MovieController(MovieList movieList, MovieView movieView, ErrorView errorView) {
        this.movieList = movieList;
        this.movieView = movieView;
        this.errorView = errorView;
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
                movieView.setDisplayType();
                break;
            case 5:
                deleteMovie();
                break;
            case 6:
                // Return to the main menu
                break;
            default:
                errorView.displayError("Invalid choice, please select a valid option.");
                movieView.displayAllMoviesActionMenu();
                int actionChoice = movieView.getUserInput();
                processMovieActionChoice(actionChoice);
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
                movieView.displayAllMoviesActionMenu();
                int actionChoice = movieView.getUserInput();
                processMovieActionChoice(actionChoice);
        }
    }

    public void addNewMovie() {
        Movie movie = movieView.promptNewMovie();
        if (movie == null) {
            errorView.displayError("Adding new movie canceled.");
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

    public void statistics(){
        List<Movie> movies = movieList.getMovies();
        if (!movies.isEmpty()) {
            int actionChoice;
            do {
                movieView.displayStatisticsMenu();
                actionChoice = movieView.getUserInput();
                processStatisticsChoice(actionChoice);
            } while (actionChoice != 5);
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
            errorView.displayError("Movie not found!");
        }
    }

    public void filterMovies() {
        int filterChoice = movieView.getFilterChoice();
        List<Movie> filteredMovies;

        switch (filterChoice) {
            case 1:  // Title
                String title = movieView.getFilterValue();
                filteredMovies = movieList.filterByTitle(title);
                break;
            case 2:  // Director
                String director = movieView.getFilterValue();
                filteredMovies = movieList.filterByDirector(director);
                break;
            case 3:  // Genre
                String genre = movieView.getFilterValue();
                filteredMovies = movieList.filterByGenre(genre);
                break;
            case 4:  // Year Range
                int[] years = movieView.getYearRange();
                filteredMovies = movieList.filterByYearRange(years[0], years[1]);
                break;
            case 5:  // Status
                Statuses status = Statuses.values()[movieView.readIntSafe() - 1];
                filteredMovies = movieList.filterByStatus(status);
                break;
            case 6:  // Rating
                double[] ratings = movieView.getRatingRange();
                filteredMovies = movieList.filterByRatingRange(ratings[0], ratings[1]);
                break;
            default:
                errorView.displayError("Invalid choice, please select a valid option.");
                return;
        }

        movieView.displayMovies(filteredMovies);
    }

    public void orderMovies() {
        int orderChoice = movieView.getOrderChoice();
        boolean isAscending = movieView.getSortOrder();
        List<Movie> orderedMovies = movieList.getMovies();

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
        movieView.displayMovies(orderedMovies);
    }

    public void deleteMovie() {
        int movieId = movieView.promptForMovieIdToDelete();
        boolean isDeleted = movieList.deleteMovieById(movieId);
        if (isDeleted) {
            System.out.println("Movie deleted successfully.");
            movieList.saveMovies();
        } else {
            errorView.displayError("No movie found with ID: " + movieId + ". Deletion failed.");
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

    private void processTotalStatistics(){
        List<Movie> movies = movieList.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        StatisticsResult stats = StatisticsCalculator.calculateTotalStatistics(movies);
        movieView.statisticDisplay().displayStatistics(stats);
    }

    public void processGenreStatistics() {
        List<Movie> movies = movieList.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        Map<String, Integer> genreCounts = StatisticsCalculator.calculateGenreCounts(movies);
        movieView.statisticDisplay().displayGenreStatistics(genreCounts);
    }

    public void processRatingStatistics() {
        List<Movie> movies = movieList.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        Map<String, Integer> ratingStatistics  = StatisticsCalculator.calculateRatingStatistics(movies);
        movieView.statisticDisplay().displayRatingStatistics(ratingStatistics);
    }

    public void processDirectorsStatistics() {
        List<Movie> movies = movieList.getMovies();
        if (movies.isEmpty()) {
            errorView.displayError("No movies available.");
            return;
        }
        Map<String, Integer> directorsStatistics  = StatisticsCalculator.calculateDirectorStatistics(movies);
        movieView.statisticDisplay().displayDirectorStatistics(directorsStatistics);
    }
}
