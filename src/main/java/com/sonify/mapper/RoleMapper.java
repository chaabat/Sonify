package com.sonify.mapper;

import com.sonify.dto.RoleDTO;
import com.sonify.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDto(Role role);
    Role toEntity(RoleDTO roleDTO);
} 