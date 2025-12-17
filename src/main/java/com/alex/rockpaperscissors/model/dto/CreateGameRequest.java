package com.alex.rockpaperscissors.model.dto;

import java.util.UUID;

public record CreateGameRequest(
        UUID player1Id,
        UUID player2Id
) {}
