package com.alquimiaSoft.miNegocio.services.implementations;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteDto;
import com.alquimiaSoft.miNegocio.domain.dto.ClienteNuevoDto;
import com.alquimiaSoft.miNegocio.domain.dto.SucursalDto;
import com.alquimiaSoft.miNegocio.domain.dto.mapper.SucursalMapper;
import com.alquimiaSoft.miNegocio.domain.entity.Cliente;
import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.PutSucursalMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorDB.FoundSucursalExistenteException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.GetSucursalesAdicionalesAndMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.PostSucursalByClienteNuevoException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.PostSucursalException;
import com.alquimiaSoft.miNegocio.repository.ClienteRepository;
import com.alquimiaSoft.miNegocio.repository.SucursalRepository;
import com.alquimiaSoft.miNegocio.services.ClienteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={SucursalServiceImpl.class, SucursalRepository.class})
public class SucursalServiceImplTest {

    @Autowired
    @InjectMocks
    private SucursalServiceImpl sucursalService;

    @MockBean
    private SucursalRepository sucursalRepository;
    @MockBean
    private  SucursalMapper sucursalMapper;
    @MockBean
    private  ClienteService clienteService;
    @MockBean
    private  ClienteRepository clienteRepository;

    private Sucursal sucursal;

    @Test
    public void givenClienteNuevo_WhenPostSucursalByClienteNuevo_ThenReturnResponse() throws FoundSucursalExistenteException, PostSucursalByClienteNuevoException {

        Sucursal sucursalNueva = new Sucursal();
        sucursalNueva.setIdSucursal(1L);
        sucursalNueva.setDireccion("test1");
        sucursalNueva.setProvincia("test2");
        sucursalNueva.setCiudad("test3");
        sucursalNueva.setSucursalMatriz(true);
        ClienteNuevoDto clienteNuevoDto = new ClienteNuevoDto();
        clienteNuevoDto.setDireccionesAsociadas(Arrays.asList(sucursalNueva));

        Mockito.when(this.sucursalRepository.findByCiudadProvinciaDireccion(clienteNuevoDto.getDireccionesAsociadas().get(0).getCiudad(),
                clienteNuevoDto.getDireccionesAsociadas().get(0).getProvincia(), clienteNuevoDto.getDireccionesAsociadas().get(0).getDireccion())).thenReturn(Optional.ofNullable(null));

        this.sucursalService.postSucursalbyClienteNuevo(clienteNuevoDto);

        verify(this.sucursalRepository).save(any(Sucursal.class));
    }

    @Test(expected = PostSucursalByClienteNuevoException.class)
    public void givenClienteNuevo_WhenPostSucursalByClienteNuevo_ThenThrowException() throws FoundSucursalExistenteException, PostSucursalByClienteNuevoException {
        Sucursal sucursalNueva = new Sucursal();
        sucursalNueva.setIdSucursal(1L);
        sucursalNueva.setDireccion("test1");
        sucursalNueva.setProvincia("test2");
        sucursalNueva.setCiudad("test3");
        sucursalNueva.setSucursalMatriz(true);
        ClienteNuevoDto clienteNuevoDto = new ClienteNuevoDto();
        clienteNuevoDto.setDireccionesAsociadas(Arrays.asList(sucursalNueva));

        Mockito.when(this.sucursalRepository.findByCiudadProvinciaDireccion(clienteNuevoDto.getDireccionesAsociadas().get(0).getCiudad(),
                clienteNuevoDto.getDireccionesAsociadas().get(0).getProvincia(), clienteNuevoDto.getDireccionesAsociadas().get(0).getDireccion()))
                .thenReturn(Optional.of(sucursalNueva));

        this.sucursalService.postSucursalbyClienteNuevo(clienteNuevoDto);
    }

    @Test
    public void givenSucursalNuevaAndIdCLiente_WhenPostSucursal_ThenReturnResponse() throws PutSucursalMatrizException, PostSucursalException {
        Sucursal sucursalNueva = new Sucursal();
        sucursalNueva.setIdSucursal(1L);
        sucursalNueva.setDireccion("test1");
        sucursalNueva.setProvincia("test2");
        sucursalNueva.setCiudad("test3");
        sucursalNueva.setSucursalMatriz(true);
        ClienteNuevoDto clienteNuevoDto = new ClienteNuevoDto();
        clienteNuevoDto.setDireccionesAsociadas(Arrays.asList(sucursalNueva));

        SucursalDto sucursalDto = Mockito.mock(SucursalDto.class);
        ClienteDto clienteDto = Mockito.mock(ClienteDto.class);

        Mockito.when(this.sucursalRepository.findByCiudadProvinciaDireccion(clienteNuevoDto.getDireccionesAsociadas().get(0).getCiudad(),
                clienteNuevoDto.getDireccionesAsociadas().get(0).getProvincia(), clienteNuevoDto.getDireccionesAsociadas().get(0).getDireccion()))
                .thenReturn(Optional.ofNullable(null));

        Mockito.when(this.sucursalMapper.toEntity(sucursalDto)).thenReturn(sucursalNueva);
        Mockito.when(this.sucursalRepository.save(sucursalNueva)).thenReturn(sucursalNueva);
        Mockito.when(this.clienteService.putSucursalMatriz(sucursalNueva, 1)).thenReturn(clienteDto);
        Mockito.when(this.sucursalMapper.toDto(sucursalNueva)).thenReturn(sucursalDto);

        SucursalDto result = this.sucursalService.postSucursal(sucursalDto, 1);

        Assert.assertEquals(result, sucursalDto);
    }

    @Test(expected = PostSucursalException.class)
    public void givenSucursalNuevaAndIdCLiente_WhenPostSucursal_ThenThrowException() throws PutSucursalMatrizException, PostSucursalException {
        Sucursal sucursalExistente = new Sucursal();
        sucursalExistente.setCiudad("Test1");
        sucursalExistente.setProvincia("Test2");
        sucursalExistente.setDireccion("Test 3");
        Mockito.when(this.sucursalRepository.findByCiudadProvinciaDireccion(anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(sucursalExistente));

        SucursalDto sucursalNueva = new SucursalDto();
        sucursalNueva.setCiudad("Dummy 1");
        sucursalNueva.setProvincia("Dummy 2");
        sucursalNueva.setDireccion("Dummy 3");

        this.sucursalService.postSucursal(sucursalNueva, 1L);
    }

    @Test
    public void givenIdSucursal_WhenPutSucursalMatriz_ThenReturnResponse() throws PutSucursalMatrizException {
        Sucursal sucursal = Mockito.mock(Sucursal.class);
        SucursalDto sucursalDto = Mockito.mock(SucursalDto.class);

        Mockito.when(this.sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursal));
        Mockito.when(this.sucursalRepository.save(sucursal)).thenReturn(sucursal);
        Mockito.when(this.sucursalMapper.toDto(sucursal)).thenReturn(sucursalDto);

        SucursalDto result = this.sucursalService.putSucursalMatriz(1L);

        Assert.assertEquals(result, sucursalDto);
    }

    @Test(expected = PutSucursalMatrizException.class)
    public void givenIdSucursal_WhenPutSucursalMatriz_ThenThrowException() throws PutSucursalMatrizException {

        Mockito.when(this.sucursalRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        this.sucursalService.putSucursalMatriz(1L);
    }

    @Test
    public void givenIdCliente_WhenGetSucursalesAdicionalesAndMatriz_ThenReturnResponse() throws GetSucursalesAdicionalesAndMatrizException {
        Cliente cliente = Mockito.mock(Cliente.class);
        Sucursal sucursal = Mockito.mock(Sucursal.class);
        SucursalDto sucursalDto = Mockito.mock(SucursalDto.class);

        List<Sucursal> sucursalList = Arrays.asList(sucursal);

        Mockito.when(this.clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(cliente.getDireccionesAsociadas()).thenReturn(sucursalList);
        Mockito.when(this.sucursalMapper.toDto(sucursal)).thenReturn(sucursalDto);


        List<SucursalDto> result = this.sucursalService.getSucursalesAdicionalesAndMatriz(1L);

        Assert.assertEquals(result.get(0), sucursalDto);
    }

    @Test(expected = GetSucursalesAdicionalesAndMatrizException.class)
    public void givenIdCliente_WhenGetSucursalesAdicionalesAndMatriz_ThenThrowException() throws GetSucursalesAdicionalesAndMatrizException {

        Mockito.when(this.clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        this.sucursalService.getSucursalesAdicionalesAndMatriz(1L);
    }


}