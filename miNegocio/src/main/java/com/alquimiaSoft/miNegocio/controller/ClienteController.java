package com.alquimiaSoft.miNegocio.controller;

import com.alquimiaSoft.miNegocio.domain.dto.ClienteDto;
import com.alquimiaSoft.miNegocio.domain.dto.ClienteNuevoDto;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreAndNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNombreException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorDB.MissingClienteByNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.ClientebyNombreOrNumeroIdentificacionException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.DeleteClienteException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.PostClienteException;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.PutClienteException;
import com.alquimiaSoft.miNegocio.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Api(tags = "Cliente" , produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "OK"),
            @ApiResponse(code = 400 , message = "BAD REQUEST"),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR")
    })
    @Operation(summary = "Trae una lista de clientes, con un nombre o un numero de identificación.")
    @GetMapping()
    public ResponseEntity<List<ClienteDto>> getClientebyNombreOrNumeroIdentificacion(@RequestParam(name = "numeroIdentificacion",required = false)
                                                                                     Integer numeroIdentificacion,
                                                                                     @RequestParam(name = "nombres", required = false)
                                                                                     String nombres) throws MissingClienteByNombreAndNumeroIdentificacionException, ClientebyNombreOrNumeroIdentificacionException, MissingClienteByNumeroIdentificacionException, MissingClienteByNombreException {
        List<ClienteDto> cliente = this.clienteService.getClientebyNombreOrNumeroIdentificacion(numeroIdentificacion,nombres);
        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "OK"),
            @ApiResponse(code = 400 , message = "BAD REQUEST"),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR")
    })
    @Operation(summary = "Crea a los clientes y a su sucursal matriz.")
    @PostMapping("")
    public ResponseEntity<ClienteDto> postCliente(@RequestBody ClienteNuevoDto clienteNuevoDto) throws PostClienteException {
        ClienteDto cliente = this.clienteService.postCliente(clienteNuevoDto);

        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "OK"),
            @ApiResponse(code = 400 , message = "BAD REQUEST"),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR")
    })
    @Operation(summary = "modifica al cliente atraves de el numero de identificación.")
    @PutMapping("{numeroIdentificacion}")
    public ResponseEntity<ClienteDto> putCliente(@RequestBody ClienteDto clienteDto,@PathVariable("numeroIdentificacion")Integer numeroIdentificacion) throws PutClienteException {
        ClienteDto cliente = this.clienteService.putCLiente(clienteDto,numeroIdentificacion);
        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "OK"),
            @ApiResponse(code = 400 , message = "BAD REQUEST"),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR")
    })
    @Operation(summary = "Elimina al cliente atraves del id del cliente.")
    @DeleteMapping("/eliminar/{idCliente}")
    public ResponseEntity deleteCliente(@PathVariable("idCliente")long idCliente) throws DeleteClienteException {
        this.clienteService.deleteCliente(idCliente);
        return new ResponseEntity(HttpStatus.OK);
    }




}
