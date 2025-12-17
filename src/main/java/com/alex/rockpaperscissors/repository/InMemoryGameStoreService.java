package com.alex.rockpaperscissors.repository;

import com.alex.rockpaperscissors.model.Game;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

/**
 * Implementation of GameStoreService in local memory
 */
@Repository
public class InMemoryGameStoreService implements IGameStoreService {

  private final ConcurrentHashMap<UUID, Game> games = new ConcurrentHashMap<>();

  @Override
  public void addGame(Game game) {
    games.put(game.getId(), game);
  }

  @Override
  public Optional<Game> findById(UUID id) {
    return Optional.ofNullable(games.get(id));
  }

  @Override
  public Collection<Game> findAll() {
    return games.values();
  }
}