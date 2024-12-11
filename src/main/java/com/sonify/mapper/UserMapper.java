package com.sonify.mapper;

import com.sonify.dto.UserDTO;
import com.sonify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    UserDTO toDto(User user);
    
    @Mapping(target = "authorities", ignore = true)
    User toEntity(UserDTO userDTO);
}
