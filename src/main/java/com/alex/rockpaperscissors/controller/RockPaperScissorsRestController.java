package com.alex.rockpaperscissors.controller;

import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.service.GameService;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.Player;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:4200")
public class RockPaperScissorsRestController {

  private final GameService gameService;

  public RockPaperScissorsRestController(GameService gameService) {
    this.gameService = gameService;
  }

  @Operation(summary = "Create a new game")
  @PostMapping
  public ResponseEntity<Game> createGame() {
    Game game = gameService.createGame(
            new Player(),
            new Player()
    );
    return ResponseEntity.ok(game);
  }

  @Operation(summary = "Play a round in a game")
  @PostMapping("/{id}/rounds")
  public ResponseEntity<Game> playRound(
          @PathVariable UUID id,
          @RequestParam PlayType playType
  ) {
      Game game = gameService.getGame(id);
      Game updated = gameService.playRound(game.getId(), playType);
    return ResponseEntity.ok(updated);
  }

  @Operation(summary = "Get all games")
  @GetMapping
  public ResponseEntity<Collection<Game>> getAllGames() {
    return ResponseEntity.ok(gameService.getScores());
  }
}
