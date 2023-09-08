package com.trackit.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(
                @JsonProperty("id") Long id,
                @NotBlank @NotNull String name,
                @NotBlank @NotNull String password,
                @NotBlank @NotNull String email,
                @NotBlank @NotNull String photo) {
}