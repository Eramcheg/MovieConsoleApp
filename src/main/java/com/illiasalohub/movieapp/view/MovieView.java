package com.illiasalohub.movieapp.view;

import java.util.List;
import java.util.Scanner;

import com.illiasalohub.movieapp.model.Movie;
import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.model.Statuses;
public class MovieView {
    private Scanner scanner = new Scanner(System.in);

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Movie Management Console Application!\n");
        System.out.println("Choose an option by entering corresponding number:");
        System.out.println("1. Start work with the program");
        System.out.println("2. Quit");
    }
    public int getInitialUserInput() {
        System.out.print("Enter your choice to continue or quit: ");
        return scanner.nextInt();
    }
    public void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Add a new movie");
        System.out.println("2. Display all movies");
        System.out.println("3. Display statistics");
        System.out.println("4. Save and Exit");
    }

    public int getUserInput() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }

    public Movie promptNewMovie() {
        System.out.println("");
        scanner.nextLine();

        System.out.print("Enter movie title:");
        String title = scanner.nextLine();

        System.out.print("Enter director:");
        String director = scanner.nextLine();

        System.out.print("Enter genre:");
        String genre = scanner.nextLine();

        System.out.print("Enter release year:");
        int year = scanner.nextInt(); // InputMismatchException

        scanner.nextLine();

        Statuses status = getStatusFromChoice();

        if (status == Statuses.ALREADY_WATCHED) {
            double rating = processRating();
            return new Movie(title, director, genre, year, status, rating);
        } else {
            return new Movie(title, director, genre, year, status);
        }
    }

    private double processRating() {
        while (true) {
            System.out.println("Enter your rating (e.g., 9.5 or 9,5):");
            String ratingInput = scanner.nextLine().trim();
            try {
                ratingInput = ratingInput.replace(',', '.');
                return Double.parseDouble(ratingInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating. Please enter a valid number.");
            }
        }
    }

    private Statuses getStatusFromChoice() {
        while (true) {  // Infinite loop to ensure input is received correctly
            System.out.println("Choose status (1- Want to Watch, 2- Watching, 3- Already Watched):");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    return Statuses.WANT_TO_WATCH;
                case 2:
                    return Statuses.WATCHING;
                case 3:
                    return Statuses.ALREADY_WATCHED;
                default:
                    System.out.println("Invalid choice, try again.");  // Try again if invalid input
            }
        }
    }

    public void displayMovies(List<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("No movies to display.");
            return;
        }
        System.out.println("Displaying all movies:");
        for (Movie movie : movies) {
            System.out.println(movieDetails(movie));
        }
    }

    private String movieDetails(Movie movie) {
        return String.format("Title: %s, Director: %s, Genre: %s, Year: %d, Status: %s, Rating: %.1f",
                movie.getTitle(), movie.getDirector(), movie.getGenre(), movie.getYear(),
                movie.getStatus(), movie.getRating());
    }


}
