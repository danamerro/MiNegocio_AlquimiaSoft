package com.alquimiaSoft.miNegocio.domain.dto.mapper;

import com.alquimiaSoft.miNegocio.domain.dto.SucursalDto;
import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SucursalMapper {

    SucursalMapper INSTANCE = Mappers.getMapper(SucursalMapper.class);

    Sucursal toEntity(SucursalDto sucursalDto);
    SucursalDto toDto(Sucursal sucursal);
}
