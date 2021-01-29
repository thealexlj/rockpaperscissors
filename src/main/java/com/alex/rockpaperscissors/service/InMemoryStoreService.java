package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.model.Game;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InMemoryStoreService implements StoreService {
  private ConcurrentHashMap<String, Game> totalGames;

  @PostConstruct
  private void init(){
    totalGames = new ConcurrentHashMap<>();
  }

  @Override
  public void addGame(Game game) {
    totalGames.put(game.getId(), game);
  }

  @Override
  public Game[] getGames() {
    return totalGames.values().toArray(new Game[0]);
  }
}
