package com.trackit.api.progress.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.trackit.api.habits.repository.HabitsRepository;
import com.trackit.api.history.model.History;
import com.trackit.api.history.repository.HistoryRepository;
import com.trackit.api.progress.dto.MonthProgressDto;
import com.trackit.api.progress.dto.ProgressDto;
import com.trackit.api.progress.dto.mapper.MonthProgressMapper;
import com.trackit.api.progress.model.HabitsWithLastHistory;
import com.trackit.api.progress.model.MonthHabits;
import com.trackit.api.progress.repository.ProgressRepository;
import com.trackit.api.user.model.Users;

@Service
public class ProgressService {
    private final ProgressRepository repository;
    private final MonthProgressMapper mapper;
    private final HistoryRepository historyRepository;
    private final HabitsRepository habitsRepository;

    public ProgressService(ProgressRepository progressRepository, MonthProgressMapper monthProgressMapper,
            HistoryRepository historyRepository, HabitsRepository habitsRepository) {
        this.repository = progressRepository;
        this.mapper = monthProgressMapper;
        this.historyRepository = historyRepository;
        this.habitsRepository = habitsRepository;
    }

    public ProgressDto getProgress(Users user) {
        java.sql.Date date = new java.sql.Date(new Date().getTime());

        return new ProgressDto(repository.todayDoneHabits(user, date), repository.todayTotalHabits(user, date));
    }

    public List<MonthProgressDto> getMonthProgress(Users user, YearMonth yearMonth) {
        checkForUndoneHabits(user);
        List<MonthHabits> doneHabits = repository.monthDoneHabits(user, yearMonth);

        List<MonthHabits> totalHabits = repository.monthTotalHabits(user, yearMonth);

        return mapper.monthHabitsToProgressDto(doneHabits, totalHabits);
    }

    public void checkForUndoneHabits(Users user) {
        List<HabitsWithLastHistory> habits = repository.findHabitsWithLastHistory(user);
        Date today = new Date();
        for (HabitsWithLastHistory habit : habits) {
            if (habit.getLastHistory().equals(today))
                continue;
            long diff = today.getTime() - habit.getLastHistory().getTime();
            long daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            LocalDate startDate = LocalDate.parse(habit.getLastHistory().toString());

            for (int i = 1; i <= daysBetween; i++) {
                LocalDate day = startDate.plusDays(i);
                String dayOfWeek = Long.toString(day.getDayOfWeek().getValue());
                if (habit.getDays().contains(dayOfWeek)) {
                    History history = new History(habit.getId(), java.sql.Date.valueOf(day));
                    this.historyRepository.save(history);
                    this.habitsRepository.resetSequenceById(habit.getId());
                }
            }
        }
    }
}
