package com.sonify.mapper;

import com.sonify.dto.ChansonDTO;
import com.sonify.model.Chanson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChansonMapper {
    ChansonMapper INSTANCE = Mappers.getMapper(ChansonMapper.class);

    @Mapping(target = "albumId", source = "album.id")
    ChansonDTO toDto(Chanson chanson);

    @Mapping(target = "album", ignore = true)
    Chanson toEntity(ChansonDTO chansonDTO);
}
