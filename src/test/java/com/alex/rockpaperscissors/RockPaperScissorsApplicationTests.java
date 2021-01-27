package com.alex.rockpaperscissors;

import com.alex.rockpaperscissors.controller.RockPaperScissorsController;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.model.Round;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RockPaperScissorsApplicationTests {

  @Autowired
  RockPaperScissorsController controller;

  @PostConstruct
  public void init() {

  }

  @Test
  void contextLoads() {
  }

  @Test
  void getRandomPlay() {
    List<PlayType> playTypes = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      playTypes.add(controller.getNewRandomPlayer().throwRound().getPlayType());
    }

    boolean allRock = true;
    boolean allPaper = true;
    boolean allScissors = true;

    for (PlayType playType : playTypes) {
      if (allRock) {
        allRock = !playType.equals(PlayType.ROCK);
      }
      ;
      if (allPaper) {
        allPaper = !playType.equals(PlayType.PAPER);
      }
      ;
      if (allScissors) {
        allScissors = !playType.equals(PlayType.SCISSORS);
      }
      ;
    }
    assert (!allRock && !allPaper && !allScissors);

  }

  @Test
  void getRockPlay() {
    PlayType playType = controller.getNewRockPlayer().throwRound().getPlayType();
    assert (playType.equals(PlayType.ROCK));
  }

  @Test
  void getPlayerGames() {
    Player player = controller.getNewRandomPlayer();

    for (int i = 0; i < 20; i++) {
      Game game = new Game(player, controller.getNewRandomPlayer());
      controller.addNewGame(game);
      playRounds(game);
    }

    List<Game> games = controller.getScores(player);
    System.out.println(games.toString());
    assert (!games.isEmpty() && games.get(0).getPlayer1().equals(player));
  }

  private void playRounds(Game game) {
    Round round = controller.playRound(game);
    game.addRound(round);

    while (round.getResult() < 0) {
      round = controller.playRound(game);
      game.addRound(round);
    }
    Round finalRound = game.getRounds().get(game.getRounds().size()-1);

    if (finalRound.getPlay1().getPlayType().equals(PlayType.fromInteger(finalRound.getResult()))) {
      game.setWinner(finalRound.getPlay1().getPlayer());
    } else {
      game.setWinner(finalRound.getPlay2().getPlayer());
    }
  }

  @Test
  void getAllGames() {
    List<Game> games = controller.getScores();
    assert (games.size() > 0);
  }

  @Test
  void computePlays() {

  }

  @Test
  void createGame() {

  }

  @Test
  void setWinner() {

  }

  @Test
  void winnerIsCorrect(){

  }

}
