package com.alex.rockpaperscissors.domain;

import com.alex.rockpaperscissors.exception.PlayerNotFoundException;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.repository.IPlayerStoreService;
import com.alex.rockpaperscissors.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    IPlayerStoreService playerStoreService;

    @InjectMocks
    PlayerService playerService;

    Player existingPlayer;

    @BeforeEach
    void setup() {
        existingPlayer = new Player("Alice");
    }

    @Test
    void shouldCreatePlayer() {
        Player player = playerService.createPlayer("Bob");

        assertNotNull(player.getId());
        assertEquals("Bob", player.getName());
        verify(playerStoreService).addPlayer(player);
    }

    @Test
    void shouldReturnPlayerById() {
        UUID playerId = existingPlayer.getId();
        when(playerStoreService.findById(playerId)).thenReturn(Optional.of(existingPlayer));

        Player result = playerService.getPlayer(playerId);

        assertEquals(existingPlayer, result);
    }

    @Test
    void shouldThrowWhenPlayerNotFound() {
        UUID unknownId = UUID.randomUUID();
        when(playerStoreService.findById(unknownId)).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerService.getPlayer(unknownId));
    }

    @Test
    void shouldReturnExistingCpuPlayer() {
        Player cpu = new Player("CPU");
        when(playerStoreService.findByName("CPU")).thenReturn(Optional.of(cpu));

        Player result = playerService.getCpuPlayer();

        assertEquals(cpu, result);
        verify(playerStoreService, never()).addPlayer(any());
    }

    @Test
    void shouldCreateCpuPlayerIfNotExists() {
        when(playerStoreService.findByName("CPU")).thenReturn(Optional.empty());

        Player result = playerService.getCpuPlayer();

        assertNotNull(result.getId());
        assertEquals("CPU", result.getName());
        verify(playerStoreService).addPlayer(result);
    }

    @Test
    void shouldReturnAllPlayers() {
        when(playerStoreService.findAll()).thenReturn(List.of(existingPlayer));

        Collection<Player> players = playerService.getPlayers();

        assertEquals(1, players.size());
        assertTrue(players.contains(existingPlayer));
    }
}
