package com.trackit.api.habits.model;

import org.hibernate.validator.constraints.Length;

import com.trackit.api.user.model.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
public class Habits {
    public Habits(Integer[] days) {
        this.days = days.toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column()
    @Length(min = 3, max = 50)
    @NotBlank
    @NotNull
    String name;

    @Column()
    @Length(min = 1, max = 10)
    @NotBlank
    @NotNull
    String days;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users user;
}
