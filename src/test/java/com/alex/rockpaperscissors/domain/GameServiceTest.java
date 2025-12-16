package com.alex.rockpaperscissors.domain;

import com.alex.rockpaperscissors.model.*;
import com.alex.rockpaperscissors.service.GameService;
import com.alex.rockpaperscissors.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        when(storeService.findById(game.getId()))
                .thenReturn(Optional.of(game));

        when(gameRules.resolve(any(), any()))
                .thenReturn(RoundResult.PLAYER1WINS);

        Game result = gameService.playRound(game.getId(), PlayType.ROCK);

        assertNotNull(result);
        assertEquals(1, result.getRounds().size());

        verify(storeService).addGame(game);
        verify(gameRules).resolve(any(), any());
    }

    @Test
    void shouldHandleConcurrentRoundsForSameGame() throws Exception {
        int threads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        Game game = new Game(new Player(), new Player());

        when(storeService.findById(game.getId()))
                .thenReturn(Optional.of(game));

        when(gameRules.resolve(any(), any()))
                .thenReturn(RoundResult.PLAYER1WINS);

        List<Callable<Void>> tasks = IntStream.range(0, threads)
                .mapToObj(i -> (Callable<Void>) () -> {
                    gameService.playRound(game.getId(), PlayType.ROCK);
                    return null;
                })
                .toList();

        executor.invokeAll(tasks);
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(threads, game.getRounds().size());

        verify(storeService, times(threads)).addGame(game);
    }
}

