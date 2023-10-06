package com.trackit.api.habits.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trackit.api.habits.dto.HabitDto;
import com.trackit.api.habits.dto.mapper.HabitMapper;
import com.trackit.api.habits.model.Habits;
import com.trackit.api.habits.repository.HabitsRepository;
import com.trackit.api.user.model.Users;

@Service
public class HabitsService {
    private final HabitsRepository repository;

    public HabitsService(HabitsRepository habitsRepository) {
        this.repository = habitsRepository;
    }

    public void create(HabitDto habitDto, Users user) {
        Habits habit = HabitMapper.INSTANCE.habitDtoToHabits(habitDto, user);
        System.out.println(habit);
        this.repository.save(habit);
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
        List<HabitDto> habitsDto = new ArrayList<>();
        List<Habits> habits = repository.findByUser(user);
        habits.forEach(habit -> habitsDto.add(HabitMapper.INSTANCE.habitsToHabitDto(habit)));
        return habitsDto;
    }

    public List<HabitDto> findToday(Users user) {
        int today = LocalDate.now().getDayOfWeek().getValue();

        List<HabitDto> habitsDto = new ArrayList<>();
        List<Habits> habits = repository.findTodayByUser(user.getId(), String.valueOf(today));
        habits.forEach(habit -> habitsDto.add(HabitMapper.INSTANCE.habitsToHabitDto(habit)));
        return habitsDto;
    }
}
