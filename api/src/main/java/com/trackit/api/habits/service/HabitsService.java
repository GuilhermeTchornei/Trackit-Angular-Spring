package com.trackit.api.habits.service;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trackit.api.habits.dto.HabitDto;
import com.trackit.api.habits.dto.mapper.HabitMapper;
import com.trackit.api.habits.model.Habits;
import com.trackit.api.habits.repository.HabitsRepository;
import com.trackit.api.history.model.History;
import com.trackit.api.history.repository.HistoryRepository;
import com.trackit.api.progress.service.ProgressService;
import com.trackit.api.user.model.Users;

@Service
public class HabitsService {
    private final HabitsRepository repository;
    private final ProgressService progressService;
    private final HistoryRepository historyRepository;

    public HabitsService(HabitsRepository habitsRepository, ProgressService progressService,
            HistoryRepository historyRepository) {
        this.repository = habitsRepository;
        this.progressService = progressService;
        this.historyRepository = historyRepository;
    }

    public void create(HabitDto habitDto, Users user) {
        Habits habit = HabitMapper.INSTANCE.habitDtoToHabits(habitDto, user);
        Habits newHabit = this.repository.save(habit);

        Date today = new Date();
        History history = new History(newHabit.getId(), new java.sql.Date(today.getTime()));
        historyRepository.save(history);
    }

    public HabitDto updateSequenceAndRecord(Habits habit) {
        if (habit.getSequence() > habit.getRecord())
            habit.setRecord(habit.getSequence());

        repository.save(habit);
        return HabitMapper.INSTANCE.habitsToHabitDto(habit);
    }

    public Optional<Habits> findOne(Long habitId) {
        return this.repository.findById(habitId);
    }

    public List<HabitDto> find(Users user) {
        progressService.checkForUndoneHabits(user);

        List<HabitDto> habitsDto = new ArrayList<>();
        List<Habits> habits = repository.findByUser(user);
        habits.forEach(habit -> habitsDto.add(HabitMapper.INSTANCE.habitsToHabitDto(habit)));
        return habitsDto;
    }

    public List<HabitDto> findToday(Users user) {
        progressService.checkForUndoneHabits(user);

        int today = LocalDate.now().getDayOfWeek().getValue();

        List<HabitDto> habitsDto = new ArrayList<>();
        List<Habits> habits = repository.findTodayByUser(user.getId(), String.valueOf(today));
        habits.forEach(habit -> habitsDto.add(HabitMapper.INSTANCE.habitsToHabitDto(habit)));
        return habitsDto;
    }
}
