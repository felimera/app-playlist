package com.project.appplaylist.controller.mapper;

import com.project.appplaylist.controller.dto.PlayListDTO;
import com.project.appplaylist.model.PlayList;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayListMapper {
    PlayListMapper INSTANCE = Mappers.getMapper(PlayListMapper.class);

    @Mapping(target = "nombre",source = "name")
    @Mapping(target = "descripcion",source = "description")
    @Mapping(target = "canciones",ignore = true)
    PlayListDTO toDto(PlayList entity);

    @InheritInverseConfiguration
    @Mapping(target = "id",ignore = true)
    PlayList toEntity(PlayListDTO dto);
}
