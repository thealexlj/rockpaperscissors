package com.alex.rockpaperscissors.model;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameRules {

    private static final Map<PairPlay, RoundResult> RULES = Map.of(
            new PairPlay(PlayType.ROCK, PlayType.ROCK), RoundResult.DRAW,
            new PairPlay(PlayType.PAPER, PlayType.PAPER), RoundResult.DRAW,
            new PairPlay(PlayType.SCISSORS, PlayType.SCISSORS), RoundResult.DRAW,

            new PairPlay(PlayType.ROCK, PlayType.SCISSORS), RoundResult.PLAYER1WINS,
            new PairPlay(PlayType.PAPER, PlayType.ROCK), RoundResult.PLAYER1WINS,
            new PairPlay(PlayType.SCISSORS, PlayType.PAPER), RoundResult.PLAYER1WINS,

            new PairPlay(PlayType.ROCK, PlayType.PAPER), RoundResult.PLAYER2WINS,
            new PairPlay(PlayType.PAPER, PlayType.SCISSORS), RoundResult.PLAYER2WINS,
            new PairPlay(PlayType.SCISSORS, PlayType.ROCK), RoundResult.PLAYER2WINS
    );

    public RoundResult resolve(PlayType play1, PlayType play2) {
        return RULES.get(new PairPlay(play1, play2));
    }
}