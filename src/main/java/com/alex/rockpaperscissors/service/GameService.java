package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.exception.GameNotFoundException;
import com.alex.rockpaperscissors.model.*;

import com.alex.rockpaperscissors.repository.IGameStoreService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains the business logic of the game
 */
@Service
public class GameService {

  private final IGameStoreService gameStoreService;
  private final GameRules gameRules;
  private final ConcurrentHashMap<UUID, Object> gameLocks = new ConcurrentHashMap<>();
  private final PlayerService playerService;

  public GameService(IGameStoreService gameStoreService, GameRules gameRules, PlayerService playerService) {
    this.gameStoreService = gameStoreService;
    this.gameRules = gameRules;
    this.playerService = playerService;
  }

  /**
   * Creates a game
   * @param player1Id
   * @param player2Id
   * @return Game a game ready to start
   */
  public Game createGame(UUID player1Id, UUID player2Id) {
    Player player1 = playerService.getPlayer(player1Id);
    Player player2 = playerService.getPlayer(player2Id);

    Game game = new Game(player1, player2);
    gameStoreService.addGame(game);

    // Initialize lock
    gameLocks.put(game.getId(), new Object());

    return game;
  }

  /**
   * Creates a game vs. Cpu player
   * @param playerId
   * @return Game a game vs cpu ready to start
   */
  public Game createGameVsCpu(UUID playerId) {
    Player cpu = playerService.getCpuPlayer();

    return this.createGame(playerId, cpu.getId());
  }

  /**
   * Plays a round for a given game
   * @param gameId
   * @param player1Id
   * @param player1Play
   * @param player2Id
   * @param player2Play
   * @return Game with the result of the round
   */
  public Game playRound(
          UUID gameId,
          UUID player1Id,
          PlayType player1Play,
          UUID player2Id,
          PlayType player2Play
  ) {
    Object lock = gameLocks.computeIfAbsent(gameId, id -> new Object());

    synchronized (lock) {
      Game game = getGame(gameId);

      Player p1 = playerService.getPlayer(player1Id);
      Player p2 = playerService.getPlayer(player2Id);

      Play play1 = p1.play(player1Play);
      Play play2 = p2.play(player2Play);

      RoundResult result = gameRules.resolve(
              play1.playType(),
              play2.playType()
      );

      game.addRound(new Round(play1, play2, result));
      gameStoreService.addGame(game);

      return game;
    }
  }

  /**
   * Plays a round vs. Cpu player
   * @param gameId
   * @param playerId
   * @param playerPlay
   * @return Game with the result of the round
   */
  public Game playRoundVsCpu(
          UUID gameId,
          UUID playerId,
          PlayType playerPlay
  ) {
      Player cpu = playerService.getCpuPlayer();

      return this.playRound(gameId, playerId, playerPlay, cpu.getId(), cpu.playRandom().playType());
  }


  /**
   * Retrieves a game given a gameId
   * @param gameId
   * @return Game The game with all the info
   * @throws GameNotFoundException
   */
  public Game getGame(UUID gameId) throws GameNotFoundException {
    return gameStoreService.findById(gameId).orElseThrow(() -> new GameNotFoundException("Game with id " + gameId + " not found"));
  }

  /**
   * Gets all the games played
   * @return Collection<Game> A collection with all the games
   */
  public Collection<Game> getScores() {
    return gameStoreService.findAll();
  }
}