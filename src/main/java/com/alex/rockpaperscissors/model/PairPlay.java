package com.alex.rockpaperscissors.model;

import java.util.Objects;

/**
 * Stores a pair of plays
 */
public class PairPlay {

  private PlayType playType1;
  private PlayType playType2;

  public PairPlay() {

  }

  public PairPlay(PlayType playType1, PlayType playType2) {
    this.playType1 = playType1;
    this.playType2 = playType2;
  }

  public PlayType getPlayType1() {
    return playType1;
  }

  public void setPlayType1(PlayType playType1) {
    this.playType1 = playType1;
  }

  public PlayType getPlayType2() {
    return playType2;
  }

  public void setPlayType2(PlayType playType2) {
    this.playType2 = playType2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PairPlay pairPlay = (PairPlay) o;
    return playType1 == pairPlay.playType1 && playType2 == pairPlay.playType2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(playType1, playType2);
  }
}
