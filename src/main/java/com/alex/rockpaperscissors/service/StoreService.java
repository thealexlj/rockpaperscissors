package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.model.Game;

public interface StoreService {

  void addGame(Game game);
  Game[] getGames();
}
