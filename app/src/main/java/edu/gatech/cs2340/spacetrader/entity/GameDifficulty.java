package edu.gatech.cs2340.spacetrader.entity;

import android.icu.lang.UCharacter;

public enum GameDifficulty {
    B ("Beginner"),
    E ("Easy"),
    N ("Normal"),
    H ("Hard"),
    I ("Impossible");

    /** the full string representation of the difficulty */
    private final String difficulty;

    /**
     * Constructor for the enumeration
     *
     * @param difficulty   full name of the course
     */
    GameDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     *
     * @return   the full course name
     */
    public String getDifficulty() { return difficulty; }

    /**
     *
     * @return the display string representation of the course
     */
    public String toString() { return difficulty; }

}
