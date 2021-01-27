package com.alex.rockpaperscissors.model;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Player {

  private String id;

  public Player(){
    this.id = "Player_"+ ThreadLocalRandom.current().nextInt();
  }

  public String getId(){
    return this.id;
  };

  public Play throwRound(){
   return null;
  };
}
