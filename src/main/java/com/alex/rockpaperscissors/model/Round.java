package com.alex.rockpaperscissors.model;

import java.util.Objects;

public class Round {
  private Play play1;
  private Play play2;
  private int result;

  public Round(){

  }

  public Round(Play play1, Play play2, int result) {
    this.play1 = play1;
    this.play2 = play2;
    this.result = result;
  }

  public Play getPlay1() {
    return play1;
  }

  public void setPlay1(Play play1) {
    this.play1 = play1;
  }

  public Play getPlay2() {
    return play2;
  }

  public void setPlay2(Play play2) {
    this.play2 = play2;
  }

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Round round = (Round) o;
    return result == round.result && Objects.equals(play1, round.play1) && Objects
        .equals(play2, round.play2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(play1, play2, result);
  }
}
