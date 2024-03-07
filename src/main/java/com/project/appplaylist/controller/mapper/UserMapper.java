package com.project.appplaylist.controller.mapper;

import com.project.appplaylist.controller.dto.SignUpDto;
import com.project.appplaylist.controller.dto.UserDto;
import com.project.appplaylist.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    @InheritInverseConfiguration
    User toEntity(UserDto userDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", ignore = true)
    User toSignUp(SignUpDto signUpDto);
}