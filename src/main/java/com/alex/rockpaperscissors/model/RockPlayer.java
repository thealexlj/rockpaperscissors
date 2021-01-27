package com.alex.rockpaperscissors.model;

public class RockPlayer extends Player {

  @Override
  public Play throwRound() {
    return new Play(this, PlayType.ROCK);
  }
}
