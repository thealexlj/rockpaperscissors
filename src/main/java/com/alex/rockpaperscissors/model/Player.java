package com.alex.rockpaperscissors.model;

import java.util.UUID;

/**
 * Stores player info and behavior
 */
public class Player {

  private final UUID id;
  private final String name;

  public Player(String name) {
      this.name = name;
      this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public String getName() { return name; }

  /**
   * Plays a given type of Play
   * @param type
   * @return Play of the type given
   */
  public Play play(PlayType type) {
    return new Play(this, type);
  }

  /**
   * Plays a random type of Play
   * @return Play selected randomly
   */
  public Play playRandom() {
    return play(PlayType.random());
  }
}
