package com.illiasalohub.movieapp.view;

import java.util.Scanner;

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
}
