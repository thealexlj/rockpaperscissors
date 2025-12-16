package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.model.Game;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface StoreService {

  void addGame(Game game);

  Optional<Game> findById(UUID id);

  Collection<Game> findAll();
}
