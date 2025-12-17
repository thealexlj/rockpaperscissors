package com.alex.rockpaperscissors.rest;

import com.alex.rockpaperscissors.controller.RockPaperScissorsRestController;
import com.alex.rockpaperscissors.exception.GameNotFoundException;
import com.alex.rockpaperscissors.exception.GlobalExceptionHandler;
import com.alex.rockpaperscissors.model.*;
import com.alex.rockpaperscissors.model.dto.CreateGameRequest;
import com.alex.rockpaperscissors.model.dto.PlayRoundRequest;
import com.alex.rockpaperscissors.model.dto.PlayRoundVsCpuRequest;
import com.alex.rockpaperscissors.service.GameService;
import com.alex.rockpaperscissors.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RockPaperScissorsRestController.class)
@Import(GlobalExceptionHandler.class)
class RockPaperScissorsRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private GameService gameService;

  @MockBean
  private PlayerService playerService;

  @Test
  void shouldCreatePlayer() throws Exception {
    Player player = new Player("Alice");

    when(playerService.createPlayer("Alice")).thenReturn(player);

    mockMvc.perform(post("/api/games/players")
                    .param("name", "Alice"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(player.getId().toString()))
            .andExpect(jsonPath("$.name").value("Alice"));
  }

  @Test
  void shouldCreateGame() throws Exception {
    Player p1 = new Player("player1");
    Player p2 = new Player("player2");
    Game game = new Game(p1, p2);

    when(gameService.createGame(p1.getId(), p2.getId())).thenReturn(game);

    CreateGameRequest request = new CreateGameRequest(p1.getId(), p2.getId());

    mockMvc.perform(post("/api/games/games")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(game.getId().toString()))
            .andExpect(jsonPath("$.player1.id").value(p1.getId().toString()))
            .andExpect(jsonPath("$.player2.id").value(p2.getId().toString()));
  }

  @Test
  void shouldCreateGameVsCpu() throws Exception {
    Player p1 = new Player("player1");
    Player cpu = new Player("CPU");
    Game game = new Game(p1, cpu);

    when(gameService.createGameVsCpu(p1.getId())).thenReturn(game);

    mockMvc.perform(post("/api/games/games/cpu")
                    .param("playerId", p1.getId().toString()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(game.getId().toString()))
            .andExpect(jsonPath("$.player1.id").value(p1.getId().toString()))
            .andExpect(jsonPath("$.player2.id").value(cpu.getId().toString()));
  }

  @Test
  void shouldPlayRoundPvp() throws Exception {
    Player p1 = new Player("player1");
    Player p2 = new Player("player2");
    Game game = new Game(p1, p2);

    PlayRoundRequest request = new PlayRoundRequest(
            p1.getId(), PlayType.ROCK,
            p2.getId(), PlayType.PAPER
    );

    when(gameService.playRound(
            game.getId(),
            request.player1Id(),
            request.player1Play(),
            request.player2Id(),
            request.player2Play()
    )).thenReturn(game);

    mockMvc.perform(post("/api/games/games/{gameId}/rounds", game.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(game.getId().toString()))
            .andExpect(jsonPath("$.player1.id").value(p1.getId().toString()))
            .andExpect(jsonPath("$.player2.id").value(p2.getId().toString()))
            .andExpect(jsonPath("$.rounds").isArray());
  }

  @Test
  void shouldPlayRoundVsCpu() throws Exception {
    Player p1 = new Player("player1");
    Player cpu = new Player("CPU");
    Game game = new Game(p1, cpu);

    PlayRoundVsCpuRequest request = new PlayRoundVsCpuRequest(p1.getId(), PlayType.ROCK);

    when(gameService.playRoundVsCpu(game.getId(), p1.getId(), PlayType.ROCK))
            .thenReturn(game);

    mockMvc.perform(post("/api/games/games/{gameId}/rounds/cpu", game.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(game.getId().toString()))
            .andExpect(jsonPath("$.player1.id").value(p1.getId().toString()))
            .andExpect(jsonPath("$.player2.id").value(cpu.getId().toString()))
            .andExpect(jsonPath("$.rounds").isArray());
  }

  @Test
  void shouldReturn404WhenGameNotFound() throws Exception {
    UUID gameId = UUID.randomUUID();

    PlayRoundRequest request = new PlayRoundRequest(
            UUID.randomUUID(), PlayType.ROCK,
            UUID.randomUUID(), PlayType.PAPER
    );

    // Aquí simulamos que el gameService lance la excepción
    when(gameService.playRound(
            any(UUID.class),
            any(UUID.class),
            any(PlayType.class),
            any(UUID.class),
            any(PlayType.class)
    )).thenThrow(new GameNotFoundException("Game not found"));

    mockMvc.perform(post("/api/games/games/{gameId}/rounds", gameId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isNotFound());
  }

  @Test
  void shouldReturnAllPlayers() throws Exception {
    Player player1 = new Player("Alice");
    Player player2 = new Player("Bob");

    // Mockeamos el servicio
    when(playerService.getPlayers()).thenReturn(List.of(player1, player2));

    mockMvc.perform(get("/api/games/players")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].id").value(player1.getId().toString()))
            .andExpect(jsonPath("$[0].name").value(player1.getName()))
            .andExpect(jsonPath("$[1].id").value(player2.getId().toString()))
            .andExpect(jsonPath("$[1].name").value(player2.getName()));
  }
}
