package com.trackit.api.history.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.trackit.api.habits.dto.HabitDto;
import com.trackit.api.habits.model.Habits;
import com.trackit.api.habits.service.HabitsService;
import com.trackit.api.history.dto.HistoryDto;
import com.trackit.api.history.dto.mapper.HistoryMapper;
import com.trackit.api.history.model.History;
import com.trackit.api.history.repository.HistoryRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Service
public class HistoryService {
    private final HistoryRepository repository;
    private final HabitsService service;

    public HistoryService(HistoryRepository historyRepository, HabitsService habitsService) {
        this.repository = historyRepository;
        this.service = habitsService;
    }

    public HabitDto create(HistoryDto historyDto, Long habitId) {
        Habits habit = service.findOne(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit doesn't exist."));
        History history = HistoryMapper.INSTANCE.historyDtoToHistory(historyDto, habit);
        Optional<History> historyExists = this.repository.findByHabitIdAndDate(habit.getId(), history.getDay());

        if (historyExists.isPresent()) {
            if (history.getDone() && !historyExists.get().getDone()) {
                habit.setSequence(habit.getSequence() + 1);
            } else if (!history.getDone() && historyExists.get().getDone()) {
                habit.setSequence(habit.getSequence() - 1);
                habit.setRecord(habit.getSequence());
            }

            historyExists.get().setDone(history.getDone());
            this.repository.save(historyExists.get());
        } else {
            this.repository.save(history);
            habit.setSequence(habit.getSequence() + 1);
        }

        return service.updateSequenceAndRecord(habit);
    }

    public boolean isDoneToday(@Valid @Positive Long id) {
        java.sql.Date date = new java.sql.Date(new Date().getTime());
        Optional<History> history = repository.findByHabitIdAndDate(id, date);
        return history.isPresent() ? history.get().getDone() : false;
    }
}
