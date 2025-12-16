package com.alex.rockpaperscissors.domain;

import com.alex.rockpaperscissors.model.PlayType;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayTypeTest {

    @Test
    void randomShouldReturnValidValue() {
        for (int i = 0; i < 100; i++) {
            PlayType playType = PlayType.random();
            assertNotNull(playType);
            assertTrue(EnumSet.allOf(PlayType.class).contains(playType));
        }
    }
}

