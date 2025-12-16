package com.alex.rockpaperscissors.controller;

import com.alex.rockpaperscissors.service.GameService;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class RockPaperScissorsRestController {

  @Autowired
  private GameService controller;

  @GetMapping("getNewPlayer")
  public ResponseEntity<Player> getNewPlayer() {
    Player userPlayer = controller.getNewPlayer();
    return new ResponseEntity<>(userPlayer, HttpStatus.OK);
  }

  @PostMapping("getNewGame")
  public ResponseEntity<Game> getNewGame(@RequestBody Player player) {
    Player computerPlayer = controller.getNewPlayer();
    Game game = new Game(player, computerPlayer);
    controller.addNewGame(game);
    return new ResponseEntity<>(game, HttpStatus.OK);
  }

  @PostMapping("playRandomRound")
  public ResponseEntity<Game> playRandomRound(@RequestBody Game game) {
    controller.playRandomRound(game);
    return new ResponseEntity<>(game, HttpStatus.OK);
  }

  @PostMapping("playRockRound")
  public ResponseEntity<Game> playRockRound(@RequestBody Game game) {
    controller.playRockRound(game);
    return new ResponseEntity<>(game, HttpStatus.OK);
  }

  @GetMapping("getAllScores")
  public ResponseEntity<Game[]> getAllScores() {
    return new ResponseEntity<>(controller.getScores(), HttpStatus.OK);
  }
}
