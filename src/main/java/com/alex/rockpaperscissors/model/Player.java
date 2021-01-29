package com.alex.rockpaperscissors.model;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Stores player info
 */
public class Player {

  private String id;

  public Player() {
    this.id = "Player_" + ThreadLocalRandom.current().nextInt();
  }

  public String getId() {
    return this.id;
  }

  ;
  private PlayType playType;

  public PlayType getPlayType() {
    return this.playType;
  }

  public void setPlayType(PlayType playType) {
    this.playType = playType;
  }

  /**
   * Throw the specified playType in a round
   * @return a Play with the specified PlayType
   */
  public Play throwRound() {
    Play play = new Play(this, this.playType);
    this.playType = null;
    return play;
  }

  /**
   * Throw the random playType in a round
   * @return a Play with a random PlayType
   */
  public Play throwRoundRandom() {
    return new Play(this, PlayType.fromInteger(ThreadLocalRandom.current().nextInt(0, 3)));
  }

  /**
   * Throw the rock playType in a round
   * @return a Play with a rock PlayType
   */
  public Play throwRoundRock() {
    return new Play(this, PlayType.ROCK);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return Objects.equals(id, player.id) && playType == player.playType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, playType);
  }
}
