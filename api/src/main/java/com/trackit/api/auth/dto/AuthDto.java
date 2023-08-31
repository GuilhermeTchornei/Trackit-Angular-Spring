package com.trackit.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthDto(
                @NotBlank @NotNull String email,
                @NotBlank @NotNull String password) {
}
