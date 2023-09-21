package com.trackit.api.habits.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackit.api.habits.dto.HabitDto;
import com.trackit.api.habits.service.HabitsService;
import com.trackit.api.user.model.Users;

@RestController
@RequestMapping("/habits")
public class HabitsController {
    private HabitsService service;

    public HabitsController(HabitsService habitsService) {
        this.service = habitsService;
    }

    @PostMapping()
    void create(@RequestBody HabitDto habitDto, Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();
        this.service.create(habitDto, user);
    }

    @GetMapping()
    List<HabitDto> find(Authentication authentication) {
        return service.find((Users) authentication.getPrincipal());
    }
}
