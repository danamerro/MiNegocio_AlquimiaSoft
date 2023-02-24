package com.alquimiaSoft.miNegocio.services.implementations;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteDto;
import com.alquimiaSoft.miNegocio.domain.dto.ClienteNuevoDto;
import com.alquimiaSoft.miNegocio.domain.dto.mapper.ClienteMapper;
import com.alquimiaSoft.miNegocio.domain.entity.Cliente;
import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.FoundClienteExistenteException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreAndNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.*;
import com.alquimiaSoft.miNegocio.repository.ClienteRepository;
import com.alquimiaSoft.miNegocio.services.ClienteService;
import com.alquimiaSoft.miNegocio.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final SucursalService sucursalService;
    private final ClienteMapper clienteMapper;

    // se utilizo el @lazy porque hace un circulo de dependencia entre sucursalService y clienteService
    // el lazy hace que se cargue sucursalService cuando sea necesario
    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper,
                              @Lazy SucursalService sucursalService) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.sucursalService = sucursalService;
    }

    /**
     * lo que hace este endpoint es traer una lista de clientes, esta puede filtrarse por numero de identificacion, nombre
     * o ambas.
     * @param numeroIdentificacion usa el numero de documento
     * @param nombres el nombre del cliente
     * @return retorna una lista de clientes
     *
     * @throws MissingClienteByNumeroIdentificacionException excepcion en caso de que si no encuentra en la BD al numero
     * identificación tire un mensaje de error.
     *
     * @throws MissingClienteByNombreException excepcion en caso de que si no encuentra en la BD al nombre, tire un mensaje de error.
     *
     * @throws MissingClienteByNombreAndNumeroIdentificacionException excepcion en caso de que no encuentre en BD
     * el nombre y el numero de identificacion tire un mensaje de error.
     *
     * @throws ClientebyNombreOrNumeroIdentificacionException excepcion en caso de que no se encuentre en la BD
     * el nombre O el numero de identificacion va a tirar un mensaje de error.
     */

    @Override
    public List<ClienteDto> getClientebyNombreOrNumeroIdentificacion(Integer numeroIdentificacion, String nombres)
            throws MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException,
            MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException {
       try {
           //*
           if (numeroIdentificacion != null && nombres == null) {
               Optional<List<Cliente>> clienteList = this.clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);

               if (clienteList.isEmpty()) {
                   throw new MissingClienteByNumeroIdentificacionException();
               }

               return clienteList.get()
                       .stream()
                       .map(this.clienteMapper::toDto)
                       .collect(Collectors.toList());
                //*
           } else if (numeroIdentificacion == null && nombres != null) {
               Optional<List<Cliente>> clienteList = this.clienteRepository.findByNombre(nombres);

               if (clienteList.isEmpty()) {
                   throw new MissingClienteByNombreException();
               }

               return clienteList.get()
                       .stream()
                       .map(this.clienteMapper::toDto)
                       .collect(Collectors.toList());

               //*
           } else if (numeroIdentificacion != null && nombres != null) {
               Optional<List<Cliente>> clienteList = this.clienteRepository.findByNumeroIdentificacionYNombre(numeroIdentificacion, nombres);
               if (clienteList.isEmpty()) {
                   throw new MissingClienteByNombreAndNumeroIdentificacionException();
               }

               return clienteList.get()
                       .stream()
                       .map(this.clienteMapper::toDto)
                       .collect(Collectors.toList());
           } else {
               List<Cliente> clienteList = this.clienteRepository.findAll();

               return clienteList
                       .stream()
                       .map(this.clienteMapper::toDto)
                       .collect(Collectors.toList());
           }

       } catch (Exception e) {
            throw new ClientebyNombreOrNumeroIdentificacionException(e);
       }

    }

    /**
     * crea el cliente
     * @param clienteNuevo
     * @return
     * @throws PostClienteException
     */

    @Override
    public ClienteDto postCliente(ClienteNuevoDto clienteNuevo) throws PostClienteException {
        try{
            Optional<List<Cliente>> cliente = this.clienteRepository.findByNumeroIdentificacion(clienteNuevo.getNumeroIdentificacion());
            if(!cliente.get().isEmpty()){
                throw new FoundClienteExistenteException();
            }
            //se creo instancia de sucursal y se las llena de datos sacados de cliente nuevo
            Sucursal sucursal = this.sucursalService.postSucursalbyClienteNuevo(clienteNuevo);

            clienteNuevo.getDireccionesAsociadas().remove(0);
            clienteNuevo.getDireccionesAsociadas().add(sucursal);

            Cliente clienteNuevoEntity = this.clienteRepository.save(this.clienteMapper.toEntity(clienteNuevo));

            return this.clienteMapper.toDto(clienteNuevoEntity);
        }catch (Exception e){
            throw new PostClienteException(e);
        }

    }

    @Override
    public ClienteDto putCLiente(ClienteDto clienteNuevo,Integer numeroIdentificacion) throws PutClienteException {
        try {
            Optional<List<Cliente>> cliente = this.clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);
            /**
             * el segundo get indica la posicion de la lista, para luego modificar lo que se pide
             */
            cliente.get().get(0).setTipoIdentificacion(clienteNuevo.getTipoIdentificacion());
            cliente.get().get(0).setNumeroIdentificacion(clienteNuevo.getNumeroIdentificacion());
            cliente.get().get(0).setNombres(clienteNuevo.getNombres());
            cliente.get().get(0).setCorreo(clienteNuevo.getCorreo());
            cliente.get().get(0).setNumeroCelular(clienteNuevo.getNumeroCelular());

            this.clienteRepository.save(cliente.get().get(0));

            return this.clienteMapper.toDto(this.clienteRepository.findByNumeroIdentificacion(numeroIdentificacion).get().get(0));
        }catch (Exception e){
            throw new PutClienteException(e);
        }
    }

    @Override
    public void deleteCliente(long idCliente) throws DeleteClienteException {

        try{
            Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);
            List<Sucursal> listaSucursales = cliente.get().getDireccionesAsociadas();
            this.sucursalService.deleteSucursalCliente(listaSucursales);
            this.clienteRepository.deleteById(idCliente);
        }catch (Exception e){
            throw new DeleteClienteException(e);
        }

    }

    /**
     * verifica que el cliente exista con el id, si la sucursal nueva es matriz modifica la sucursal matriz anterior,
     * la actualiza en la base de datos junto con los cambios, modifica dicha sucursal en la lista del cliente,
     * añade la nueva sucursal y actualiza al cliente con la sucursal nueva.
     * caso contrario solamente añade la sucursal nueva, mantiene la sucursal matriz anterior y actualiza al cliente.
     * @param sucursalNueva es la nueva sucursal que puede ser o no matriz
     * @param idCliente es el id unico del cliente
     * @return el cliente junto con la lista de sucursales
     */

    @Override
    public ClienteDto putSucursalMatriz(Sucursal sucursalNueva, long idCliente) throws PutSucursalMatrizException {

        try{
            Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);

            if(sucursalNueva.getSucursalMatriz()){
                List<Sucursal> sucursal = cliente.get().getDireccionesAsociadas()
                        .stream()
                        .filter(Sucursal::getSucursalMatriz)
                        .collect(Collectors.toList());

                this.sucursalService.putSucursalMatriz(sucursal.get(0).getIdSucursal());

                cliente.get().getDireccionesAsociadas()
                        .stream()
                        .filter(s -> s.getSucursalMatriz())
                        .forEach(s -> s.setSucursalMatriz(false));

                cliente.get().getDireccionesAsociadas().add(sucursalNueva);

                return this.clienteMapper.toDto(this.clienteRepository.save(cliente.get()));

            }else{

                cliente.get().getDireccionesAsociadas().add(sucursalNueva);

                return this.clienteMapper.toDto(this.clienteRepository.save(cliente.get()));

            }

        }catch (Exception e){
            throw new PutSucursalMatrizException(e);
        }

    }
}
