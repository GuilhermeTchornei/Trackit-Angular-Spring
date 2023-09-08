package com.trackit.api.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.trackit.api.auth.dto.AuthDto;
import com.trackit.api.auth.service.AuthService;

@RequestMapping("/signin")
@RestController
public class AuthController {
    private final AuthService service;

    AuthController(AuthService authService) {
        this.service = authService;
    }

    @PostMapping()
    public ResponseEntity<Object> signin(@RequestBody AuthDto auth) {
        try {
            Map<String, String> token = new HashMap<>();
            token.put("token", this.service.signin(auth));
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

}
