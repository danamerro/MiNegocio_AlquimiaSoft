package com.alquimiaSoft.miNegocio.domain.dto.mapper;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteDto;
import com.alquimiaSoft.miNegocio.domain.dto.ClienteNuevoDto;
import com.alquimiaSoft.miNegocio.domain.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDto clienteDto);
    Cliente toEntity(ClienteNuevoDto clienteNuevoDto);
    ClienteDto toDto(Cliente cliente);
}
