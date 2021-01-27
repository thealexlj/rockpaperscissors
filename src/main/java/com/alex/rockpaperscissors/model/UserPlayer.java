package com.alex.rockpaperscissors.model;

public class UserPlayer extends Player{

  private PlayType playType;

  public PlayType getPlayType(){
    return this.playType;
  }

  public void setPlayType(PlayType playType){
    this.playType = playType;
  }

  @Override
  public Play throwRound() {
    Play play = new Play(this, this.playType);
    this.playType = null;
    return play;
  }
}
