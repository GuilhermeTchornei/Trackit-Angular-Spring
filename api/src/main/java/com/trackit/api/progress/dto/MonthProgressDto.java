package com.trackit.api.progress.dto;

import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MonthProgressDto {
        Date date;
        Long doneHabits;
        Long totalHabits;

        public MonthProgressDto(Date date, Long doneHabits, Long totalHabits) {
                this.date = date;
                this.doneHabits = doneHabits;
                this.totalHabits = totalHabits;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public Long getDoneHabits() {
                return doneHabits;
        }

        public void setDoneHabits(Long doneHabits) {
                this.doneHabits = doneHabits;
        }

        public Long getTotalHabits() {
                return totalHabits;
        }

        public void setTotalHabits(Long totalHabits) {
                this.totalHabits = totalHabits;
        }
}
