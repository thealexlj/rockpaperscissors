package com.alex.rockpaperscissors.rest;

import com.alex.rockpaperscissors.controller.RockPaperScissorsRestController;
import com.alex.rockpaperscissors.exception.GameNotFoundException;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.PlayType;
import com.alex.rockpaperscissors.model.Player;
import com.alex.rockpaperscissors.service.GameService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RockPaperScissorsRestController.class)
class RockPaperScissorsRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private GameService gameService;

  @Test
  void shouldCreateGame() throws Exception {
    Game game = new Game(new Player(), new Player());

    when(gameService.createGame(any(), any()))
            .thenReturn(game);

    mockMvc.perform(post("/api/games"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.player1").exists())
            .andExpect(jsonPath("$.player2").exists());
  }

  @Test
  void shouldPlayRound() throws Exception {
    Player player1 = new Player();
    Player player2 = new Player();
    Game game = new Game(player1, player2);

    UUID gameId = game.getId();

    when(gameService.getGame(gameId)).thenReturn(game);
    when(gameService.playRound(gameId, PlayType.ROCK)).thenReturn(game);

    mockMvc.perform(post("/api/games/{id}/rounds", gameId)
                    .param("playType", "ROCK"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(gameId.toString()))
            .andExpect(jsonPath("$.player1.id").value(player1.getId().toString()))
            .andExpect(jsonPath("$.player2.id").value(player2.getId().toString()))
            .andExpect(jsonPath("$.rounds").isArray());
  }

  @Test
  void shouldReturn404WhenGameNotFound() throws Exception {
    UUID gameId = UUID.randomUUID();

    when(gameService.getGame(gameId))
            .thenThrow(new GameNotFoundException("Game not found"));

    mockMvc.perform(post("/api/games/{id}/rounds", gameId)
                    .param("playType", "ROCK"))
            .andExpect(status().isNotFound());
  }
}
