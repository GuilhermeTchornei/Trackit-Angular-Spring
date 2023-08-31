package com.trackit.api.auth.jwt;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.trackit.api.auth.dto.AuthDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private static final long EXPIRATION_TIME = 604800000; // 1 week

    @Override
    public String extractData(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    @Override
    public String generateToken(AuthDto authDto) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.builder().setSubject(authDto.email()).setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secretToBytes())
                .compact();
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            return getClaimsFromToken(token).getExpiration().after(new java.util.Date()) &&
                    userDetails.getUsername().equals(extractData(token));
        } catch (Exception ex) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    private byte[] secretToBytes() {
        return Base64.getEncoder().encode(jwtSecret.getBytes());
    }

    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretToBytes()).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

}
