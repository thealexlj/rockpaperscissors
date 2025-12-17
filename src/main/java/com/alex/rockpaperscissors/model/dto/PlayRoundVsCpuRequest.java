package com.alex.rockpaperscissors.model.dto;

import com.alex.rockpaperscissors.model.PlayType;

import java.util.UUID;

public record PlayRoundVsCpuRequest(
        UUID playerId,
        PlayType playType
) {}