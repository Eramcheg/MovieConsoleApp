package com.illiasalohub.movieapp;

import com.illiasalohub.movieapp.controller.MainController;
import com.illiasalohub.movieapp.model.DataProcessor;
import com.illiasalohub.movieapp.services.MoviesService;
import com.illiasalohub.movieapp.views.ErrorView;
import com.illiasalohub.movieapp.views.MainView;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        DataProcessor dataProcessor = new DataProcessor();
        MoviesService moviesService = new MoviesService(dataProcessor);
        ErrorView errorView = new ErrorView();
        MainController controller = new MainController(view, errorView, moviesService);
        controller.start();
    }
}