package com.trackit.api.history.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackit.api.habits.dto.HabitDto;
import com.trackit.api.history.dto.HistoryDto;
import com.trackit.api.history.service.HistoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/history")
@Validated
public class HistoryController {
    private final HistoryService service;

    HistoryController(HistoryService historyService) {
        this.service = historyService;
    }

    @GetMapping("{habitId}")
    boolean isDoneToday(@PathVariable @Valid @Positive Long habitId) {
        return this.service.isDoneToday(habitId);
    }

    @PostMapping("{habitId}")
    HabitDto create(@PathVariable @Valid @Positive Long habitId, @RequestBody @Valid HistoryDto historyDto) {
        return service.create(historyDto, habitId);
    }
}
