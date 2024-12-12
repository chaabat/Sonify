package com.sonify.mapper;

import com.sonify.dto.UserDTO;
import com.sonify.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    @Mapping(target = "roles", source = "roles")
    UserDTO toDto(User user);

    @Mapping(target = "roles", source = "roles")
    User toEntity(UserDTO userDTO);
}
