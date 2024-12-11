package com.sonify.mapper;

import com.sonify.dto.AlbumDTO;
import com.sonify.model.Album;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ChansonMapper.class})
public interface AlbumMapper {
    AlbumDTO toDto(Album album);
    Album toEntity(AlbumDTO albumDTO);
}
