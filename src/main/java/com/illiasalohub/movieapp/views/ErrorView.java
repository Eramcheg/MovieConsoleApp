package com.illiasalohub.movieapp.views;

/**
 * A view class responsible for displaying error messages to the user.
 * .
 */
public class ErrorView {
    /**
     * Displays an error message to the user.
     * This method formats the error message with a prefix to distinguish it as an error.
     *
     * @param message the error message to display
     */
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}
