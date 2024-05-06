package com.illiasalohub.movieapp;

import com.illiasalohub.movieapp.controller.MainController;
import com.illiasalohub.movieapp.model.DataProcessor;
import com.illiasalohub.movieapp.services.MoviesService;
import com.illiasalohub.movieapp.views.ErrorView;
import com.illiasalohub.movieapp.views.MainView;

public class Main {

    /**
     * Initializes and starts the movie management application.
     * Sets up the view, controller, service, and data processor, and then starts the main interaction loop.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        MainView view = new MainView();
        DataProcessor dataProcessor = new DataProcessor();
        MoviesService moviesService = new MoviesService(dataProcessor);
        ErrorView errorView = new ErrorView();
        MainController controller = new MainController(view, errorView, moviesService);
        controller.start();
    }
}