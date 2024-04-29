package com.illiasalohub.movieapp.views;

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
        return readIntSafe();
    }
    public void displayStartMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Add a new movie");
        System.out.println("2. Display all movies");
        System.out.println("3. Display statistics");
        System.out.println("4. Save and Exit");
    }

    public void displayAllMoviesActionMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Edit movie");
        System.out.println("2. Filter movies displaying");
        System.out.println("3. Order movies displaying");
        System.out.println("4. Set display type");
        System.out.println("5. Delete movie");
        System.out.println("6. Back to main menu");
    }

    public int getUserInput() {
        System.out.print("Enter your choice: ");
        return readIntSafe();
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }

    private boolean wantsToQuit(String input) {
        return "quit".equalsIgnoreCase(input.trim());
    }

    public Movie promptNewMovie() {

        String title="";
        while(title.isEmpty()){
            System.out.println("\nEnter movie title (or type 'quit' to exit): ");
            title = scanner.nextLine();
            if (wantsToQuit(title)) {
                return null;  // Signal that the user wants to quit
            }
            if(title.isEmpty()){
                System.out.println("Title cannot be empty!");
            }
        }


        System.out.print("Enter director: ");
        String director = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        int year = getMovieYear();

        Statuses status = getStatusFromChoice();

        if (status == Statuses.ALREADY_WATCHED) {
            double rating = processRating();
            return new Movie(title, director, genre, year, status, rating);
        } else {
            return new Movie(title, director, genre, year, status);
        }
    }

    public int getMovieYear() {
        int year;
        while (true) {
            System.out.print("Enter release year: ");
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year >= Movie.FIRST_MOVIE_YEAR) {
                    return year;
                } else {
                    System.out.println("Invalid year. First movie was created in " + Movie.FIRST_MOVIE_YEAR + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public int readIntSafe() {

        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
            }
        }
    }

    public Statuses getStatusFromChoice() {
        while (true) {  // Infinite loop to ensure input is received correctly
            System.out.println("Choose status (1- Want to Watch, 2- Watching, 3- Already Watched):");
            int choice = readIntSafe();

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

    public double processRating() {
        while (true) {
            System.out.println("Enter your rating (e.g., 9.5 or 9,5):");
            String ratingInput = scanner.nextLine().trim();
            try {
                ratingInput = ratingInput.replace(',', '.');
                double rating = Double.parseDouble(ratingInput);
                if (rating < 1 || rating > 10) {
                    System.out.println("Invalid rating. Please enter a rating between 1 and 10.");
                } else {
                    return rating;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating. Please enter a valid number.");
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
        String output;
        if (MovieList.displayingType == 1) {
            output = String.format("Id: %d\nTitle: %s\nDirector: %s\nGenre: %s\nYear: %d\nStatus: %s\n",
                    movie.getId(), movie.getTitle(), movie.getDirector(), movie.getGenre(), movie.getYear(),
                    movie.getStatus());
        } else {
            output = String.format("%d. %s, Directed by %s in %d\nGenre: %s\nStatus: %s\n",
                    movie.getId(), movie.getTitle(), movie.getDirector(),  movie.getYear(), movie.getGenre(),
                    movie.getStatus());
        }

        if(movie.getRating() > 0){
            output+= String.format("Rating: %.1f/10\n", movie.getRating());
        }
        return output;
    }

    public int promptForMovieId() {
        System.out.print("Write movie ID you want to edit: ");
        return readIntSafe();
    }

    public void displayMovie(Movie movie) {
        if (movie != null) {
            System.out.println("Current Movie Details:\n");
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Director: " + movie.getDirector());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println("Year: " + movie.getYear());
            System.out.println("Status: " + movie.getStatus());
            if(movie.getRating() != 0.0){
                System.out.println("Rating: " + movie.getRating());
            }

        } else {
            System.out.println("No movie found with the given ID.");
        }
    }

    public int getEditChoice(Movie movie) {
        System.out.println("What do you want to edit?");
        System.out.println("1. Title");
        System.out.println("2. Director");
        System.out.println("3. Genre");
        System.out.println("4. Year");
        System.out.println("5. Status");
        if(movie.getRating() != 0.0) {
            System.out.println("6. Rating");
        }
        System.out.println("Enter your choice:");

        return readIntSafe();
    }

    public String getNewValue(String field) {
        System.out.print("Enter new " + field + ": ");
        return scanner.nextLine();
    }

    public int getFilterChoice() {
        System.out.println("\nBy which field you want to filter?");
        System.out.println("1. Title");
        System.out.println("2. Director");
        System.out.println("3. Genre");
        System.out.println("4. Year Range");
        System.out.println("5. Status");
        System.out.println("6. Rating");
        System.out.println("Enter your choice:");

        return readIntSafe();
    }

    public String getFilterValue() {
        System.out.print("Enter filter value: ");
        return scanner.nextLine();
    }

    public int[] getYearRange() {
        System.out.print("Enter start year: ");
        int startYear = readIntSafe();
        System.out.print("Enter end year: ");
        int endYear = readIntSafe();
        return new int[]{startYear, endYear};
    }

    public double[] getRatingRange() {
        System.out.print("Enter minimum rating (1-10): ");
        double minRating = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum rating (1-10): ");
        double maxRating = Double.parseDouble(scanner.nextLine());
        return new double[]{minRating, maxRating};
    }

    public int getOrderChoice() {
        System.out.println("Choose the field to order movies by:");
        System.out.println("1. Title");
        System.out.println("2. Director");
        System.out.println("3. Genre");
        System.out.println("4. Year");
        System.out.println("5. Status");
        System.out.println("6. Rating");
        System.out.println("Enter your choice:");
        return readIntSafe();
    }

    public boolean getSortOrder() {
        System.out.println("Select sort order:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.println("Enter your choice:");
        int choice = readIntSafe();
        return choice != 2;
    }

    public void setDisplayType() {
        System.out.println("Choose the display format:");
        System.out.println("1. Detailed list format");
        System.out.println("2. Compact paragraph format");
        System.out.println("Enter your choice:");
        int choice = readIntSafe();
        if (choice == 1 || choice == 2) {
            MovieList.displayingType = choice;
            System.out.println("Display format set successfully.");
        } else {
            System.out.println("Invalid choice, defaulting to detailed list format.");
            MovieList.displayingType = 1;
        }
    }

    public int promptForMovieIdToDelete() {
        System.out.print("Enter the ID of the movie you want to delete: ");
        return readIntSafe();  // Uses a method to read the input safely as an integer
    }
}
