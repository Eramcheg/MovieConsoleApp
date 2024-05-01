package com.illiasalohub.movieapp.model;

/**
 * Enumerates the possible statuses for a user's added movie.
 * This helps to track the viewing status of each movie.
 */
public enum Statuses {
    WANT_TO_WATCH("Want to Watch"),
    WATCHING("Watching"),
    ALREADY_WATCHED("Already Watched");

    private String status;

    /**
     * Constructs a status with a specified display name.
     *
     * @param status The display name for the status.
     */
    Statuses(String status) {
        this.status = status;
    }

    /**
     * Returns the display name of this status.
     *
     * @return The string name of the status.
     */
    @Override
    public String toString() {
        return this.status;
    }
}
