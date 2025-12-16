package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.exception.GameNotFoundException;
import com.alex.rockpaperscissors.model.*;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

  private final StoreService storeService;
  private final GameRules gameRules;
  private final ConcurrentHashMap<UUID, Object> gameLocks = new ConcurrentHashMap<>();

  public GameService(StoreService storeService, GameRules gameRules) {
    this.storeService = storeService;
    this.gameRules = gameRules;
  }

  public Game createGame(Player player1, Player player2) {
    Game game = new Game(player1, player2);
    storeService.addGame(game);

    // Initialize lock
    gameLocks.put(game.getId(), new Object());

    return game;
  }

  public Game playRound(UUID gameId, PlayType player2Play) {

    //Retrieve the lock
    Object lock = gameLocks.computeIfAbsent(gameId, id -> new Object());

    //We lock the game so only one client can mutate the state of a game at the same time
    synchronized (lock) {
      Game game = getGame(gameId);

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
  }

  public Game getGame(UUID gameId) throws GameNotFoundException {
    return storeService.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game with id " + gameId + " not found"));
  }

  public Collection<Game> getScores() {
    return storeService.findAll();
  }
}