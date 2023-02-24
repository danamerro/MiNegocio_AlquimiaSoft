package com.alquimiaSoft.miNegocio.services.implementations;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteDto;
import com.alquimiaSoft.miNegocio.domain.dto.SucursalDto;
import com.alquimiaSoft.miNegocio.domain.dto.mapper.ClienteMapper;
import com.alquimiaSoft.miNegocio.domain.entity.Cliente;
import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreAndNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.ClientebyNombreOrNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.DeleteClienteException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.PutClienteException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.DeleteSucursalClienteException;
import com.alquimiaSoft.miNegocio.repository.ClienteRepository;
import com.alquimiaSoft.miNegocio.services.SucursalService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClienteServiceImpl.class})
public class ClienteServiceImplTest {

    @Autowired
    //esa anotacion burla a la clase serviceImpl haciendo creer que todo funciona a su alrededor cuando enrealidad esta
    //usando @Mocks
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private SucursalService sucursalService;

    @MockBean
    private ClienteMapper clienteMapper;

    private ClienteDto clienteDto;

    private Cliente cliente;

    private Sucursal sucursal;

    private SucursalDto sucursalDto;

    /*private void setSucursal(){
        this.sucursal = new Sucursal();
        this.sucursal.setIdSucursal(1L);
        this.sucursal.setCiudad("Resistencia");
        this.sucursal.setProvincia("Corrientes");
        this.sucursal.setDireccion("calle pepe 123");
        this.sucursal.setSucursalMatriz(true);
    }

    private void setClienteDto(){
        this.clienteDto = new ClienteDto();
        this.clienteDto.setIdCliente(1L);
        this.clienteDto.setTipoIdentificacion("documento");
        this.clienteDto.setNumeroIdentificacion(123456);
        this.clienteDto.setNombres("ramiro");
        this.clienteDto.setCorreo("ramita@hotmail.com");
        this.clienteDto.setNumeroCelular(123456);
        this.clienteDto.setDireccionesAsociadas(Collections.singletonList(this.sucursal));

    }

    @Before
    public void setUp(){

    }*/

    @Test
    public void givenNumeroIdentificacionAndNombre_WhenGetClientes_ThenReturnResponse() throws MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException, MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException {
        Cliente cliente1 = Mockito.mock(Cliente.class);
        ClienteDto clienteDto1 = Mockito.mock(ClienteDto.class);
        Optional<List<Cliente>> clienteList = Optional.of(Arrays.asList(cliente1));
        Mockito.when(this.clienteRepository.findByNumeroIdentificacionYNombre(1,"test"))
                .thenReturn(clienteList);
        Mockito.when(this.clienteMapper.toDto(cliente1)).thenReturn(clienteDto1);

        List<ClienteDto> clienteDtoList = this.clienteService.getClientebyNombreOrNumeroIdentificacion(1, "test");

        Assert.assertEquals(clienteDtoList.get(0), clienteDto1);
    }

    @Test
    public void givenNumeroIdentificacion_WhenGetClientes_ThenReturnResponse() throws MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException, MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException {
        Cliente cliente1 = Mockito.mock(Cliente.class);
        ClienteDto clienteDto1 = Mockito.mock(ClienteDto.class);
        Optional<List<Cliente>> clienteList = Optional.of(Arrays.asList(cliente1));
        Mockito.when(this.clienteRepository.findByNumeroIdentificacion(2))
                .thenReturn(clienteList);
        Mockito.when(this.clienteMapper.toDto(cliente1)).thenReturn(clienteDto1);

        List<ClienteDto> clienteDtoList = this.clienteService.getClientebyNombreOrNumeroIdentificacion(2,null);

        Assert.assertEquals(clienteDtoList.get(0), clienteDto1);
    }

    @Test
    public void givenNombre_WhenGetClientes_ThenReturnResponse() throws MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException, MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException {
        Cliente cliente1 = Mockito.mock(Cliente.class);
        ClienteDto clienteDto1 = Mockito.mock(ClienteDto.class);
        Optional<List<Cliente>> clienteList = Optional.of(Arrays.asList(cliente1));

        Mockito.when(this.clienteRepository.findByNombre("sofia"))
                .thenReturn(clienteList);
        Mockito.when(this.clienteMapper.toDto(cliente1)).thenReturn(clienteDto1);

        List<ClienteDto> clienteDtoList = this.clienteService.getClientebyNombreOrNumeroIdentificacion(null,"sofia");

        Assert.assertEquals(clienteDtoList.get(0), clienteDto1);
    }

    @Test
    public void givenNothing_WhenGetClientes_ThenReturnResponse() throws MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException, MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException {
        Cliente cliente1 = Mockito.mock(Cliente.class);
        ClienteDto clienteDto1 = Mockito.mock(ClienteDto.class);
        List<Cliente> clienteList = Arrays.asList(cliente1);

        Mockito.when(this.clienteRepository.findAll())
                .thenReturn(clienteList);
        Mockito.when(this.clienteMapper.toDto(cliente1)).thenReturn(clienteDto1);

        List<ClienteDto> clienteDtoList = this.clienteService.getClientebyNombreOrNumeroIdentificacion(null, null);

        Assert.assertEquals(clienteDtoList.get(0), clienteDto1);

    }

    @Test(expected = ClientebyNombreOrNumeroIdentificacionException.class)
    public void givenNumber_WhenGetClientes_ThenReturnException() throws MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException, MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException {
        Mockito.when(this.clienteRepository.findByNumeroIdentificacion(2))
                .thenReturn(Optional.ofNullable(null));

        this.clienteService.getClientebyNombreOrNumeroIdentificacion(2, null);
    }

    @Test
    public void givenClienteNuevoAndNumeroIdentificacion_WhenPutCliente_ThenReturnResponse() throws PutClienteException {

        Cliente cliente1 = Mockito.mock(Cliente.class);
        ClienteDto clienteDto1 = Mockito.mock(ClienteDto.class);
        Optional<List<Cliente>> clienteList = Optional.of(Arrays.asList(cliente1));

        Mockito.when(this.clienteRepository.findByNumeroIdentificacion(223)).thenReturn(clienteList);
        Mockito.when(this.clienteRepository.save(clienteList.get().get(0))).thenReturn(cliente1);
        Mockito.when(this.clienteMapper.toDto(cliente1)).thenReturn(clienteDto1);

        ClienteDto result = this.clienteService.putCLiente(clienteDto1,223);

        Assert.assertEquals(result,clienteDto1);
    }

    @Test(expected = PutClienteException.class)
    public void givenClienteNuevoAndNumeroIdentificacion_WhenPutCliente_ThenReturnException() throws PutClienteException {
        ClienteDto clienteDto1 = Mockito.mock(ClienteDto.class);

        Mockito.when(this.clienteRepository.findByNumeroIdentificacion(111)).thenReturn(Optional.ofNullable(null));

        this.clienteService.putCLiente(clienteDto1,111);
    }

    @Test
    public void givenIdCliente_WhenDeleteCliente_ThenDeleteCliente() throws DeleteSucursalClienteException, DeleteClienteException {
        Cliente cliente1 = Mockito.mock(Cliente.class);
        Optional<Cliente> cliente = Optional.of(cliente1);
        Sucursal sucursal1 = Mockito.mock(Sucursal.class);
        List<Sucursal> sucursalList = Arrays.asList(sucursal1);

        Mockito.when(this.clienteRepository.findById(3L)).thenReturn(cliente);
        Mockito.when(cliente.get().getDireccionesAsociadas()).thenReturn(sucursalList);
        Mockito.doNothing().when(this.sucursalService).deleteSucursalCliente(sucursalList);
        Mockito.doNothing().when(this.clienteRepository).deleteById(3L);

        this.clienteService.deleteCliente(3L);

        verify(this.sucursalService, times(1)).deleteSucursalCliente(sucursalList);
        verify(this.clienteRepository, times(1)).deleteById(3L);
    }

    @Test(expected = DeleteClienteException.class)
    public void givenIdCliente_WhenDeleteCliente_ThenReturnException() throws DeleteClienteException {
        Mockito.when(this.clienteRepository.findById(3L)).thenReturn(Optional.ofNullable(null));

        this.clienteService.deleteCliente(3L);
    }

   /*@Test
    public void givenSucursalNuevaMatrizAndIdCliente_WhenPutSucursalMatriz_ThenReturnResponse() throws PutSucursalMatrizException {
        Sucursal sucursalNueva = Mockito.mock(Sucursal.class);
        Sucursal sucursal = Mockito.mock(Sucursal.class);
        SucursalDto sucursalDto = Mockito.mock(SucursalDto.class);
        ClienteDto clienteDto = Mockito.mock(ClienteDto.class);
        List<Sucursal> sucursalList = Arrays.asList(sucursal);
        Optional<Cliente> cliente = Optional.of(new Cliente());
        cliente.get().setDireccionesAsociadas(sucursalList);


        Mockito.when(this.clienteRepository.findById(4L)).thenReturn(cliente);
        Mockito.when(sucursalNueva.getSucursalMatriz()).thenReturn(true);
        Mockito.when(sucursal.getSucursalMatriz()).thenReturn(true);
        Mockito.when(this.sucursalService.putSucursalMatriz(sucursalList.get(0).getIdSucursal())).thenReturn(sucursalDto);
        Mockito.when(this.clienteRepository.save(cliente.get())).thenReturn(cliente.get());
        Mockito.when(this.clienteMapper.toDto(cliente.get())).thenReturn(clienteDto);

        ClienteDto result = this.clienteService.putSucursalMatriz(sucursalNueva,4L);

        Assert.assertEquals(result.getDireccionesAsociadas().get(1), sucursalNueva);
    }*/


}