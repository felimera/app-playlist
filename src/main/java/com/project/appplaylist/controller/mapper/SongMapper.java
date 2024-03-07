package com.project.appplaylist.controller.mapper;

import com.project.appplaylist.controller.dto.CancionDTO;
import com.project.appplaylist.model.Song;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SongMapper {

    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "titulo", source = "title")
    @Mapping(target = "artista", source = "artist")
    @Mapping(target = "anno", source = "year")
    @Mapping(target = "genero", source = "gender")
    CancionDTO toDto(Song entity);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    Song toEntity(CancionDTO dto);
}
