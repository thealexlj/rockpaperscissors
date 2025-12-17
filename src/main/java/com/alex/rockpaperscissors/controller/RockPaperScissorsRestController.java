package com.alex.rockpaperscissors.controller;

import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.dto.CreateGameRequest;
import com.alex.rockpaperscissors.model.dto.PlayRoundRequest;
import com.alex.rockpaperscissors.model.dto.PlayRoundVsCpuRequest;
import com.alex.rockpaperscissors.service.GameService;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

/**
 * Rest Contoller of the app
 */
@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:4200")
public class RockPaperScissorsRestController {

  private final GameService gameService;
  private final PlayerService playerService;

  public RockPaperScissorsRestController(GameService gameService, PlayerService playerService) {
    this.gameService = gameService;
    this.playerService = playerService;
  }

  @PostMapping("/players")
  public ResponseEntity<Player> createPlayer(
          @RequestParam String name
  ) {
    Player player = playerService.createPlayer(name);
    return ResponseEntity.status(HttpStatus.CREATED).body(player);
  }

  @Operation(summary = "Get all players")
  @GetMapping("/players")
  public ResponseEntity<Collection<Player>> getAllPlayers() {
    Collection<Player> players = playerService.getPlayers();
    return ResponseEntity.ok(players);
  }

  @Operation(summary = "Create a new game")
  @PostMapping("/games")
  public ResponseEntity<Game> createGame(
          @RequestBody CreateGameRequest request
  ) {
    Game game = gameService.createGame(
            request.player1Id(),
            request.player2Id()
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(game);
  }

  @Operation(summary = "Create a new game vs. CPU")
  @PostMapping("/games/cpu")
  public ResponseEntity<Game> createGameVsCpu(
          @RequestParam UUID playerId
  ) {
    Game game = gameService.createGameVsCpu(playerId);
    return ResponseEntity.status(HttpStatus.CREATED).body(game);
  }

  @Operation(summary = "Play a round (player vs player)")
  @PostMapping("/games/{gameId}/rounds")
  public ResponseEntity<Game> playRoundPvp(
          @PathVariable UUID gameId,
          @RequestBody PlayRoundRequest request
  ) {
    Game updated = gameService.playRound(
            gameId,
            request.player1Id(),
            request.player1Play(),
            request.player2Id(),
            request.player2Play()
    );
    return ResponseEntity.ok(updated);
  }

  @Operation(summary = "Play a round against CPU")
  @PostMapping("/games/{gameId}/rounds/cpu")
  public ResponseEntity<Game> playRoundVsCpu(
          @PathVariable UUID gameId,
          @RequestBody PlayRoundVsCpuRequest request
  ) {
    Game updated = gameService.playRoundVsCpu(
            gameId,
            request.playerId(),
            request.playType()
    );
    return ResponseEntity.ok(updated);
  }

  @Operation(summary = "Get all games")
  @GetMapping
  public ResponseEntity<Collection<Game>> getAllGames() {
    return ResponseEntity.ok(gameService.getScores());
  }
}
