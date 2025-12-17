package com.alex.rockpaperscissors.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Store playtypes for our game
 */
public enum PlayType {
  ROCK, PAPER, SCISSORS;

  /**
   * Returns a random playType of Rock, Paper or Scissors
   * @return
   */
  public static PlayType random() {
    return values()[ThreadLocalRandom.current().nextInt(values().length)];
  }
}
