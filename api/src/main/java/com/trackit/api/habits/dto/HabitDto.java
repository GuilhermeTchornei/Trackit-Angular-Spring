package com.trackit.api.habits.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HabitDto(
        @JsonProperty("id") Long id,
        @NotBlank @NotNull String name,
        @NotEmpty @NotNull int[] days,
        @NotNull @PositiveOrZero int sequence,
        @NotNull @PositiveOrZero int record) {

    @Override
    public String toString() {
        StringBuilder daysStringBuilder = new StringBuilder();
        daysStringBuilder.append("[");
        for (int i = 0; i < days.length; i++) {
            daysStringBuilder.append(days[i]);
            if (i < days.length - 1) {
                daysStringBuilder.append(", ");
            }
        }
        daysStringBuilder.append("]");

        return "HabitDto[id=" + id + ", name=" + name + ", days=" + daysStringBuilder.toString() + "]";
    }
}
