package com.illiasalohub.movieapp;

import com.illiasalohub.movieapp.controller.MainController;
import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.views.ErrorView;
import com.illiasalohub.movieapp.views.MainView;

public class Main {
    public static void main(String[] args) {
        MovieList model = new MovieList();
        MainView view = new MainView();
        ErrorView errorView = new ErrorView();
        MainController controller = new MainController(model, view, errorView);
        controller.start();
    }
}