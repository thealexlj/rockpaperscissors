package com.alex.rockpaperscissors.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Stores a game with players and rounds
 */
public class Game {

  private Player player1;
  private Player player2;
  private final String id;
  private final List<Round> rounds;

  public Game() {
    this.id = "Game_" + ThreadLocalRandom.current().nextInt();
    this.rounds = new ArrayList<>();
  }

  public Game(Player player1, Player player2) {
    this.id = "Game_" + ThreadLocalRandom.current().nextInt();
    this.rounds = new ArrayList<>();
    this.player1 = player1;
    this.player2 = player2;
  }

  public Player getPlayer1() {
    return player1;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
  }

  public String getId() {
    return this.id;
  }

  public void addRound(Round round) {
    this.rounds.add(round);
  }

  ;

  public List<Round> getRounds() {
    return this.rounds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(player1, game.player1) && Objects
        .equals(player2, game.player2) && Objects.equals(id, game.id) && Objects
        .equals(rounds, game.rounds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(player1, player2, id, rounds);
  }
}
