package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.model.*;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GameService {

  private final StoreService storeService;
  private final GameRules gameRules;

  public GameService(StoreService storeService, GameRules gameRules) {
    this.storeService = storeService;
    this.gameRules = gameRules;
  }

  public Game createGame(Player player1, Player player2) {
    Game game = new Game(player1, player2);
    storeService.addGame(game);
    return game;
  }

  public Game playRound(Game game, PlayType player2Play) {
    Play play1 = game.getPlayer1().playRandom();
    Play play2 = game.getPlayer2().play(player2Play);

    RoundResult result = gameRules.resolve(
            play1.playType(),
            play2.playType()
    );

    game.addRound(new Round(play1, play2, result));
    storeService.addGame(game);
    return game;
  }

  public Collection<Game> getScores() {
    return storeService.findAll();
  }
}