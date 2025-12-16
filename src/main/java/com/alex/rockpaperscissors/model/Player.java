package com.alex.rockpaperscissors.model;

import java.util.UUID;

/**
 * Stores player info
 */
public class Player {

  private final UUID id;

  public Player() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public Play play(PlayType type) {
    return new Play(this, type);
  }

  public Play playRandom() {
    return play(PlayType.random());
  }
}
