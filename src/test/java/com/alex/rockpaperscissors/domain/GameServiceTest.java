package com.alex.rockpaperscissors.domain;

import com.alex.rockpaperscissors.model.*;
import com.alex.rockpaperscissors.service.GameService;
import com.alex.rockpaperscissors.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    StoreService storeService;

    @Mock
    GameRules gameRules;

    @InjectMocks
    GameService gameService;

    @Test
    void shouldCreateGame() {
        Player p1 = new Player();
        Player p2 = new Player();

        Game game = gameService.createGame(p1, p2);

        assertNotNull(game);
        assertEquals(p1, game.getPlayer1());
        assertEquals(p2, game.getPlayer2());
        verify(storeService).addGame(game);
    }

    @Test
    void shouldPlayRoundAndStoreGame() {
        Game game = new Game(new Player(), new Player());

        when(gameRules.resolve(any(), any()))
                .thenReturn(RoundResult.PLAYER1WINS);

        Game result = gameService.playRound(game, PlayType.ROCK);

        assertEquals(1, result.getRounds().size());
        verify(storeService).addGame(game);
        verify(gameRules).resolve(any(), any());
    }
}

