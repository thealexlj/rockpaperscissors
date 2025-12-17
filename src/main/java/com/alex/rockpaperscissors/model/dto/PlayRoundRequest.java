package com.alex.rockpaperscissors.model.dto;

import com.alex.rockpaperscissors.model.PlayType;

import java.util.UUID;

public record PlayRoundRequest(
        UUID player1Id,
        PlayType player1Play,
        UUID player2Id,
        PlayType player2Play
) {}

