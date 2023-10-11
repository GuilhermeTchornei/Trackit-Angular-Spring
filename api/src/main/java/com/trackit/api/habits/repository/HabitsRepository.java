package com.trackit.api.habits.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trackit.api.habits.model.Habits;
import com.trackit.api.user.model.Users;
import java.util.List;

public interface HabitsRepository extends JpaRepository<Habits, Long> {
    List<Habits> findByUser(Users user);

    @Query("SELECT h FROM Habits h WHERE h.user.id = :userId AND h.days LIKE %:today%")
    List<Habits> findTodayByUser(@Param("userId") Long userId, @Param("today") String today);

    @Query("UPDATE Habits h SET h.sequence = 0 WHERE h.id = :habitId")
    void resetSequenceById(@Param("habitId") long habitId);
}
