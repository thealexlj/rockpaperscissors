package com.alex.rockpaperscissors.domain;

import com.alex.rockpaperscissors.model.GameRules;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.RoundResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoundRulesTest {

    private final GameRules rules = new GameRules();

    @Test
    void rockVsRockIsDraw() {
        assertEquals(
                RoundResult.DRAW,
                rules.resolve(PlayType.ROCK, PlayType.ROCK)
        );
    }

    @Test
    void rockVsPaperPlayer2Wins() {
        assertEquals(
                RoundResult.PLAYER2WINS,
                rules.resolve(PlayType.ROCK, PlayType.PAPER)
        );
    }

    @Test
    void rockVsScissorsPlayer1Wins() {
        assertEquals(
                RoundResult.PLAYER1WINS,
                rules.resolve(PlayType.ROCK, PlayType.SCISSORS)
        );
    }
}
