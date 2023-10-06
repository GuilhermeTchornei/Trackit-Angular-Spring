package com.trackit.api.progress.dto;

public class ProgressDto {
    Long doneHabits;
    Long totalHabits;

    public ProgressDto(Long doneHabits, Long totalHabits) {
        this.doneHabits = doneHabits;
        this.totalHabits = totalHabits;
    }

    public Long getDoneHabits() {
        return doneHabits;
    }

    public Long getTotalHabits() {
        return totalHabits;
    }
}
