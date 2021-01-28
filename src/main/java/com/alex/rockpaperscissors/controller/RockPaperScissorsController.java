package com.alex.rockpaperscissors.controller;

import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.PairPlay;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.model.Round;
import com.alex.rockpaperscissors.model.RoundResult;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
public class RockPaperScissorsController {

  private ConcurrentHashMap<String, Game> totalGames;
  private ConcurrentHashMap<PairPlay, RoundResult> results;

  @PostConstruct
  private void init() {
    totalGames = new ConcurrentHashMap<>();
    results = new ConcurrentHashMap<>();

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

  private synchronized RoundResult comparePlays(Round round) {
    return this.results
        .get(new PairPlay(round.getPlay1().getPlayType(), round.getPlay2().getPlayType()));
  }

  public synchronized void playRandomRound(Game game) {
    Round round = new Round(game.getPlayer1().throwRound(), game.getPlayer2().throwRoundRandom(),
        RoundResult.DRAW);
    round.setRoundResult(comparePlays(round));
    game.addRound(round);
    totalGames.put(game.getId(), game);
  }

  public synchronized void playRockRound(Game game) {
    Round round = new Round(game.getPlayer1().throwRound(), game.getPlayer2().throwRoundRock(),
        RoundResult.DRAW);
    round.setRoundResult(comparePlays(round));
    game.addRound(round);
    totalGames.put(game.getId(), game);
  }

  public synchronized Player getNewPlayer() {
    return new Player();
  }

  public synchronized Game getNewGame(Player player1, Player player2) {
    Game game = getGame();
    game.setPlayer1(player1);
    game.setPlayer2(player2);
    totalGames.put(game.getId(), game);
    return game;
  }

  public Game[] getScores() {
    Game[] result = totalGames.values().toArray(new Game[0]);
    return result;
  }

  public List<Game> getScores(Player player) {
    return totalGames.values().stream()
        .filter(game -> game.getPlayer1().equals(player) || game.getPlayer2().equals(player))
        .collect(
            Collectors.toList());
  }

  public List<Game> getScores(Game game) {
    return totalGames.values().stream().filter(g -> g.getId().equals(game.getId())).collect(
        Collectors.toList());
  }

  public void addNewGame(Game game) {
    this.totalGames.put(game.getId(), game);
  }

}
