package com.trackit.api.user.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.trackit.api.user.dto.UserDto;
import com.trackit.api.user.model.Users;

@Mapper
public interface UserMapper {
    public UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // @Mapping(source = "user_id", target = "id") if field name is different
    UserDto usersToUserDto(Users users);

    Users userDtoToUsers(UserDto userDto);
}
