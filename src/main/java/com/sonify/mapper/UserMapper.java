package com.sonify.mapper;

import com.sonify.dto.UserDTO;
import com.sonify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    UserDTO toDto(User user);
    
    @Mapping(target = "authorities", ignore = true)
    User toEntity(UserDTO userDTO);
}
