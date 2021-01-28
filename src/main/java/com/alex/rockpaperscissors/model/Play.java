package com.alex.rockpaperscissors.model;

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
}
