package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.model.Game;
import java.util.List;

public interface StoreService {

  void addGame(Game game);
  Game[] getGames();
}
