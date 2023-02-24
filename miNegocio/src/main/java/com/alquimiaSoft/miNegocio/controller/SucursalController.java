package com.alquimiaSoft.miNegocio.controller;

import com.alquimiaSoft.miNegocio.domain.dto.SucursalDto;
import com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo.PutSucursalMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.GetSucursalesAdicionalesAndMatrizException;
import com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo.PostSucursalException;
import com.alquimiaSoft.miNegocio.services.SucursalService;
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
@RequestMapping("/sucursal")
@Api(tags = "Sucursal" , produces = MediaType.APPLICATION_JSON_VALUE)
public class SucursalController {

    private final SucursalService sucursalService;

    @Autowired
    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "OK"),
            @ApiResponse(code = 400 , message = "BAD REQUEST"),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR")
    })
    @Operation(summary = "Crea una sucursal adicional teniendo en cuenta el id y lo agrega al cliente.")
    @PostMapping("/cliente/{idCliente}")
    public ResponseEntity<SucursalDto> postSucursal(@RequestBody SucursalDto sucursalDto,
                                                    @PathVariable("idCliente") long idCliente) throws PostSucursalException, PutSucursalMatrizException {
        SucursalDto sucursal = this.sucursalService.postSucursal(sucursalDto,idCliente);

        return new ResponseEntity<>(sucursal, HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "OK"),
            @ApiResponse(code = 400 , message = "BAD REQUEST"),
            @ApiResponse(code = 500 , message = "INTERNAL SERVER ERROR")
    })
    @Operation(summary = "Trae una lista de direcciones adicionales y la dirección matriz.")
    @GetMapping("")
    public ResponseEntity<List<SucursalDto>> getSucursalesAdicionalesAndMatriz(@RequestParam(name = "idCliente") long idCliente) throws GetSucursalesAdicionalesAndMatrizException {
        List<SucursalDto> sucursal = this.sucursalService.getSucursalesAdicionalesAndMatriz(idCliente);
        return new ResponseEntity<>(sucursal, HttpStatus.OK);
    }
}
