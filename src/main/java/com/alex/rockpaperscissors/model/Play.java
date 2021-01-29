package com.alex.rockpaperscissors.model;

import java.util.Objects;

/**
 * Stores a Play associated with a player
 */
public class Play {

  Player player;
  PlayType playType;

  public Play(Player player, PlayType playType) {
    this.player = player;
    this.playType = playType;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public PlayType getPlayType() {
    return playType;
  }

  public void setPlayType(PlayType playType) {
    this.playType = playType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Play play = (Play) o;
    return Objects.equals(player, play.player) && playType == play.playType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(player, playType);
  }
}
