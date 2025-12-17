package com.alex.rockpaperscissors.exception;

import java.io.Serial;

/**
 * Exception when a Player could not been found
 */
public class PlayerNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
