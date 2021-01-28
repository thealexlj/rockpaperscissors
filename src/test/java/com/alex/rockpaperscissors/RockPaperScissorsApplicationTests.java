package com.alex.rockpaperscissors;

import com.alex.rockpaperscissors.controller.RockPaperScissorsController;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.Player;
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

  private Player player;

  @PostConstruct
  public void init() {
    player = controller.getNewPlayer();

    for (int i = 0; i < 20; i++) {
      Game game = new Game(player, controller.getNewPlayer());
      controller.addNewGame(game);
      playRounds(game);
    }
  }

  @Test
  void contextLoads() {
  }

  @Test
  void getRandomPlay() {
    List<PlayType> playTypes = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      playTypes.add(controller.getNewPlayer().throwRound().getPlayType());
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
    PlayType playType = controller.getNewPlayer().throwRound().getPlayType();
    assert (playType.equals(PlayType.ROCK));
  }

  @Test
  void getPlayerGames() {
    List<Game> games = controller.getScores(player);
    assert (!games.isEmpty() && games.get(0).getPlayer1().equals(player));
  }

  private void playRounds(Game game) {
    for(int i = 0; i < 5; i++){
      controller.playRandomRound(game);
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
