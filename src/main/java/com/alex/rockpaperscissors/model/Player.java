package com.alex.rockpaperscissors.model;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

  private String id;

  public Player(){
    this.id = "Player_"+ ThreadLocalRandom.current().nextInt();
  }

  public String getId(){
    return this.id;
  };
  private PlayType playType;

  public PlayType getPlayType(){
    return this.playType;
  }

  public void setPlayType(PlayType playType){
    this.playType = playType;
  }

  public Play throwRound() {
    Play play = new Play(this, this.playType);
    this.playType = null;
    return play;
  }

  public Play throwRoundRandom() {
    return new Play(this, PlayType.fromInteger(ThreadLocalRandom.current().nextInt(0, 3)));
  }

  public Play throwRoundRock() {
    return new Play(this, PlayType.ROCK);
  }
}
