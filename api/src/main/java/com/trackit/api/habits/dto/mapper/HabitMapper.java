package com.trackit.api.habits.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.trackit.api.habits.dto.HabitDto;
import com.trackit.api.habits.model.Habits;
import com.trackit.api.user.model.Users;

@Mapper
public interface HabitMapper {
    public HabitMapper INSTANCE = Mappers.getMapper(HabitMapper.class);

    @Mapping(expression = "java(mapStringToInteger(habits.getDays()))", target = "days")
    HabitDto habitsToHabitDto(Habits habits);

    @Mapping(expression = "java(mapIntToString(habitDto.days()))", target = "days")
    @Mapping(source = "habitDto.id", target = "id")
    @Mapping(source = "habitDto.name", target = "name")
    @Mapping(target = "record_day", ignore = true)
    Habits habitDtoToHabits(HabitDto habitDto, Users user);

    default int[] mapStringToInteger(String days) {
        int[] result = new int[days.length()];
        for (int i = 0; i < days.length(); i++) {
            result[i] = Integer.parseInt(String.valueOf(days.charAt(i)));
        }
        return result;
    }

    default String mapIntToString(int[] days) {
        StringBuilder sb = new StringBuilder("");
        for (int day : days) {
            sb.append(day);
        }

        return sb.toString();
    }
}
