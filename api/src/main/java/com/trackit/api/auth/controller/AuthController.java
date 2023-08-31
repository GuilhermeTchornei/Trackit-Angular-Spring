package com.trackit.api.auth.controller;

import org.springframework.http.HttpStatus;
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
    public String signin(@RequestBody AuthDto auth) {
        try {
            return service.signin(auth);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

}
