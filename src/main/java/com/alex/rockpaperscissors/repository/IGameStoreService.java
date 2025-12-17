package com.alex.rockpaperscissors.repository;

import com.alex.rockpaperscissors.model.Game;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface to define storage of games
 */
public interface IGameStoreService {

  void addGame(Game game);

  Optional<Game> findById(UUID id);

  Collection<Game> findAll();
}
