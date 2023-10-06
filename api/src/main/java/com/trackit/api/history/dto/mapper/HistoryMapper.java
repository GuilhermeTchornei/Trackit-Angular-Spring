package com.trackit.api.history.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.trackit.api.habits.model.Habits;
import com.trackit.api.history.dto.HistoryDto;
import com.trackit.api.history.model.History;

@Mapper()
public interface HistoryMapper {
    public HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);

    @Mapping(target = "day", ignore = true)
    @Mapping(source = "historyDto.id", target = "id")
    History historyDtoToHistory(HistoryDto historyDto, Habits habit);
}
