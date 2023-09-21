package com.trackit.api.habits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackit.api.habits.model.Habits;
import com.trackit.api.user.model.Users;
import java.util.List;

public interface HabitsRepository extends JpaRepository<Habits, Long> {
    List<Habits> findByUser(Users user);
}
