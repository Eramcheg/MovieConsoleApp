package com.illiasalohub.movieapp;

import com.illiasalohub.movieapp.controller.MovieController;
import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.views.ErrorView;
import com.illiasalohub.movieapp.views.MovieView;

public class Main {
    public static void main(String[] args) {
        MovieList model = new MovieList();
        MovieView view = new MovieView();
        ErrorView errorView = new ErrorView();
        MovieController controller = new MovieController(model, view, errorView);
        controller.start();
    }
}