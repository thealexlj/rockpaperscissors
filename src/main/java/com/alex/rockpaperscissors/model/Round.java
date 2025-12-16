package com.alex.rockpaperscissors.model;

import java.util.Objects;

/**
 * Stores a round info
 */
public record Round(
        Play play1,
        Play play2,
        RoundResult result
) {

}
