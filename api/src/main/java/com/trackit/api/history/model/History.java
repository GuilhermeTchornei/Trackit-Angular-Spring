package com.trackit.api.history.model;

import java.sql.Date;

import com.trackit.api.habits.model.Habits;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column()
    @NotNull
    Date day = new Date(new java.util.Date().getTime());

    @Column()
    @NotNull
    Boolean done;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    Habits habit;
}
