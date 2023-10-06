package com.trackit.api.progress.model;

import java.util.Date;

public class MonthHabits {
    Date date;
    Long numHabits;

    public MonthHabits(Date date, Long numHabits) {
        this.date = date;
        this.numHabits = numHabits;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getNumHabits() {
        return numHabits;
    }

    public void setNumHabits(Long numHabits) {
        this.numHabits = numHabits;
    }
}
