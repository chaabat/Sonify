package com.sonify.mapper;

import com.sonify.dto.ChansonDTO;
import com.sonify.model.Chanson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChansonMapper {
    @Mapping(target = "albumId", source = "album.id")
    ChansonDTO toDto(Chanson chanson);

    @Mapping(target = "album", ignore = true)
    Chanson toEntity(ChansonDTO chansonDTO);
}
