package com.trackit.api.auth.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import com.trackit.api.auth.dto.AuthDto;

public interface JwtService {
    String extractData(String token);

    String generateToken(AuthDto authDto);

    Boolean isTokenValid(String token, UserDetails userDetails);
}
