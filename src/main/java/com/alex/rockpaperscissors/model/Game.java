package com.alex.rockpaperscissors.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Stores a game with players and rounds
 */
public class Game {

  private final UUID id;
  private final Player player1;
  private final Player player2;
  private final List<Round> rounds = new ArrayList<>();

  public Game(Player player1, Player player2) {
    this.id = UUID.randomUUID();
    this.player1 = player1;
    this.player2 = player2;
  }

  public UUID getId() {
    return id;
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public List<Round> getRounds() {
    return List.copyOf(rounds);
  }

  public void addRound(Round round) {
    rounds.add(round);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof Game g) && id.equals(g.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
