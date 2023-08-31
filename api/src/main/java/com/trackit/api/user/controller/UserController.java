package com.trackit.api.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackit.api.user.dto.UserDto;
import com.trackit.api.user.service.UserService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping("/{id}")
    UserDto getUser(@PathVariable @NotNull @Positive Long id) {
        return service.getUser(id);
    }

    @PostMapping
    void create(@RequestBody UserDto user) {
        service.create(user);
    }
}