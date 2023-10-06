package com.trackit.api.history.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackit.api.history.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h WHERE h.day=:date AND h.habit.id=:habitId")
    Optional<History> findByHabitIdAndDate(Long habitId, Date date);
}
