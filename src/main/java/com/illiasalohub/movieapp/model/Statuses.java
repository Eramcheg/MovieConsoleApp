package com.illiasalohub.movieapp.model;

public enum Statuses {
    WANT_TO_WATCH("Want to Watch"),
    WATCHING("Watching"),
    ALREADY_WATCHED("Already Watched");

    private String status;

    Statuses(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
