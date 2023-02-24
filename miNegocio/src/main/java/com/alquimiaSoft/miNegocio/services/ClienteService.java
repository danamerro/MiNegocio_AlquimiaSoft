package com.alquimiaSoft.miNegocio.services;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteDto;
import com.alquimiaSoft.miNegocio.domain.dto.ClienteNuevoDto;
import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreAndNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.*;

import java.util.List;

public interface ClienteService {
    List<ClienteDto> getClientebyNombreOrNumeroIdentificacion(Integer numeroIdentificacion, String nombres) throws MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException, MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException;
    ClienteDto postCliente(ClienteNuevoDto clienteNuevo) throws PostClienteException;
    ClienteDto putCLiente(ClienteDto clienteNuevo,Integer numeroIdentificacion) throws PutClienteException;
    void deleteCliente(long idCliente) throws DeleteClienteException;
    ClienteDto putSucursalMatriz(Sucursal sucursalNueva, long idCliente) throws PutSucursalMatrizException;

}
