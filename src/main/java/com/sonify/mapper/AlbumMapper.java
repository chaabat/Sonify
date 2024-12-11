package com.sonify.mapper;

import com.sonify.dto.AlbumDTO;
import com.sonify.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ChansonMapper.class})
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);
    
    AlbumDTO toDto(Album album);
    Album toEntity(AlbumDTO albumDTO);
}
