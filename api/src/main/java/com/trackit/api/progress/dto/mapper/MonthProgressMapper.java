package com.trackit.api.progress.dto.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.trackit.api.progress.dto.MonthProgressDto;
import com.trackit.api.progress.model.MonthHabits;

@Component
public class MonthProgressMapper {
    public List<MonthProgressDto> monthHabitsToProgressDto(List<MonthHabits> doneHabits,
            List<MonthHabits> totalHabits) {
        List<MonthProgressDto> monthProgress = new ArrayList<MonthProgressDto>();

        int j = 0;
        for (int i = 0; i < totalHabits.size(); i++) {
            Date date = totalHabits.get(i).getDate();
            Long total = totalHabits.get(i).getNumHabits();
            Long done = (long) 0;

            if (doneHabits.get(j).getDate().equals(date)) {
                done = doneHabits.get(i).getNumHabits();
                j += 1;
            }

            monthProgress.add(new MonthProgressDto(date, done, total));
        }

        return monthProgress;
    }
}
