package com.trackit.api.habits.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.trackit.api.habits.dto.HabitDto;
import com.trackit.api.habits.dto.mapper.HabitMapper;
import com.trackit.api.habits.model.Habits;
import com.trackit.api.habits.repository.HabitsRepository;
import com.trackit.api.user.model.Users;

@Validated
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

    public List<HabitDto> find(Users user) {
        List<HabitDto> habitsDto = new ArrayList<>();
        List<Habits> habits = repository.findByUser(user);
        habits.forEach(habit -> habitsDto.add(HabitMapper.INSTANCE.habitsToHabitDto(habit)));
        return habitsDto;
    }
}
