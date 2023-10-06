package com.trackit.api.progress.service;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.trackit.api.progress.dto.MonthProgressDto;
import com.trackit.api.progress.dto.ProgressDto;
import com.trackit.api.progress.dto.mapper.MonthProgressMapper;
import com.trackit.api.progress.model.MonthHabits;
import com.trackit.api.progress.repository.ProgressRepository;
import com.trackit.api.user.model.Users;

@Service
public class ProgressService {
    private final ProgressRepository repository;
    private final MonthProgressMapper mapper;

    public ProgressService(ProgressRepository progressRepository, MonthProgressMapper monthProgressMapper) {
        this.repository = progressRepository;
        this.mapper = monthProgressMapper;
    }

    public ProgressDto getProgress(Users user) {
        java.sql.Date date = new java.sql.Date(new Date().getTime());

        return new ProgressDto(repository.todayDoneHabits(user, date), repository.todayTotalHabits(user, date));
    }

    public List<MonthProgressDto> getMonthProgress(Users user, YearMonth yearMonth) {
        List<MonthHabits> doneHabits = repository.monthDoneHabits(user, yearMonth);

        List<MonthHabits> totalHabits = repository.monthTotalHabits(user, yearMonth);

        return mapper.monthHabitsToProgressDto(doneHabits, totalHabits);
    }
}
