package com.trackit.api.progress.model;

import java.util.Date;

public class HabitsWithLastHistory {
    Long id;
    String days;
    Date lastHistory;

    public HabitsWithLastHistory(Long id, String days, Date lastHistory) {
        this.id = id;
        this.days = days;
        this.lastHistory = lastHistory;
    }

    public Long getId() {
        return id;
    }

    public String getDays() {
        return days;
    }

    public Date getLastHistory() {
        return lastHistory;
    }
}
