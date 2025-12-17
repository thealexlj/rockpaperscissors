package com.alex.rockpaperscissors.repository;

import com.alex.rockpaperscissors.model.Player;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of PlayerStoreService in local memory
 */
@Repository
public class InMemoryPlayerStoreService implements IPlayerStoreService {

  private final ConcurrentHashMap<UUID, Player> players = new ConcurrentHashMap<>();

  @Override
  public void addPlayer(Player player) {
    players.put(player.getId(), player);
  }

  @Override
  public Optional<Player> findById(UUID id) {
    return Optional.ofNullable(players.get(id));
  }

  @Override
  public Optional<Player> findByName(String name) {
    return players.values().stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst();
  }

  @Override
  public Collection<Player> findAll() {
    return players.values();
  }
}