package com.trackit.api.history.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public record HistoryDto(
        @JsonProperty("id") Long id,
        @NotNull Boolean done) {
}
