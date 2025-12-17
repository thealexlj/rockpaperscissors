package com.alex.rockpaperscissors.model;

/**
 * Stores a round info
 */
public record Round(
        Play play1,
        Play play2,
        RoundResult result
) {

}
