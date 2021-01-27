package com.alex.rockpaperscissors.model;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayer extends Player{

  @Override
  public Play throwRound() {
    return new Play(this, PlayType.fromInteger(ThreadLocalRandom.current().nextInt(0, 3)));
  }
}
