package com.alex.rockpaperscissors.service;

import com.alex.rockpaperscissors.exception.PlayerNotFoundException;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.repository.IPlayerStoreService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class PlayerService {

    private final IPlayerStoreService playerStoreService;

    public PlayerService(IPlayerStoreService playerStoreService) {
        this.playerStoreService = playerStoreService;
    }

    public Player createPlayer( String name ){
        Player player = new Player( name );
        playerStoreService.addPlayer( player );
        return player;
    }

    /**
     * Retrieves a player given a playerId
     * @param playerId
     * @return Player The player with all the info
     * @throws PlayerNotFoundException
     */
    public Player getPlayer(UUID playerId) throws PlayerNotFoundException {
        return playerStoreService.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + " not found"));
    }

    /**
     * Retriaves the Cpu Player
     * @return Player Cpu player with all info
     */
    public Player getCpuPlayer() {
        return playerStoreService.findByName("CPU")
                .orElseGet(() -> {
                    Player player = new Player("CPU");
                    playerStoreService.addPlayer(player);
                    return player;
                });
    }

    /**
     * Gets all the players
     * @return Collection<Player> A collection with all the players
     */
    public Collection<Player> getPlayers() {
        return playerStoreService.findAll();
    }
}
