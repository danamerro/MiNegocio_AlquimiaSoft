package com.alquimiaSoft.miNegocio.services;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteNuevoDto;
import com.alquimiaSoft.miNegocio.domain.dto.SucursalDto;
import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.PutSucursalMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorDB.FoundSucursalExistenteException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.DeleteSucursalClienteException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.GetSucursalesAdicionalesAndMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.PostSucursalByClienteNuevoException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.PostSucursalException;

import java.util.List;

public interface SucursalService {
    Sucursal postSucursalbyClienteNuevo(ClienteNuevoDto clienteNuevo) throws FoundSucursalExistenteException, PostSucursalByClienteNuevoException;
    SucursalDto putSucursalMatriz(Long idSucursal) throws PutSucursalMatrizException;
    SucursalDto postSucursal(SucursalDto sucursalNueva, long idCliente) throws PutSucursalMatrizException, PostSucursalException;
    List<SucursalDto> getSucursalesAdicionalesAndMatriz(long idCliente) throws GetSucursalesAdicionalesAndMatrizException;
    void deleteSucursalCliente (List<Sucursal> listaSucursal) throws DeleteSucursalClienteException;
}
