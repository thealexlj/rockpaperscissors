package com.alex.rockpaperscissors.controller;

import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.PairPlay;
import com.alex.rockpaperscissors.model.Play;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.model.RandomPlayer;
import com.alex.rockpaperscissors.model.RockPlayer;
import com.alex.rockpaperscissors.model.Round;
import com.alex.rockpaperscissors.model.UserPlayer;
import java.util.ArrayList;
import java.util.Collections;
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
  private ConcurrentHashMap<PairPlay, Integer> results;

  @PostConstruct
  private void init(){
    totalGames = new ConcurrentHashMap<>();
    results = new ConcurrentHashMap<>();

    //DRAWS

    PairPlay rockDraw = new PairPlay(PlayType.ROCK, PlayType.ROCK);
    PairPlay paperDraw = new PairPlay(PlayType.PAPER, PlayType.PAPER);
    PairPlay scissorsDraws = new PairPlay(PlayType.SCISSORS, PlayType.SCISSORS);

    //WINS

    PairPlay rockWins = new PairPlay(PlayType.ROCK, PlayType.SCISSORS);
    PairPlay paperWins = new PairPlay(PlayType.PAPER, PlayType.ROCK);
    PairPlay scissorsWins = new PairPlay(PlayType.SCISSORS, PlayType.PAPER);

    //LOSES

    PairPlay rockLoses = new PairPlay(PlayType.ROCK, PlayType.PAPER);
    PairPlay paperLoses = new PairPlay(PlayType.PAPER, PlayType.SCISSORS);
    PairPlay scissorsLoses = new PairPlay(PlayType.SCISSORS, PlayType.ROCK);

    results.put(rockDraw, -1);
    results.put(paperDraw, -1);
    results.put(scissorsDraws, -1);

    results.put(rockWins, PlayType.toInteger(PlayType.ROCK));
    results.put(paperWins, PlayType.toInteger(PlayType.PAPER));
    results.put(scissorsWins, PlayType.toInteger(PlayType.SCISSORS));

    results.put(rockLoses, PlayType.toInteger(PlayType.PAPER));
    results.put(paperLoses, PlayType.toInteger(PlayType.SCISSORS));
    results.put(scissorsLoses, PlayType.toInteger(PlayType.ROCK));
  }

  @Bean
  @Scope("prototype")
  private Game getGame(){
    return new Game();
  }

  private synchronized int comparePlays(Round round){
    return this.results.get(new PairPlay(round.getPlay1().getPlayType(), round.getPlay2().getPlayType()));
  }

  public synchronized Round playRound(Game game){
    Round round = new Round(game.getPlayer1().throwRound(), game.getPlayer2().throwRound(), -1);
    round.setResult(comparePlays(round));
    return round;
  }

  public synchronized Player getNewRandomPlayer(){
    return new RandomPlayer();
  }

  public synchronized Player getNewRockPlayer(){
    return new RockPlayer();
  }

  public synchronized Player getNewUserPlayer(){
    return new UserPlayer();
  }

  public synchronized Game getNewGame(Player player1, Player player2) {
    Game game = getGame();
    game.setPlayer1(player1);
    game.setPlayer2(player2);
    totalGames.put(game.getId(), game);
    return game;
  }

  public synchronized void setGameWinner(String gameId, Player winner){
  }

  public synchronized List<Game> getScores(){
    return new ArrayList<>(totalGames.values());
  }

  public synchronized List<Game> getScores(Player player){
    return totalGames.values().stream().filter(game -> game.getPlayer1().equals(player) || game.getPlayer2().equals(player)).collect(
        Collectors.toList());
  }

  public void addNewGame(Game game){
    this.totalGames.put(game.getId(), game);
  }

}
