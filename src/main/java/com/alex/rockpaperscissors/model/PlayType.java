package com.alex.rockpaperscissors.model;

public enum PlayType {
  ROCK,
  PAPER,
  SCISSORS;

  public static PlayType fromInteger(int x) {
    switch (x) {
      case 0:
        return ROCK;
      case 1:
        return PAPER;
      case 2:
        return SCISSORS;
    }
    return null;
  }

  public static int toInteger(PlayType playType) {
    switch (playType) {
      case ROCK:
        return 0;
      case PAPER:
        return 1;
      case SCISSORS:
        return 2;
    }
    return -1;
  }
}
