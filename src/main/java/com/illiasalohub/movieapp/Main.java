package com.illiasalohub.movieapp;

import com.illiasalohub.movieapp.controller.MovieController;
import com.illiasalohub.movieapp.model.MovieList;
import com.illiasalohub.movieapp.view.MovieView;

public class Main {
    public static void main(String[] args) {
        MovieList model = new MovieList();
        MovieView view = new MovieView();
        MovieController controller = new MovieController(model, view);
        controller.start();
    }
}