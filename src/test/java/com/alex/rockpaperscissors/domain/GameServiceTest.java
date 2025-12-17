package com.alex.rockpaperscissors.domain;

import com.alex.rockpaperscissors.exception.GameNotFoundException;
import com.alex.rockpaperscissors.model.*;
import com.alex.rockpaperscissors.repository.IGameStoreService;
import com.alex.rockpaperscissors.service.GameService;
import com.alex.rockpaperscissors.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    IGameStoreService gameStoreService;

    @Mock
    PlayerService playerService;

    @Mock
    GameRules gameRules;

    @InjectMocks
    GameService gameService;

    @Test
    void shouldCreateGame() {
        Player p1 = new Player("player1");
        Player p2 = new Player("player2");

        when(playerService.getPlayer(p1.getId())).thenReturn(p1);
        when(playerService.getPlayer(p2.getId())).thenReturn(p2);

        Game game = gameService.createGame(p1.getId(), p2.getId());

        assertNotNull(game);
        assertEquals(p1, game.getPlayer1());
        assertEquals(p2, game.getPlayer2());

        verify(gameStoreService).addGame(game);
    }

    @Test
    void shouldPlayRoundAndStoreGame() {
        Player p1 = new Player("player1");
        Player p2 = new Player("player2");
        Game game = new Game(p1, p2);

        // Mocks para getGame y playerService
        when(gameStoreService.findById(game.getId())).thenReturn(Optional.of(game));
        when(playerService.getPlayer(p1.getId())).thenReturn(p1);
        when(playerService.getPlayer(p2.getId())).thenReturn(p2);

        // Mock de reglas
        when(gameRules.resolve(any(), any())).thenReturn(RoundResult.PLAYER1WINS);

        Game result = gameService.playRound(
                game.getId(),
                p1.getId(),
                PlayType.ROCK,
                p2.getId(),
                PlayType.SCISSORS
        );

        assertNotNull(result);
        assertEquals(1, result.getRounds().size());
        assertEquals(RoundResult.PLAYER1WINS, result.getRounds().get(0).result());

        verify(gameRules).resolve(any(), any());
        verify(gameStoreService).addGame(game);
    }

    @Test
    void shouldThrowGameNotFound() {
        UUID randomId = UUID.randomUUID();
        when(gameStoreService.findById(randomId)).thenReturn(Optional.empty());

        assertThrows(GameNotFoundException.class, () ->
                gameService.playRound(randomId, UUID.randomUUID(), PlayType.ROCK, UUID.randomUUID(), PlayType.SCISSORS)
        );
    }

    @Test
    void shouldCreateGameVsCpu() {
        Player human = new Player("player1");
        Player cpu = new Player("cpu");

        when(playerService.getPlayer(human.getId())).thenReturn(human);
        when(playerService.getCpuPlayer()).thenReturn(cpu);
        when(playerService.getPlayer(cpu.getId())).thenReturn(cpu);

        Game game = gameService.createGameVsCpu(human.getId());

        assertNotNull(game);
        assertEquals(human, game.getPlayer1());
        assertEquals(cpu, game.getPlayer2());

        verify(gameStoreService).addGame(game);
    }
}
