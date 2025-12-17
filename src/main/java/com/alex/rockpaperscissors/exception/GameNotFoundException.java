package com.alex.rockpaperscissors.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exception when a game hasn't been found in the storage
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public GameNotFoundException(String message) {
        super(message);
    }
}
