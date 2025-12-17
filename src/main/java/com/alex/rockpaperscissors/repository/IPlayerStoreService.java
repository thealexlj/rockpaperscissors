package com.alex.rockpaperscissors.repository;

import com.alex.rockpaperscissors.model.Player;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface to define storage of players
 */
public interface IPlayerStoreService {

  void addPlayer(Player player);

  Optional<Player> findById(UUID id);
  Optional<Player> findByName(String name);

  Collection<Player> findAll();
}
