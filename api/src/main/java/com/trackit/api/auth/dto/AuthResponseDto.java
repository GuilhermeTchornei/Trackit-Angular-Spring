package com.trackit.api.auth.dto;

public record AuthResponseDto(
        String token,
        String photo) {
}
