package com.sonify.mapper;

import com.sonify.dto.RoleDTO;
import com.sonify.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    
    RoleDTO toDto(Role role);
    Role toEntity(RoleDTO roleDTO);
} 