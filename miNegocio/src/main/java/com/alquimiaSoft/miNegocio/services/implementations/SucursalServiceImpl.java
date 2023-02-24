package com.alquimiaSoft.miNegocio.services.implementations;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteNuevoDto;
import com.alquimiaSoft.miNegocio.domain.dto.SucursalDto;
import com.alquimiaSoft.miNegocio.domain.dto.mapper.SucursalMapper;
import com.alquimiaSoft.miNegocio.domain.entity.Cliente;
import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.PutSucursalMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorDB.FoundSucursalExistenteException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.DeleteSucursalClienteException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.GetSucursalesAdicionalesAndMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.PostSucursalByClienteNuevoException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.PostSucursalException;
import com.alquimiaSoft.miNegocio.repository.ClienteRepository;
import com.alquimiaSoft.miNegocio.repository.SucursalRepository;
import com.alquimiaSoft.miNegocio.services.ClienteService;
import com.alquimiaSoft.miNegocio.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;
    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    @Autowired
    public SucursalServiceImpl(SucursalRepository sucursalRepository, SucursalMapper sucursalMapper,ClienteService clienteService,ClienteRepository clienteRepository) {
        this.sucursalRepository = sucursalRepository;
        this.sucursalMapper = sucursalMapper;
        this.clienteService = clienteService;
        this.clienteRepository= clienteRepository;
    }


    @Override
    public Sucursal postSucursalbyClienteNuevo(ClienteNuevoDto clienteNuevo) throws FoundSucursalExistenteException, PostSucursalByClienteNuevoException {
        try{
            Optional<Sucursal> sucursalExistente = this.sucursalRepository.findByCiudadProvinciaDireccion(clienteNuevo.getDireccionesAsociadas().get(0).getCiudad(),
                    clienteNuevo.getDireccionesAsociadas().get(0).getProvincia(), clienteNuevo.getDireccionesAsociadas().get(0).getDireccion());

            if(sucursalExistente.isPresent()){
                throw new FoundSucursalExistenteException();
            }

            Sucursal sucursal = new Sucursal();
            sucursal.setProvincia(clienteNuevo.getDireccionesAsociadas().get(0).getProvincia());
            sucursal.setCiudad(clienteNuevo.getDireccionesAsociadas().get(0).getCiudad());
            sucursal.setDireccion(clienteNuevo.getDireccionesAsociadas().get(0).getDireccion());
            sucursal.setSucursalMatriz(true);

            Sucursal sucursal1 = this.sucursalRepository.save(sucursal);

            return sucursal1;
        }catch (Exception e){
            throw new PostSucursalByClienteNuevoException(e);
        }

    }

    /**
     *
     * @param sucursalNueva
     * @param idCliente
     * @return
     * @throws PutSucursalMatrizException
     */

    @Override
    public SucursalDto postSucursal(SucursalDto sucursalNueva, long idCliente) throws PutSucursalMatrizException, PostSucursalException {
        try{
            Optional<Sucursal> sucursalExistente = this.sucursalRepository.findByCiudadProvinciaDireccion(sucursalNueva.getCiudad(),
                    sucursalNueva.getProvincia(),sucursalNueva.getDireccion());

            if(sucursalExistente.isPresent()){
                throw new FoundSucursalExistenteException();
            }

            Sucursal sucursal = this.sucursalRepository.save(this.sucursalMapper.toEntity(sucursalNueva));
            this.clienteService.putSucursalMatriz(sucursal,idCliente);

            return this.sucursalMapper.toDto(sucursal);

        }catch (Exception e){
            throw new PostSucursalException(e);
        }

    }

    @Override
    public SucursalDto putSucursalMatriz(Long idSucursal) throws PutSucursalMatrizException {
        try{
            Optional<Sucursal> sucursal = this.sucursalRepository.findById(idSucursal);

            sucursal.get().setSucursalMatriz(false);

            return this.sucursalMapper.toDto(this.sucursalRepository.save(sucursal.get()));
        }catch (Exception e){
            throw new PutSucursalMatrizException(e);
        }

    }

    /**
     * se trae una lista de sucursales adicionales y la direccion matriz teniendo en cuenta el id del cliente
     * @param idCliente
     * @return saca lo que esta dentro de cliente y llama a las direcciones asociadas, luego hace un stream
     * en donde ese entity lo vuelve en dto y guarda en collect esos datos que se extrajeron.
     * @throws GetSucursalesAdicionalesAndMatrizException
     */
    @Override
    public List<SucursalDto> getSucursalesAdicionalesAndMatriz(long idCliente) throws GetSucursalesAdicionalesAndMatrizException {
        try{
            Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);

            return cliente.get().getDireccionesAsociadas()
                    .stream()
                    .map(this.sucursalMapper::toDto)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new GetSucursalesAdicionalesAndMatrizException(e);
        }
    }

    /**
     * elimina todos los datos de la lista de sucursales que pertenece al cliente
     * @param listaSucursal
     */
    @Override
    @Transactional
    public void deleteSucursalCliente(List<Sucursal> listaSucursal) throws DeleteSucursalClienteException {
        try{
            this.sucursalRepository.deleteAll(listaSucursal);
        }catch (Exception e){
            throw new DeleteSucursalClienteException(e);
        }

    }


}
