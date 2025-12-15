package com.alex.rockpaperscissors;

import com.alex.rockpaperscissors.controller.GameService;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.model.Round;
import com.alex.rockpaperscissors.model.RoundResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RockPaperScissorsApplicationTests {

  @Autowired
  GameService controller;

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
      playTypes.add(controller.getNewPlayer().throwRoundRandom().getPlayType());
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
    PlayType playType = controller.getNewPlayer().throwRoundRock().getPlayType();
    assert (playType.equals(PlayType.ROCK));
  }

  private void playRounds(Game game) {
    for(int i = 0; i < 5; i++){
      game.getPlayer1().setPlayType(player.throwRoundRandom().getPlayType());
      controller.playRandomRound(game);
    }
  }

  @Test
  void getAllGames() {
    Game[] games = controller.getScores();
    assert (games.length > 0);
  }

  @Test
  void createGame() {
    Game game = controller.getNewGame(controller.getNewPlayer(), controller.getNewPlayer());
    assert(game!=null);
  }

  @Test
  void winnerIsCorrect(){
    ArrayList<Game> games = new ArrayList<>(Arrays.asList(controller.getScores()));

    for (Game game: games) {
      for(Round round: game.getRounds()) {
        if(round.getPlay1().getPlayType().equals(PlayType.ROCK) &&
          round.getPlay2().getPlayType().equals(PlayType.ROCK) &&
          !round.getRoundResult().equals(RoundResult.DRAW)) {
            assert(false);
        }

        if(round.getPlay1().getPlayType().equals(PlayType.ROCK) &&
            round.getPlay2().getPlayType().equals(PlayType.PAPER) &&
            !round.getRoundResult().equals(RoundResult.PLAYER2WINS)) {
          assert(false);
        }

        if(round.getPlay1().getPlayType().equals(PlayType.ROCK) &&
            round.getPlay2().getPlayType().equals(PlayType.SCISSORS) &&
            !round.getRoundResult().equals(RoundResult.PLAYER1WINS)) {
          assert(false);
        }
      }
    }
    assert(true);
  }

}
