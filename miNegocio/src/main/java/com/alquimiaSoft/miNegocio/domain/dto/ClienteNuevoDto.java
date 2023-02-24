package com.alquimiaSoft.miNegocio.domain.dto;

import com.alquimiaSoft.miNegocio.domain.entity.Sucursal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class ClienteNuevoDto {
    private Long idCliente;
    @NotNull(message = "ingrese el tipo de identificación")
    private String tipoIdentificacion;
    private Integer numeroIdentificacion;
    @NotNull(message = "debe ingresar un nombre")
    @Pattern(regexp = "\"^[A-Za-z]+$\"")
    private String nombres;
    @Pattern(regexp = "^[a-zA-Z0-9._%+]+@[a-zA-Z0-9.]+\\.[a-zA-Z]{2,}$")
    private String correo;
    private Integer numeroCelular;
    @NotNull(message = "debe tener una dirección")
    private List<Sucursal> direccionesAsociadas;


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Integer getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Integer numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombres() {

        return nombres;
    }

    public void setNombres(String nombres) {

        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(Integer numeroCelular) {

        this.numeroCelular = numeroCelular;
    }

    public List<Sucursal> getDireccionesAsociadas() {

        return direccionesAsociadas;
    }

    public void setDireccionesAsociadas(List<Sucursal> direccionesAsociadas) {
        this.direccionesAsociadas = direccionesAsociadas;
    }
}
