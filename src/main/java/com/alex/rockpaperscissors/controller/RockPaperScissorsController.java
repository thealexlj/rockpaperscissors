package com.alex.rockpaperscissors.controller;

import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.PairPlay;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.model.Round;
import com.alex.rockpaperscissors.model.RoundResult;
import com.alex.rockpaperscissors.service.StoreService;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
public class RockPaperScissorsController {

  private ConcurrentHashMap<PairPlay, RoundResult> results;

  @Autowired
  StoreService storeService;

  @PostConstruct
  private void init() {
    results = new ConcurrentHashMap<>();

    // This could be a configuration file on properties

    // Here we store the possible outcomes of a round so
    // we can access the result of the round instantly

    //DRAWS

    PairPlay rockDraw = new PairPlay(PlayType.ROCK, PlayType.ROCK);
    PairPlay paperDraw = new PairPlay(PlayType.PAPER, PlayType.PAPER);
    PairPlay scissorsDraws = new PairPlay(PlayType.SCISSORS, PlayType.SCISSORS);

    //PLAYER1 WINS

    PairPlay rockWins = new PairPlay(PlayType.ROCK, PlayType.SCISSORS);
    PairPlay paperWins = new PairPlay(PlayType.PAPER, PlayType.ROCK);
    PairPlay scissorsWins = new PairPlay(PlayType.SCISSORS, PlayType.PAPER);

    //PLAYER2 LOSES

    PairPlay rockLoses = new PairPlay(PlayType.ROCK, PlayType.PAPER);
    PairPlay paperLoses = new PairPlay(PlayType.PAPER, PlayType.SCISSORS);
    PairPlay scissorsLoses = new PairPlay(PlayType.SCISSORS, PlayType.ROCK);

    results.put(rockDraw, RoundResult.DRAW);
    results.put(paperDraw, RoundResult.DRAW);
    results.put(scissorsDraws, RoundResult.DRAW);

    results.put(rockWins, RoundResult.PLAYER1WINS);
    results.put(paperWins, RoundResult.PLAYER1WINS);
    results.put(scissorsWins, RoundResult.PLAYER1WINS);

    results.put(rockLoses, RoundResult.PLAYER2WINS);
    results.put(paperLoses, RoundResult.PLAYER2WINS);
    results.put(scissorsLoses, RoundResult.PLAYER2WINS);
  }

  @Bean
  @Scope("prototype")
  private Game getGame() {
    return new Game();
  }

  /**
   * Compare the plays of a round
   *
   * @param round
   * @return RoundResult result of the round
   */
  private RoundResult comparePlays(Round round) {
    return this.results
        .get(new PairPlay(round.getPlay1().getPlayType(), round.getPlay2().getPlayType()));
  }

  /**
   * Plays a random round by forcing player 2 to throw a random playtype
   * @param game
   */
  public synchronized void playRandomRound(Game game) {
    Round round = new Round(game.getPlayer1().throwRound(), game.getPlayer2().throwRoundRandom(),
        RoundResult.DRAW);
    round.setRoundResult(comparePlays(round));
    game.addRound(round);
    storeService.addGame(game);
  }

  /**
   * Plays a random round by forcing player 2 to throw a rock playtype
   * @param game
   */
  public synchronized void playRockRound(Game game) {
    Round round = new Round(game.getPlayer1().throwRound(), game.getPlayer2().throwRoundRock(),
        RoundResult.DRAW);
    round.setRoundResult(comparePlays(round));
    game.addRound(round);
    storeService.addGame(game);
  }

  public synchronized Player getNewPlayer() {
    return new Player();
  }

  public synchronized Game getNewGame(Player player1, Player player2) {
    Game game = getGame();
    game.setPlayer1(player1);
    game.setPlayer2(player2);
    storeService.addGame(game);
    return game;
  }

  public Game[] getScores() {
    return storeService.getGames();
  }

  public void addNewGame(Game game) {
    storeService.addGame(game);
  }

}
