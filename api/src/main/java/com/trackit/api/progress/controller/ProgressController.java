package com.trackit.api.progress.controller;

import java.time.YearMonth;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackit.api.progress.dto.MonthProgressDto;
import com.trackit.api.progress.dto.ProgressDto;
import com.trackit.api.progress.service.ProgressService;
import com.trackit.api.user.model.Users;

import jakarta.validation.Valid;

@RestController
@RequestMapping("progress")
@Validated
public class ProgressController {
    private final ProgressService service;

    public ProgressController(ProgressService progressService) {
        this.service = progressService;
    }

    @GetMapping()
    ProgressDto getProgress(Authentication authentication) {
        return service.getProgress((Users) authentication.getPrincipal());
    }

    @GetMapping("{yearMonth}")
    List<MonthProgressDto> getMonthProgress(@PathVariable @Valid() YearMonth yearMonth,
            Authentication authentication) {
        return service.getMonthProgress((Users) authentication.getPrincipal(), yearMonth);
    }
}
